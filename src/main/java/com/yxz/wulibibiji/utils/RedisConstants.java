package com.yxz.wulibibiji.utils;

/**
 * rediskey类
 *
 * @author yangxiaozhuo
 * @date 2022/11/18
 */
public class RedisConstants {
    /**
     * 用户注册用验证码的key
     */
    public static final String CREATE_CODE_KEY = "user:create:code:";
    /**
     * 用户注册时验证码过期时间
     */
    public static final long CREATECODE_TTL = 30L;
    /**
     * 用户登录token的key
     */
    public static final String LOGIN_USER_KEY = "user:login:token:";
    /**
     * 用户登录token的key的过期时间，单位小时
     */
    public static final Long LOGIN_USER_TTL = 1L;
    /**
     * 用户给文章点赞
     */
    public static final String ARTICLE_LIKED_KEY = "article:liked:";
    /**
     * 热门文章缓存key
     */
    public static final String CACHE_ARTICLE_HOT_KEY = "article:cache:hot:";
    /**
     * 热门文章缓存key的过期时间，单位分钟
     */
    public static final Long CACHE_ARTICLE_HOT_TTL = 30L;
    /**
     * 最新文章缓存key
     */
    public static final String CACHE_ARTICLE_NEW_KEY = "article:cache:new:";
    /**
     * 文章分类名的key
     */
    public static final String ARTICLE_CATEGORY_NAME = "article:category:name:";
}
