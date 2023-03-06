package com.yxz.wulibibiji.es;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yxz.wulibibiji.dto.EsArticleDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Article;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.impl.EsArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxiaozhuo
 * @date 2023/03/06
 */
@SpringBootTest
public class MysqlToEs {

    @Resource
    private ArticleService articleService;

    @Resource
    private EsArticleServiceImpl esService;

    @Test
    void fun() {
        ArrayList<EsArticleDTO> esArticles = new ArrayList<>();
        int current = 1;
        while (true) {
            Result result = articleService.queryNewArticle(current, 0);
            IPage<Article> data = (IPage<Article>) result.getData();
            List<Article> records = data.getRecords();
            if (records.size()==0) {
                break;
            }
            esArticles.addAll(BeanUtil.copyToList(records, EsArticleDTO.class));
            current++;
        }
//        System.out.println(esArticles.toString());
//        System.out.println(esService.addArticleAll(esArticles));
    }

}
