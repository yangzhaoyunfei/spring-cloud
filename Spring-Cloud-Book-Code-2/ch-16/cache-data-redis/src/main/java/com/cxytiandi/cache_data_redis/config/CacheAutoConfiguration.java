package com.cxytiandi.cache_data_redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheAutoConfiguration extends CachingConfigurerSupport {
	private Logger logger = LoggerFactory.getLogger(CacheAutoConfiguration.class);

	/**
	 * redis数据操作异常处理
	 * <p>
	 * 在默认配置里面，然会的没有 CacheErrorHandler 这个bean，返回的是null，所以缓存操作失败是会抛出异常，导致整个该次请求失败。
	 * 这里的处理：在日志中打印出错误信息，但是放行
	 * 保证redis服务器出现连接等问题的时候不影响程序的正常运行，使得在出问题时不用缓存,可以继续执行业务逻辑去查询DB
	 *
	 * @return
	 */
	@Bean
	@Override
	public CacheErrorHandler errorHandler() {
		return new CacheErrorHandler() {
			@Override
			public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
				logger.error("redis异常：key=[{}]", key, e);
			}

			@Override
			public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
				logger.error("redis异常：key=[{}]", key, e);
			}

			@Override
			public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
				logger.error("redis异常：key=[{}]", key, e);
			}

			@Override
			public void handleCacheClearError(RuntimeException e, Cache cache) {
				logger.error("redis异常：", e);
			}
		};
	}
}
