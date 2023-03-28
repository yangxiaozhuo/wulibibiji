package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.SensitiveUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.Event.EventProducer;
import com.yxz.wulibibiji.dto.ArticleDTO;
import com.yxz.wulibibiji.dto.Event;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.dto.UserDTO;
import com.yxz.wulibibiji.entity.Article;
import com.yxz.wulibibiji.entity.User;
import com.yxz.wulibibiji.mapper.ArticleMapper;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.CategoryService;
import com.yxz.wulibibiji.service.EsArticleService;
import com.yxz.wulibibiji.service.UserService;
import com.yxz.wulibibiji.service.other.IQiNiuService;
import com.yxz.wulibibiji.utils.MyFileUtil;
import com.yxz.wulibibiji.utils.UserHolder;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import static com.yxz.wulibibiji.utils.RabbitConstants.TOPIC_LIKE;
import static com.yxz.wulibibiji.utils.RedisConstants.ARTICLE_LIKED_KEY;
import static com.yxz.wulibibiji.utils.SystemConstants.*;

/**
 * @author Yang
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2022-11-18 22:24:23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IQiNiuService qiNiuService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private UserService userService;

    @Autowired
    private EsArticleService esArticleService;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;


    @DS("slave")
    @Override
    public Result queryNewArticle(Integer current, Integer category) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("created_time");
        if (category != 0) {
            wrapper.eq("article_category_id", category);
        }
        int count = this.count(wrapper);
        if(count == 0) {
            return Result.ok();
        }
        wrapper.last("limit " + Math.min(count - 1, (current * 10 - 1))  + ", 1");
        wrapper.select("created_time");
        Article one = getOne(wrapper);
        wrapper = new QueryWrapper<>();
        wrapper.ge("created_time", one.getCreatedTime()).orderByAsc("created_time");
        IPage<Article> page = articleMapper.listJoinInfoPages(new Page<>(1, MAX_PAGE_SIZE,false), wrapper);
        page.setTotal(count);
        page.setCurrent(current);
        int size = page.getRecords().size();
        for (int i = 0; i < size / 2; i++) {
            Article temp = page.getRecords().get(i);
            page.getRecords().set(i, page.getRecords().get(size - i - 1));
            page.getRecords().set(size - i - 1, temp);
        }
        page.getRecords().forEach(article -> {
            this.isArticleLiked(article);
        });
        return Result.ok(page);
    }

    @DS("slave")
    @Override
    public Result queryHotArticle(Integer current, Integer category) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.ge("created_time", DateUtil.lastMonth()).orderByDesc("article_like_count").orderByDesc("created_time");
        if (category != 0) {
            wrapper.eq("article_category_id", category);
        }
        IPage<Article> page = articleMapper.listJoinInfoPages(new Page<>(current, MAX_PAGE_SIZE), wrapper);
        // 1.获取当前页数据
        List<Article> records = page.getRecords();
        // 2.查询是否被点赞了
        records.forEach(article -> {
            this.isArticleLiked(article);
        });
        return Result.ok(page);
    }

    @Override
    public Result createArticle(ArticleDTO articleDTO) {
        UserDTO user = UserHolder.getUser();
        Result result = checkArticle(articleDTO);
        if (SUCCESS_CODE != result.getCode()) {
            return result;
        }
        String categoryName = (String) categoryService.getCategoryById(articleDTO.getArticleCategoryId()).getData();
        Article article = new Article(null, articleDTO.getArticleTitle(),
                articleDTO.getArticleContent(),
                0, 0, 0, DateUtil.date(), DateUtil.date(), null,
                0, articleDTO.getArticleCategoryId(), user.getEmail(), categoryName);
        this.save(article);
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                esArticleService.addArticle(article);
            }
        });
        uploadImg(articleDTO.getFiles(), article);
        return Result.ok();
    }

    private Result checkArticle(ArticleDTO articleDTO) {
        if (articleDTO.getArticleTitle().length() > 40) {
            return Result.fail("标题最多不允许超过40个字符");
        }
        //处理敏感词
        List<FoundWord> foundAllSensitive = SensitiveUtil.getFoundAllSensitive(articleDTO.getArticleTitle());
        if (!foundAllSensitive.isEmpty()) {
            return Result.fail("标题中含有以下违禁词 " + foundAllSensitive.toString() + " ,请修改后发布");
        }
        foundAllSensitive = SensitiveUtil.getFoundAllSensitive(articleDTO.getArticleContent());
        if (!foundAllSensitive.isEmpty()) {
            return Result.fail("正文中中含有以下违禁词 " + foundAllSensitive.toString() + " ,请修改后发布");
        }
        Result result = categoryService.getCategoryById(articleDTO.getArticleCategoryId());
        if (result.getCode() != SUCCESS_CODE) {
            return result;
        }
        if (articleDTO.getFiles() != null) {
            if (articleDTO.getFiles().size() > 9) {
                return Result.fail("最多上传9张图片!");
            }
            for (int i = 0; i < articleDTO.getFiles().size(); i++) {
                if (!MyFileUtil.sizeCheck(articleDTO.getFiles().get(i), 2)) {
                    return Result.fail("每张图片大小应为2MB以内!");
                }
            }
        }
        return Result.ok();
    }


    private Result uploadImg(List<MultipartFile> files, Article article) {
        if (files == null) {
            return Result.ok();
        }
        String[] urls = new String[files.size()];
        try {
            boolean flag = true;
            for (int i = 0; i < files.size(); i++) {
                MultipartFile file = files.get(i);
                String format = DateUtil.format(DateUtil.date(), "yyyy/MM/");
                String type = FileTypeUtil.getType(file.getInputStream());
                String string = article.getArticleId() + RandomUtil.randomString(10 - article.getArticleId().toString().length()) + "." + type;
                String key = "article/" + format + string;
                flag = flag && (qiNiuService.uploadFile(file.getInputStream(), key).getCode() == SUCCESS_CODE);
                urls[i] = IMAGE_UPLOAD_DIR + key;
            }
            if (flag) {
                article.setArticleImg(String.join(";", urls));
                updateById(article);
                return Result.ok("更新成功");
            }
            return Result.fail("更新失败！");
        } catch (Exception e) {
            return Result.fail("系统异常！");
        }
    }


    @Override
    public Result likeArticle(long id) {
        //1获取登录用户
        String userId = UserHolder.getUser().getEmail();
        //2.判断当前用户是否已经点赞
        String key = ARTICLE_LIKED_KEY + id;
        Double score = redissonClient.getScoredSortedSet(key).getScore(userId);
        if (score == null) {
            //3.如果未点赞，可以点赞
            boolean isSuccess = update().setSql("article_like_count = article_like_count + 1").eq("article_id", id).update();
            if (isSuccess) {
                redissonClient.getScoredSortedSet(key).add(System.currentTimeMillis(), userId);
            }
            sentMq(id + "", getById(id).getArticleUserId());
        } else {
            //已点赞 可以取消
            boolean isSuccess = update().setSql("article_like_count = article_like_count - 1").eq("article_id", id).update();
            if (isSuccess) {
                redissonClient.getScoredSortedSet(key).remove(userId);
            }
        }
        return Result.ok();
    }

    public void sentMq(String articleid, String userid) {
        Event event = new Event(TOPIC_LIKE, UserHolder.getUser().getEmail(), "article", articleid, userid);
        eventProducer.fireEvent(event);
    }


    @Override
    @DS("slave")
    public Result allArticle(String useId, int current) {
        User user = userService.getById(useId);
        if (user == null) {
            return Result.fail("没用此用户");
        }
        IPage<Article> page = query().eq("article_user_id", useId).orderByDesc("created_time").page(new Page<>(current, MAX_PAGE_SIZE));
        page.getRecords().forEach(article -> isArticleLiked(article));
        page.getRecords().forEach(article -> {
            article.setAvatar(user.getAvatar());
            article.setName(user.getNickname());
        });
        return Result.ok(page);
    }

    @Override
    public Result detailArticle(Long id) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", id);
        Article article = articleMapper.queryDetail(wrapper);
        if (article == null) {
            return Result.fail("没有这篇文章");
        }
        update().eq("article_id", id).setSql("article_view_count = article_view_count + 1").update();
        isArticleLiked(article);
        return Result.ok(article);
    }


    private void isArticleLiked(Article article) {
        //1获取登录用户
        if (UserHolder.getUser() == null) {
            return;
        }
        String userId = UserHolder.getUser().getEmail();
        String key = ARTICLE_LIKED_KEY + article.getArticleId();
        Double score = redissonClient.getScoredSortedSet(key).getScore(userId);
        article.setLiked(score != null);
    }
}




