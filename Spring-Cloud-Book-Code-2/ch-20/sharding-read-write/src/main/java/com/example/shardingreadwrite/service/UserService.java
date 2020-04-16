package com.example.shardingreadwrite.service;

import com.example.shardingreadwrite.po.User;

import java.util.List;

public interface UserService {

	List<User> list();
	
	Long add(User user);
	
}
