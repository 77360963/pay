package com.yunpan.base.cache.impl;

import com.yunpan.base.cache.IRedisCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

//@Component
public class RedisCache implements IRedisCache {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

/*	@Override
	public <T> T query(String module,String key,long timeout,TimeUnit unit,CacheCallBack<T> cb) {
		BoundHashOperations<String, String, T> opt =	redisTemplate.boundHashOps(module);
		T result = opt.get(opt);
		if(result!=null){
			return result;
		}else{
			T t = cb.action();
			opt.put(key, t);
			opt.expire(timeout, unit);
			return t;
		}
	}*/

}
