package com.yxz.wulibibiji.article;

import cn.hutool.core.io.FileUtil;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.SensitiveProcessor;
import cn.hutool.dfa.SensitiveUtil;
import cn.hutool.dfa.WordTree;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxz.wulibibiji.entity.Article;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.impl.ArticleServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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
     * 测试敏感词屏蔽
     */
    @Test
    public void testSenstive() {
//        List<String> list = new ArrayList<>();
//        list.add("大傻蛋");
//        SensitiveUtil.init(list);
        String text = "2022年11月18日，“烂尾”8年的西安楼盘易合坊开始正式交房，但现场仅有个别业主选择收房。原因是，他们被要求交一笔将近20万的“续建费”，才能换回房产证，这让人们普遍难以接受。/n然而，多位律师认为，“续建费”是一个平衡各方利益的解决方式，合理且具有法律效力。/n在全国，不少其他烂尾楼的业主都在关注西安易合坊。或许，对于未来一些烂尾楼盘重启，发生在这个冬天的事情具有着某种样本意义。";
        text = "月有阴晴圆缺";
        String after = SensitiveUtil.sensitiveFilter(text, false, new SensitiveProcessor() {
            @Override
            public String process(FoundWord foundWord) {
                return "*";
            }
        });
        String s = SensitiveUtil.sensitiveFilter(text);
        System.out.println("直接=======");
        System.out.println(text);
        System.out.println(s);
        System.out.println("true=======");
        System.out.println(text);
        System.out.println(after);
    }

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
