package com.yxz.wulibibiji.service;

import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Article;

/**
 * @author yangxiaozhuo
 * @date 2023/03/05
 */
public interface EsArticleService {

    Result search(String key);

    void addArticle(Article article);
}
