package com.yxz.wulibibiji.article;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxz.wulibibiji.entity.Article;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static com.yxz.wulibibiji.utils.SystemConstants.MAX_PAGE_SIZE;

/**
 * @author yangxiaozhuo
 * @date 2022/12/07
 */
@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleServiceImpl articleService;

    /**
     * 测试自己写的联表查询，直接查出用户的姓名和头像
     */
    @Test
    public void testQueryArticle() {
        Page<Article> page = articleService.query().orderByDesc("created_time").page(new Page<>(1, MAX_PAGE_SIZE));
        System.out.println(JSONUtil.toJsonStr(page));
    }

    @Test
    public void aa() {
        String[] urls = new String[2];
        urls[0] = "http://qiniu.yangxiaobai.top/2022/12/07/sadwedwqdqwdd";
        urls[1] = "http://qiniu.yangxiaobai.top/2022/12/07/sadwedwqdqwdd";
        String join = String.join(";", urls);
        System.out.println(JSONUtil.toJsonStr(urls));
        System.out.println(JSONUtil.toJsonStr(join));
        System.out.println(join);
    }
}
