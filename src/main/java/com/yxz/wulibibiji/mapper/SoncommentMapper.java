package com.yxz.wulibibiji.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yxz.wulibibiji.entity.Firstcomment;
import com.yxz.wulibibiji.entity.Soncomment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Yang
 * @description 针对表【sonComment】的数据库操作Mapper
 * @createDate 2022-12-07 11:45:17
 * @Entity com.yxz.wulibibiji.entity.Soncomment
 */
public interface SoncommentMapper extends BaseMapper<Soncomment> {
    IPage<Soncomment> listJoinInfoPages(IPage<Soncomment> page, @Param(Constants.WRAPPER) Wrapper<Soncomment> queryWrapper);

}




