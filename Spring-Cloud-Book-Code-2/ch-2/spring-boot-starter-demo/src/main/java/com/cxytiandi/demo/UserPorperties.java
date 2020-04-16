package com.cxytiandi.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;
//配置属性类，保存需要的配置属性
@Data
@ConfigurationProperties("spring.user")
public class UserPorperties {

	private String name;
	
}
