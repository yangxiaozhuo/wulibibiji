package com.yxz.wulibibiji.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Uvcount;

/**
 * @author Yang
 * @description 针对表【uvCount】的数据库操作Service
 * @createDate 2023-01-12 22:10:20
 */
public interface UvcountService extends IService<Uvcount> {

    Result getDay();

    Result getAll();
}
