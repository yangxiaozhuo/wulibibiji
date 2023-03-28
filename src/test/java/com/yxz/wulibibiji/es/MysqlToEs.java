package com.yxz.wulibibiji.es;

import com.yxz.wulibibiji.entity.Article;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.impl.EsArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
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
    void fun() throws InterruptedException {
        boolean t = esService.delete("article");
        if (!t) {
            System.out.println("失败");
            return;
        }
        //1 - 100_0000
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                add(1, 333333);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                add(333334, 666666);
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                add(666666, 1000000);
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("over");
    }

    private void add(int start, int end) {
        int l = start;
        int r = start + 50;
        int t = 1;
        while (true) {
            List<Article> list = articleService.query().ge("article_id", l).le("article_id", r).list();
            esService.addArticleAll(list);
            l = r + 1;
            r = l + 50;
            if (l > end) {
                break;
            }
            if (((l - start) * 100 / (end - start)) > t) {
                System.out.println("已经到" + l);
                System.out.println(t);
                t++;
            }
        }
        System.out.println(start + "===>" + end + "over");
    }

}
