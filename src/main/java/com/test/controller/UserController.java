package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.User;
import com.test.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
	@ResponseBody 
	public String hello() {
		return "hello";
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public List<User> queryUser(HttpServletRequest request, HttpServletResponse response) {
		return userService.queryUser(null);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request, HttpServletResponse response) {
		userService.addUser(null);
	}
}
