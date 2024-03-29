package com.yxz.wulibibiji.config;

import com.yxz.wulibibiji.utils.LoginInterceptor;
import com.yxz.wulibibiji.utils.RefreshTokenInterceptor;
import com.yxz.wulibibiji.utils.UVInterceptor;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yangxiaozhuo
 * @date 2022/11/19
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    RedissonClient redissonClient;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
                        "/api", "/api-docs", "/api-docs/**", "/doc.html/**",
                        "/user/sentCode",
                        "/user/create",
                        "/user/login",
                        "/user/loginPlus",
                        "/user/query/**",
                        "/user/isLogin",
                        "/uv/**",
                        "/sonComment/getList",
                        "/sonComment/detail/**",
                        "/follow/or/not/**",
                        "/firstComment/getNewList",
                        "/firstComment/getHotList",
                        "/firstComment/detail/**",
                        "/category/**",
                        "/article/new",
                        "/article/hot",
                        "/article/detail/**",
                        "/article/all",
                        "/article/search"
                ).order(1);
        registry.addInterceptor(new RefreshTokenInterceptor(redissonClient)).addPathPatterns("/**").order(0);
        registry.addInterceptor(new UVInterceptor(redissonClient)).addPathPatterns("/article/new").order(0);
    }

    // 请求跨域
    @Configuration
    public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            //添加映射路径
            registry.addMapping("/**")
                    //是否发送Cookie
                    .allowCredentials(true)
                    //设置放行哪些原始域   SpringBoot2.4.4下低版本使用.allowedOrigins("*")
                    .allowedOrigins("*")
                    //放行哪些请求方式
                    .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                    //.allowedMethods("*") //或者放行全部
                    //放行哪些原始请求头部信息
                    .allowedHeaders("*")
                    //暴露哪些原始请求头部信息
                    .exposedHeaders("*");
        }
    }
}

