package com.dgq.quartzMail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzMailApplicationTests {
	
	@Autowired
	private JavaMailSender mailSender;
	
//	@Test
	public void contextLoads() {
		SimpleMailMessage message = new SimpleMailMessage();
		System.out.println("------------send mail start....");
		message.setFrom("denggqa@litsoft.com.cn");
		message.setTo("565820745@qq.com");
		message.setSubject("gzt");
		message.setText("员工编号：5346\r\n" + 
				"员工姓名：邓国泉\r\n" + 
				"所在事业部：ERP事业部\r\n" + 
				"所在办公工区：中化业务处\r\n" + 
				"联系方式：18611312771");
		mailSender.send(message);
		System.out.println("------------send mail end....");
	}
	
	@Test
	public void test() {
		
	}
}
