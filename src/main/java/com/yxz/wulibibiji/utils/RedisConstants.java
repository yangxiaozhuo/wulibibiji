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
    public static final Long LOGIN_USER_TTL = 48L;
    /**
     * 用户给文章点赞
     */
    public static final String ARTICLE_LIKED_KEY = "article:liked:";
    /**
     * 最新文章缓存key
     */
    public static final String CACHE_ARTICLE_NEW_KEY = "article:cache:new";
    /**
     * 文章分类名存为list的key
     */
    public static final String ARTICLE_CATEGORY_LIST = "category:cache:list";
    /**
     * 文章分类名存为map的key
     */
    public static final String ARTICLE_CATEGORY_MAP = "category:cache:map";

    /**
     * 文章发布时所存的id，用于上传图片使用
     */
    public static final String ARTICLE_UPLOAD_IMG_ID = "category:upload:img:id:";
    /**
     * 文章发布时所存的id，用于上传图片使用过期时间  1分钟
     */
    public static final Long ARTICLE_UPLOAD_IMG_ID_TTL = 30L;

    /**
     * 用户给一级评论点赞
     */
    public static final String FIRST_COMMENT_LIKED_KEY = "first:comment:liked:";

    /**
     * 用户给二级评论点赞
     */
    public static final String SON_COMMENT_LIKED_KEY = "son:comment:liked:";

    /**
     * 系统日访问量
     */
    public static final String SYSTEM_DAY_VISIT = "system:day:visit:";

    /**
     * 系统总访问量
     */
    public static final String SYSTEM_ALL_VISIT = "system:all:visit";
}
