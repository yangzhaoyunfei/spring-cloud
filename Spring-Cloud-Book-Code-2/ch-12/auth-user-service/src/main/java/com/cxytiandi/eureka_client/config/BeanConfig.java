package com.cxytiandi.eureka_client.config;

import com.cxytiandi.auth.filter.HttpBasicAuthorizeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanConfig {

	@Bean
	public FilterRegistrationBean<HttpBasicAuthorizeFilter> filterRegistrationBean() {
		FilterRegistrationBean<HttpBasicAuthorizeFilter> registrationBean = new FilterRegistrationBean<>();
		HttpBasicAuthorizeFilter httpBasicFilter = new HttpBasicAuthorizeFilter();
		registrationBean.setFilter(httpBasicFilter);
		List<String> urlPatterns = new ArrayList<>(1);
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
}
