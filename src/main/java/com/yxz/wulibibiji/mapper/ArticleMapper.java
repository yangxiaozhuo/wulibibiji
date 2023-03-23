package com.yxz.wulibibiji.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yxz.wulibibiji.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Yang
 * @description 针对表【article】的数据库操作Mapper
 * @createDate 2022-11-18 22:24:23
 * @Entity com.yxz.wulibibiji.entity.Article
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<Article> listJoinInfoPages(IPage<Article> page, @Param(Constants.WRAPPER) Wrapper<Article> queryWrapper);


    Article queryDetail(@Param(Constants.WRAPPER) Wrapper<Article> queryWrapper);
}




