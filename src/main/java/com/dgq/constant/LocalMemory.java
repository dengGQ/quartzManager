package com.dgq.constant;

import java.util.concurrent.ConcurrentHashMap;

import com.dgq.controller.WebSocketController;
import com.dgq.po.User;

public class LocalMemory {
	
	public static ConcurrentHashMap<String, WebSocketController> onlineUsers 
		= new ConcurrentHashMap<>();

	public static ConcurrentHashMap<String, User> logingUser = new ConcurrentHashMap<>();
	
}
