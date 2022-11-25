package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.dto.ArticleDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.dto.UserDTO;
import com.yxz.wulibibiji.entity.Article;
import com.yxz.wulibibiji.entity.Category;
import com.yxz.wulibibiji.entity.User;
import com.yxz.wulibibiji.mapper.ArticleMapper;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.CategoryService;
import com.yxz.wulibibiji.service.UserService;
import com.yxz.wulibibiji.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.yxz.wulibibiji.utils.RedisConstants.*;
import static com.yxz.wulibibiji.utils.SystemConstants.MAX_PAGE_SIZE;

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
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public Result queryNewArticle(Integer current) {
        String key = CACHE_ARTICLE_NEW_KEY + current;
        String cache = stringRedisTemplate.opsForValue().get(key);
        if (cache != null) {
            Page<Article> page = new Page<>();
            page.setRecords(JSONUtil.toList(cache, Article.class));
            return Result.ok(page);
        }
        Page<Article> page = reflashNewArticle(key, current);
        return Result.ok(page);
    }

    private Page<Article> reflashNewArticle(String key, Integer current) {
        Page<Article> page = query().orderByDesc("created_time").page(new Page<>(current, MAX_PAGE_SIZE));
        // 1.获取当前页数据
        List<Article> records = page.getRecords();
        // 2.查询是否被点赞了
        records.forEach(article -> {
            this.queryArticleUser(article);
            this.isArticleLiked(article);
        });
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(records, new JSONConfig().setIgnoreNullValue(false)));
        return page;
    }

    @Override
    public Result queryHotArticle(Integer current) {
        Page<Article> page = query().ge("created_time", DateUtil.lastMonth()).orderByDesc("article.article_like_count").page(new Page<>(current, MAX_PAGE_SIZE));
        // 1.获取当前页数据
        List<Article> records = page.getRecords();
        // 2.查询是否被点赞了
        records.forEach(article -> {
            this.queryArticleUser(article);
            this.isArticleLiked(article);
        });
        return Result.ok(page);
    }

    @Override
    public Result createArticle(ArticleDTO articleDTO) {
        UserDTO user = UserHolder.getUser();
        if (user == null) {
            return Result.fail("请登录后再试");
        }
        if (articleDTO.getArticleTitle().length() > 40) {
            return Result.fail("标题最多不允许超过40个字符");
        }
        String key = ARTICLE_CATEGORY_NAME + articleDTO.getArticleCategoryId();
        String categoryName = stringRedisTemplate.opsForValue().get(key);
        if (categoryName == null) {
            reflashCategory();
            categoryName = stringRedisTemplate.opsForValue().get(key);
            if (categoryName == null) {
                return Result.fail("没有这个文章类别，请重新选择");
            }
        }
        Article article = new Article(null, articleDTO.getArticleTitle(),
                articleDTO.getArticleContent(),
                0, 0, 0, DateUtil.date(), DateUtil.date(), articleDTO.getArticleImgCount(),
                0, articleDTO.getArticleCategoryId(), user.getEmail(), categoryName);
        this.save(article);
        Page<Article> page = reflashNewArticle(CACHE_ARTICLE_NEW_KEY + 1, 1);
        return Result.ok(page);
    }

    //更新category的redis缓存
    private void reflashCategory() {
        List<Category> list = categoryService.list();
        for (int i = 0; i < list.size(); i++) {
            Category category = list.get(i);
            String key = ARTICLE_CATEGORY_NAME + category.getCategoryId();
            stringRedisTemplate.opsForValue().set(key, category.getCategoryName());
        }
    }

    private void isArticleLiked(Article article) {
        //1获取登录用户
        if (UserHolder.getUser() == null) {
            return;
        }
        String userId = UserHolder.getUser().getEmail();
        String key = ARTICLE_LIKED_KEY + article.getArticleCategoryId();
        Double score = stringRedisTemplate.opsForZSet().score(key, userId);
        article.setLiked(score != null);
    }

    private void queryArticleUser(Article article) {
        String userId = article.getArticleUserId();
        User user = userService.getById(userId);
        article.setName(user.getNickname());
        article.setAvatar(user.getAvatar());
    }
}




