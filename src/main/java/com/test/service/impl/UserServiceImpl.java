package com.test.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.dao.UserDao;
import com.test.model.User;
import com.test.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	public List<User> queryUser(Map<String, Object> params) {
		return userDao.queryUser(params);
	}

	public void addUser(Map<String, Object> params) {
		userDao.addUser(params);
	}

}
