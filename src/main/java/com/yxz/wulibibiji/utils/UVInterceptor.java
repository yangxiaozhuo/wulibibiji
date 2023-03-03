package com.yxz.wulibibiji.utils;

import cn.hutool.core.date.DateUtil;
import org.redisson.api.RedissonClient;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yxz.wulibibiji.utils.RedisConstants.SYSTEM_ALL_VISIT;
import static com.yxz.wulibibiji.utils.RedisConstants.SYSTEM_DAY_VISIT;

/**
 * @author yangxiaozhuo
 * @date 2023/01/12
 */
public class UVInterceptor implements HandlerInterceptor {

    private RedissonClient redissonClient;

    public UVInterceptor(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = SYSTEM_DAY_VISIT + DateUtil.today();
        redissonClient.getAtomicLong(key).incrementAndGet();
        redissonClient.getAtomicLong(SYSTEM_ALL_VISIT).incrementAndGet();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
