package com.cxytiandi.auth.service;

import org.springframework.stereotype.Service;

import com.cxytiandi.auth.po.User;
import com.cxytiandi.auth.query.AuthQuery;

@Service
public class AuthServiceImpl implements AuthService {

	@Override
	public User auth(AuthQuery query) {
		//从认证表查询，如果存在则返回一个user，
		// 为了方便，这里省略了
		return new User(1L);
	}

}
