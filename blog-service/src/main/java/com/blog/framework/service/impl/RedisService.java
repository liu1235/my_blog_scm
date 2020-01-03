package com.blog.framework.service.impl;

import cn.hutool.core.map.MapUtil;
import com.blog.framework.common.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * redis 操作类
 *
 * @author liuzw
 */
@Component
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 将Object类型value存入redis
     *
     * @param key key
     * @param obj value
     */
    public void set(String key, Object obj) {
        if (StringUtils.isNotEmpty(key) && obj != null) {
            getValueOperations().set(key, JsonUtil.toJson(obj));
        }
    }

    /**
     * 将Object类型value存入redis
     *
     * @param key        key
     * @param obj        value
     * @param expireTime expireTime 过期时间
     * @param unit       unit 时间粒度
     */
    public void set(String key, Object obj, long expireTime, TimeUnit unit) {
        if (StringUtils.isNotEmpty(key) && obj != null) {
            getValueOperations().set(key, JsonUtil.toJson(obj), expireTime, unit);
        }
    }

    /**
     * 设置key的有效期
     *
     * @param key        key
     * @param expireTime 有效时长
     * @param unit       单位
     */
    public void expire(String key, long expireTime, TimeUnit unit) {
        stringRedisTemplate.expire(key, expireTime, unit);
    }

    /**
     * 从redis中获取
     *
     * @param key key
     * @return 结果
     */
    public String get(String key) {
        return getValueOperations().get(key);
    }

    /**
     * 从redis中获取对象
     *
     * @param key key
     * @param clz 目标对象
     * @return 结果
     */
    public <T> T get(String key, Class<T> clz) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return JsonUtil.toBean(get(key), clz);
    }

    /**
     * 存放hash
     *
     * @param hashKey hashKey
     * @param key     key
     * @param value   value
     */
    public void setHash(String key, String hashKey, Object value) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(hashKey)) {
            getHashOperations().put(key, hashKey, JsonUtil.toJson(value));
        }
    }


    /**
     * 存放hash
     *
     * @param key   key
     * @param value value
     */
    public void setHash(String key, Map<String, ?> value) {
        if (StringUtils.isNotEmpty(key) && MapUtil.isNotEmpty(value)) {
            Map<String, String> map = value.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, v -> JsonUtil.toJson(v.getValue())));
            getHashOperations().putAll(key, map);
        }
    }

    /**
     * 从redis中获取对象
     *
     * @param key key
     * @return 结果
     */
    public Map<String, String> getHash(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return getHashOperations().entries(key);
    }

    /**
     * 从redis中获取对象
     *
     * @param hashKey hashKey
     * @param key     key
     * @param clz     目标对象
     * @return 结果
     */
    public <T> T getHash(String key, String hashKey, Class<T> clz) {
        if (StringUtils.isBlank(key) && StringUtils.isBlank(hashKey)) {
            return null;
        }
        return JsonUtil.toBean(getHash(key, hashKey), clz);
    }

    /**
     * 从redis中获取对象
     *
     * @param hashKey hashKey
     * @param key     key
     * @return 结果
     */
    public String getHash(String key, String hashKey) {
        if (StringUtils.isBlank(key) && StringUtils.isBlank(hashKey)) {
            return null;
        }
        return getHashOperations().get(key, hashKey);
    }

    /**
     * 删除key
     *
     * @param key key
     */
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key key
     * @return 结果
     */
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * redis操作字符串
     *
     * @return ValueOperations
     */
    private ValueOperations<String, String> getValueOperations() {
        return stringRedisTemplate.opsForValue();
    }

    /**
     * redis操作hash
     *
     * @return HashOperations
     */
    private HashOperations<String, String, String> getHashOperations() {
        return stringRedisTemplate.opsForHash();
    }
}
