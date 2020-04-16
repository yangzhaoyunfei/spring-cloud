package com.cxytiandi.cache_data_redis.service;

/**
 * 闭包（回调接口）
 *
 * @param <O>
 * @param <I>
 */
public interface Closure<O, I> {
	O execute(I input);
}