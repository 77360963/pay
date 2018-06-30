package com.yunpan.base.redis;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Configuration
//@EnableCaching  
public class RedisConfig {

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(
			RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}

	//@Bean("keyGenerator")
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method,
					Object... params) {
				
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};

	}
	//@Bean
	public CacheManager cacheManager(RedisTemplate<Object, Object>  redisTemplate) {
		RedisCacheManager cm = new RedisCacheManager(redisTemplate);
		//15秒过期
		Map<String,Long> param = new HashMap<String,Long>();
		param.put("usercache1", 20L);
		cm.setExpires(param);
		return cm;
	}
}
