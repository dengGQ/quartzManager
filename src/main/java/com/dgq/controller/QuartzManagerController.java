package com.dgq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cors")
public class QuartzManagerController {
	
	@GetMapping("/getUser")
	@ResponseBody
	public String getUser(String name) {
		return "GET: hello "+name;
	}
	
	@PostMapping("/postUser")
	@ResponseBody
	public String postUser(String name) {
		
		return "POST: hello "+name;
	}
	
}
