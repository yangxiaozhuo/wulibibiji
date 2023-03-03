package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Uvcount;
import com.yxz.wulibibiji.mapper.UvcountMapper;
import com.yxz.wulibibiji.service.UvcountService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yxz.wulibibiji.utils.RedisConstants.SYSTEM_ALL_VISIT;
import static com.yxz.wulibibiji.utils.RedisConstants.SYSTEM_DAY_VISIT;

/**
 * @author Yang
 * @description 针对表【uvCount】的数据库操作Service实现
 * @createDate 2023-01-12 22:10:20
 */
@Service
public class UvcountServiceImpl extends ServiceImpl<UvcountMapper, Uvcount> implements UvcountService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public Result getDay() {
        String key = SYSTEM_DAY_VISIT + DateUtil.today();
        return Result.ok(redissonClient.getBucket(key).get());
    }

    @Override
    public Result getAll() {
        return Result.ok(redissonClient.getBucket(SYSTEM_ALL_VISIT).get());
    }
}




