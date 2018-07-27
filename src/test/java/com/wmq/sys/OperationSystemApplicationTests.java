package com.wmq.sys;

import com.wmq.sys.redis.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationSystemApplicationTests {
	@Autowired
	private RedisCache redisCache;

	@Test
	public void contextLoads() {
	}

	@Test
	public void rediesTest() {
		redisCache.set("aaa","bbb");
		String str = redisCache.get("aaa");
		System.out.println("-----------------" + str);
	}
}
