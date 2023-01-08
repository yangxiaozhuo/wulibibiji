package com.yxz.wulibibiji.service;

import com.yxz.wulibibiji.dto.ArticleDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @author Yang
 * @description 针对表【article】的数据库操作Service
 * @createDate 2022-11-18 22:24:23
 */
@Service
public interface ArticleService extends IService<Article> {
    Result queryNewArticle(Integer current);

    Result queryHotArticle(Integer current);

    Result createArticle(ArticleDTO articleDTO);

    Result likeArticle(long id);

    Result uploadImg(List<MultipartFile> files, Integer id);

}
