package com.cxytiandi.cache_data_redis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cxytiandi.cache_data_redis.po.Person;

/**
 * spring cache demo
 */
@Service
public class PersonServiceImpl implements PersonService {
	
	@Override
//	@Cacheable(value = "get", key = "#id")
	@Cacheable(value = "get", keyGenerator = "keyGenerator")
	public Person get(String id) {
		Person p = new Person();
		p.setFirstname("xxx");
		p.setLastname("bbb");
		p.setId(id);
		return p;
	}
	
}
