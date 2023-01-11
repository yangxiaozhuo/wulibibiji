package com.yxz.wulibibiji.config;

import com.yxz.wulibibiji.utils.LoginInterceptor;
import com.yxz.wulibibiji.utils.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author yangxiaozhuo
 * @date 2022/11/19
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
                        "/api", "/api-docs", "/api-docs/**", "/doc.html/**",
                        "/user/sentCode",
                        "/user/create",
                        "/user/login",
                        "/article/**",
                        "/**"
                ).order(1);
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**").order(0);
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

