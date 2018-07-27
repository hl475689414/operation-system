package com.wmq.sys.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by 李怀鹏 on 2018/6/23.
 * redis实现共享session
 */
@Component
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {
    //缓存过期时间
    @Value("${shiro_session_redis_overdueTime}")
    private int shiro_session_redis_overdueTime;
    //缓存key前缀
    @Value("${shiro_redis_prefix}")
    private String shiro_redis_prefix;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 创建session，保存到redis
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisTemplate.opsForValue().set(shiro_redis_prefix + sessionId.toString(), session); //将sessionId放入缓存
        return sessionId;
    }

    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去redis
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) redisTemplate.opsForValue().get(shiro_redis_prefix + sessionId.toString());
        }
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        String key = shiro_redis_prefix + session.getId().toString();
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, session);
        }
        redisTemplate.expire(key, shiro_session_redis_overdueTime, TimeUnit.SECONDS);
    }

    // 删除session
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        redisTemplate.delete(shiro_redis_prefix + session.getId().toString());
    }
}
