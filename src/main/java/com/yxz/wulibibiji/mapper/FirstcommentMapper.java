package com.yxz.wulibibiji.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yxz.wulibibiji.entity.Firstcomment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Yang
 * @description 针对表【firstComment】的数据库操作Mapper
 * @createDate 2022-12-07 11:45:17
 * @Entity com.yxz.wulibibiji.entity.Firstcomment
 */
public interface FirstcommentMapper extends BaseMapper<Firstcomment> {

    IPage<Firstcomment> listJoinInfoPages(IPage<Firstcomment> page, @Param(Constants.WRAPPER) Wrapper<Firstcomment> queryWrapper);
}




