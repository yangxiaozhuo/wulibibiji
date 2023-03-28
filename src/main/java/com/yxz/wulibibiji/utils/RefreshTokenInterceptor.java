package com.yxz.wulibibiji.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yxz.wulibibiji.dto.UserDTO;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.yxz.wulibibiji.utils.RedisConstants.SINGLE_POINT_KEY;

/**
 * @author yangxiaozhuo
 * @date 2022/11/19
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private RedissonClient redissonClient;

    public RefreshTokenInterceptor(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1。获取token
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            return true;
        }
        //2。获取redis的用户信息
        String key = RedisConstants.LOGIN_USER_KEY + token;
        RMap<Object, Object> userMap = redissonClient.getMap(key);
        //3.判断用户是不是存在
        if (userMap.isEmpty()) {
            return true;
        }
        //4.存在，map转对象
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
        UserHolder.saveUser(userDTO);

        //刷新token有效期
        redissonClient.getMap(key).expire(RedisConstants.LOGIN_USER_TTL, TimeUnit.HOURS);
        //刷新token有效期
        redissonClient.getMap(SINGLE_POINT_KEY + userDTO.getEmail()).expire(RedisConstants.LOGIN_USER_TTL, TimeUnit.HOURS);
        //5.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
