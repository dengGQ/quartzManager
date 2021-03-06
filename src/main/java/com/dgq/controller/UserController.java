package com.dgq.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgq.constant.LocalMemory;
import com.dgq.po.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/login")
	public Map<String, Object> login(User user){
		LocalMemory.logingUser.put(user.getUsername(), user);
		Map<String,Object> params = new HashMap<String, Object>();
		LocalMemory.logingUser.put(user.getUsername(), user);
		params.put("success", true);
		params.put("onlineCount", LocalMemory.onlineUsers.size());
		params.put("loginCount", LocalMemory.logingUser.size());
		return params;
	}
}
