package com.yxz.wulibibiji.utils;

import cn.hutool.core.date.DateUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    private StringRedisTemplate stringRedisTemplate;

    public UVInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = SYSTEM_DAY_VISIT + DateUtil.today();
        stringRedisTemplate.opsForValue().increment(key);
        stringRedisTemplate.opsForValue().increment(SYSTEM_ALL_VISIT);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
