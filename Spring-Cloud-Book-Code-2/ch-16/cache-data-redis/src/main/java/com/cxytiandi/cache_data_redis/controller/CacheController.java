package com.cxytiandi.cache_data_redis.controller;

import com.cxytiandi.cache_data_redis.po.Person;
import com.cxytiandi.cache_data_redis.repository.PersonRepository;
import com.cxytiandi.cache_data_redis.service.CacheService;
import com.cxytiandi.cache_data_redis.service.Closure;
import com.cxytiandi.cache_data_redis.service.PersonService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class CacheController {

	@Autowired
	private PersonRepository repo;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private PersonService personService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private RedissonClient redissonClient;

	@GetMapping("/lock")
	public String lock() {
		RLock lock = redissonClient.getLock("anyLock");
		lock.lock(10, TimeUnit.SECONDS);
		System.out.println("-----");
		lock.unlock();
		return "success";
	}


	/**
	 * 通过 repository 操作数据
	 */
	@GetMapping("/test2")
	public void basicCrudOperations() {
		Person person = new Person();
		person.setFirstname(" 尹吉欢 ");
		person.setLastname("yinjihuan");
		repo.save(person);
		Optional<Person> personObj = repo.findById(person.getId());
		System.err.println(personObj.get().getFirstname());
		System.err.println(repo.count());
		repo.delete(person);
	}

	/**
	 * 通过 RedisTemplate 操作数据
	 *
	 * @return
	 */
	@GetMapping("/test")
	public String test() {
		stringRedisTemplate.opsForValue().set("key", " 猿天地 ", 1, TimeUnit.HOURS);
		String value = stringRedisTemplate.opsForValue().get("key");
		System.out.println(value);
		stringRedisTemplate.delete("key");
		boolean exists = stringRedisTemplate.hasKey("key");
		System.out.println(exists);
		// 获取连接，手动操作
		RedisConnection connection = stringRedisTemplate.getConnectionFactory().getConnection();
		connection.set("name".getBytes(), "yinjihuan".getBytes());
		System.out.println(new String(connection.get("name".getBytes())));
		return "success";
	}

	/**
	 * 通过 spring cache annotation 操作缓存
	 *
	 * @return
	 */
	@GetMapping("/get")
	public Person get() {
		String id = String.valueOf(new Random().nextInt(1000));
		return personService.get(id);
	}

	@GetMapping("/getCallback")
	public String getCallback() {
		String cacheKey = "1001";
		return cacheService.getCache(cacheKey, new Closure<String, String>() {
			@Override
			public String execute(String id) {
				// 执行你的业务逻辑
				return id + "hello";
			}
		});
	}
}
