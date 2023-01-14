package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.SensitiveUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.dto.ArticleDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.dto.UserDTO;
import com.yxz.wulibibiji.entity.Article;
import com.yxz.wulibibiji.mapper.ArticleMapper;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.CategoryService;
import com.yxz.wulibibiji.service.other.IQiNiuService;
import com.yxz.wulibibiji.utils.MyFileUtil;
import com.yxz.wulibibiji.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.yxz.wulibibiji.utils.RedisConstants.*;
import static com.yxz.wulibibiji.utils.SystemConstants.*;

/**
 * @author Yang
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2022-11-18 22:24:23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IQiNiuService qiNiuService;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public Result queryNewArticle(Integer current) {
        // 1.获取当前页数据
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0).orderByDesc("created_time");
        IPage<Article> page = articleMapper.listJoinInfoPages(new Page<>(current, MAX_PAGE_SIZE), wrapper);
        page.getRecords().forEach(article -> {
            this.isArticleLiked(article);
        });
        return Result.ok(page);
    }

    @Override
    public Result queryHotArticle(Integer current) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();//
        wrapper.eq("is_deleted", 0).ge("created_time", DateUtil.lastMonth()).orderByDesc("article_like_count");
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
        String categoryName = (String) result.getData();
        Article article = new Article(null, articleDTO.getArticleTitle(),
                articleDTO.getArticleContent(),
                0, 0, 0, DateUtil.date(), DateUtil.date(), null,
                0, articleDTO.getArticleCategoryId(), user.getEmail(), categoryName);
        this.save(article);
        Integer id = article.getArticleId();
        stringRedisTemplate.opsForValue().set(ARTICLE_UPLOAD_IMG_ID + id, "1", ARTICLE_UPLOAD_IMG_ID_TTL, TimeUnit.MINUTES);
        return Result.ok(id);
    }

    @Override
    public Result likeArticle(long id) {
        //1获取登录用户
        String userId = UserHolder.getUser().getEmail();
        //2.判断当前用户是否已经点赞
        String key = ARTICLE_LIKED_KEY + id;
        Double score = stringRedisTemplate.opsForZSet().score(key, userId);
        if (score == null) {
            //3.如果未点赞，可以点赞
            boolean isSuccess = update().setSql("article_like_count = article_like_count + 1").eq("article_id", id).update();
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().add(key, userId, System.currentTimeMillis());
            }
        } else {
            //已点赞 可以取消
            boolean isSuccess = update().setSql("article_like_count = article_like_count - 1").eq("article_id", id).update();
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().remove(key, userId);
            }
        }
        return Result.ok();
    }

    @Override
    public Result uploadImg(List<MultipartFile> files, Integer id) {
        if (files.size() > 9) {
            return Result.fail("最多上传9张图片!");
        }
        for (int i = 0; i < files.size(); i++) {
            if (!MyFileUtil.sizeCheck(files.get(i), 10)) {
                return Result.fail("每张图片大小应为10MB以内!");
            }
        }
        String s = stringRedisTemplate.opsForValue().get(ARTICLE_UPLOAD_IMG_ID + id);
        if (StrUtil.isBlank(s)) {
            return Result.fail("没找到对应的文章!");
        }
        Article article = this.getById(id);
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
                urls[i] = IMAGE_UPLOAD_DIR + key + WITH_MARK;
            }
            if (flag) {
                article.setArticleImg(String.join(";", urls));
                updateById(article);
                return Result.ok("更新成功");
            }
            return Result.fail("更新失败！");
        } catch (Exception e) {
            return Result.fail("系统异常！");
        } finally {
            stringRedisTemplate.delete(CACHE_ARTICLE_NEW_KEY);
        }
    }

    @Override
    public Result allArticle(String useId, int current) {
        IPage<Article> page = query().eq("article_user_id", useId).orderByDesc("created_time").page(new Page<>(1, MAX_PAGE_SIZE));
        return Result.ok(page);
    }

    @Override
    public Result detailAriticle(Long id) {
        Article article = getById(id);
        if (article == null) {
            return Result.fail("没有这篇文章");
        }
        article.setArticleViewCount(article.getArticleViewCount() + RandomUtil.randomInt(3) + 1);
        updateById(article);
        return Result.ok(article);
    }


    private void isArticleLiked(Article article) {
        //1获取登录用户
        if (UserHolder.getUser() == null) {
            return;
        }
        String userId = UserHolder.getUser().getEmail();
        String key = ARTICLE_LIKED_KEY + article.getArticleId();
        Double score = stringRedisTemplate.opsForZSet().score(key, userId);
        article.setLiked(score != null);
    }
}




