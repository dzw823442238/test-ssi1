package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.model.User;

public interface UserService {
	/**
	 * 查询用户
	 * 
	 * @param params
	 * @return
	 */
	public List<User> queryUser(Map<String, Object> params);

	/**
	 * 新增用户
	 * 
	 * @param params
	 */
	public void addUser(Map<String, Object> params);
}
