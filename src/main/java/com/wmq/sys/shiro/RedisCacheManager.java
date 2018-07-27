package com.wmq.sys.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by 李怀鹏 on 2018/6/23.
 * 实现shiro 的CacheManager
 */
@Component
public class RedisCacheManager implements CacheManager {
    @Value("${shiro_session_redis_overdueTime}")
    private int shiro_session_redis_overdueTime;
    @Value("${shiro_redis_prefix}")
    private String shiro_redis_prefix;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroCache<K, V>(name, redisTemplate, shiro_redis_prefix, shiro_session_redis_overdueTime);
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
