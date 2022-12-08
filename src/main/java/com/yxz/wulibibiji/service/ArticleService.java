package com.yxz.wulibibiji.service;

import com.yxz.wulibibiji.dto.ArticleDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;


/**
 * @author Yang
 * @description 针对表【article】的数据库操作Service
 * @createDate 2022-11-18 22:24:23
 */
@Service
public interface ArticleService extends IService<Article> {
    public Result queryNewArticle(Integer current);

    public Result queryHotArticle(Integer current);

    public Result createArticle(ArticleDTO articleDTO);


}
