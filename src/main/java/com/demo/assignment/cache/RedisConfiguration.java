package com.demo.assignment.cache;


import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;


@Configuration
public class RedisConfiguration {
	
	@Value("${employee.cache.name}")
	private String employeeCache;
	
	@Value("${employee.cache.duration}")
	private int employeeCacheDuration;
	
	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
		return (buider) -> buider.withCacheConfiguration(employeeCache,
				RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(employeeCacheDuration)));

	}
	

}
