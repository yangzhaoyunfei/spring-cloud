package com.cxytiandi.spring_boot_example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "com.cxytiandi")
@Configuration
@Data
public class MyConfig {

	private String name;

}
