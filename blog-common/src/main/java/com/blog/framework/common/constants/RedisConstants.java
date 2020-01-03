
package com.blog.framework.common.constants;

/**
 * redis 常量
 *
 * @author liuzw
 */
public class RedisConstants {

    private RedisConstants(){}

    /**
     * TOKEN redis token 前缀
     */
    public static final String REDIS_BLOG_TOKEN = "redis:blog:token:";

    /**
     * 验证码 redis key 前缀
     */
    public static final String CAPTCHA = "redis:blog:captcha:";


    /**
     *  redis blog key
     */
    public static final String REDIS_BLOG_LIST = "redis:blog:list";

    /**
     *  redis 喜欢操作 key
     */
    public static final String REDIS_BLOG_LIKE = "redis:blog:operating:like:";

    /**
     *  redis 点赞操作 key
     */
    public static final String REDIS_BLOG_COLLECT = "redis:blog:operating:collect:";




}
