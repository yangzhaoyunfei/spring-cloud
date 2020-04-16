package com.cxytiandi.demo;

/**
 * 功能类提供相关功能，类似 mongoTemplate
 */
public class UserClient {

	private UserPorperties userPorperties;
	
	public UserClient() {
		
	}
	
	public UserClient(UserPorperties p) {
		this.userPorperties = p;
	}
	
	public String getName() {
		return userPorperties.getName();
	}
	
}
