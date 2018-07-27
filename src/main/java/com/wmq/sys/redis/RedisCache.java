package com.wmq.sys.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCache {

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 设置缓存键值对
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value);
	}

	/**
	 * 设置缓存键值对 -- Object
	 * @param key
	 * @param value
	 */
	public void setObject(String key, Object value) {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value);
	}

	/**
	 * 超时设置
	 * @param key 键
	 * @param value 值
	 * @param time 时间值
	 * @param timeUnit 时间单位
	 * TimeUnit.DAYS          //天
	 * TimeUnit.HOURS         //小时
	 * TimeUnit.MINUTES       //分钟
	 * TimeUnit.SECONDS       //秒
	 * TimeUnit.MILLISECONDS  //毫秒
	 * TimeUnit.NANOSECONDS   //毫微秒
	 * TimeUnit.MICROSECONDS  //微秒
	 */
	public void setex(String key, String value, long time, TimeUnit timeUnit ) {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value, time, timeUnit);
	}

	/**
	 * 根据键拿缓存值
	 * @param key
	 * @return
	 */
	public String get(String key) {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		return opsForValue.get(key);
	}

	/**
	 * 根据键拿缓存值
	 * @param key
	 * @return
	 */
	public Object getObject(String key) {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		return opsForValue.get(key);
	}

	/**
	 * 检查 key 是否存在
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}

    /**
     * 自增
     * @param key
     * @return
     */
	public long increment(String key) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
	    return opsForValue.increment(key, 1);
    }

	/**
	 * 删除
	 * @param key
	 */
	public void evict(Object key) {
		final String keyf = (String) key;
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.del(keyf.getBytes());
			}
		});
	}
}