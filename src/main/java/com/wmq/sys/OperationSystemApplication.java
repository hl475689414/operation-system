package com.wmq.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事务
public class OperationSystemApplication extends SpringBootServletInitializer {
	/**
	 * 打war包时去掉此代码注释
	 * @param
	 */
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(OperationSystemApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(OperationSystemApplication.class, args);
	}
}
