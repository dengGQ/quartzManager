package com.dgq.quartzMail;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class SendMailJob extends QuartzJobBean{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		SimpleMailMessage message = new SimpleMailMessage();
		System.out.println("------------send mail start....");
		message.setFrom("denggqa@litsoft.com.cn");
		message.setTo("xubina@litsoft.com.cn");
		message.setSubject("gzt");
		message.setText("员工编号：5346\r\n" + 
				"员工姓名：邓国泉\r\n" + 
				"所在事业部：ERP事业部\r\n" + 
				"所在办公工区：中化业务处\r\n" + 
				"联系方式：18611312771");
		mailSender.send(message);
		System.out.println("------------send mail end....");
	}
}
