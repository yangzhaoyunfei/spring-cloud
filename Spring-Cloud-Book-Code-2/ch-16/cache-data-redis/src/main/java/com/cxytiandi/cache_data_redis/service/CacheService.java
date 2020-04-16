package com.cxytiandi.cache_data_redis.service;

import java.util.concurrent.TimeUnit;

/**
 * 自定义缓存工具类
 * <p>
 * from: 书中P279
 */
public interface CacheService {
	/**
	 * 设置缓存
	 *
	 * @param key      缓存 KEY
	 * @param value    缓存值
	 * @param timeout  缓存过期时间
	 * @param timeUnit 缓存过期时间单位
	 */
	void setCache(String key, String value, long timeout, TimeUnit timeUnit);

	/**
	 * 获取缓存
	 *
	 * @param key 缓 存 KEY
	 * @return
	 */
	String getCache(String key);

	/**
	 * 缓存未命中时，会执行回调，并把回调结果放入缓存，然后返回回调结果
	 *
	 * @param key
	 * @param closure
	 * @param <V>
	 * @param <K>
	 * @return
	 */
	<V, K> String getCache(K key, Closure<V, K> closure);

	/**
	 * {@link #getCache(K, Closure<V, K> )}.
	 *
	 * @param key
	 * @param closure
	 * @param timeout
	 * @param timeUnit
	 * @param <V>
	 * @param <K>
	 * @return
	 */
	<V, K> String getCache(K key, Closure<V, K> closure, long timeout, TimeUnit timeUnit);

	/**
	 * 删除缓存
	 *
	 * @param key 缓 存 KEY
	 */
	void deleteCache(String key);
}