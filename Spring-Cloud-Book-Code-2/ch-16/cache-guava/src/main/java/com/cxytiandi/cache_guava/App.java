package com.cxytiandi.cache_guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class App {
	public static void main(String[] args) {
		final PersonDao dao = new PersonDao();

		// 这里注意 loadingCache 与 cache 类的区别
		// loadingCache中值由cache自动加载，cache需要手动load值。
		LoadingCache<String, Person> loadingCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS)
				.build(new CacheLoader<String, Person>() {
					@Override
					public Person load(String key) throws Exception {
						return dao.findById(key);
					}
				});

		try {
			while (true) {
				//缓存中获取不到数据则执行com.google.common.cache.CacheLoader.load()方法
				Person person = loadingCache.get("1");
				System.out.println(person.getName());
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
