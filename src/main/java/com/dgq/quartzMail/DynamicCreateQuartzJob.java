package com.dgq.quartzMail;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class DynamicCreateQuartzJob extends QuartzJobBean{
	
	private static Logger logger = LoggerFactory.getLogger(DynamicCreateQuartzJob.class);
	
	@Autowired
	private QuartzManager quartzManager;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			logger.info("--------------DynamicCreateQuartzJob");
			LocalDate now = LocalDate.now();
			if(now.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
				quartzManager.addJob("sendMail", "com.dgq.quartzMail.SendMailJob", buildCronExpression(LocalDate.now().plusDays(2)));
			}else if(now.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
				quartzManager.addJob("sendMail", "com.dgq.quartzMail.SendMailJob", buildCronExpression(LocalDate.now().plusDays(1)));
			}else {
				quartzManager.addJob("sendMail", "com.dgq.quartzMail.SendMailJob", buildCronExpression(LocalDate.now()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String buildCronExpression(LocalDate startDate) {
		LocalDate date = next2WorkDays(startDate);
		String cronExpression = "0 0 10 "+date.getDayOfMonth()+" "+date.getMonth().getValue()+" ?";
//		String cronExpression = "0 37 10 17 1 ?";
		logger.info(cronExpression);
		return cronExpression;
	}
	
	/**
	 * 计算指定时间date后num个工作日的日期
	 * @param date
	 * @param num
	 * @return
	 */
	public static LocalDate nextWorkDays(LocalDate date, int num) {
		
		int days = 0;
		LocalDate plusDays = date;
		while (days < num) {
			plusDays = plusDays.plusDays(1);
			DayOfWeek week = plusDays.getDayOfWeek();
			if(!week.equals(DayOfWeek.SATURDAY) && !week.equals(DayOfWeek.SUNDAY)) {
				days++;
			}
		}
		return plusDays;
	}
	
	public static LocalDate next2WorkDays(LocalDate date) {
		TemporalAdjuster next = TemporalAdjusters.ofDateAdjuster(
				tdate->{
					DayOfWeek work = tdate.getDayOfWeek();
					int addDays=0;
					if(work.equals(DayOfWeek.FRIDAY)) {
						addDays=4;
					}else if(work.equals(DayOfWeek.THURSDAY)) {
						addDays=4;
					}else if(work.equals(DayOfWeek.SATURDAY)){
						addDays=3;
					}else {
						addDays=2;
					}
					return date.plusDays(addDays);
				}
			);
		return date.with(next);
	}
	public static void main(String[] args) {
		/*LocalDate date = next2WorkDays(LocalDate.now().minus(2, ChronoUnit.DAYS));
		int dayOfMonth = date.getDayOfMonth();
		int month = date.getMonth().getValue();
		System.out.println("0 45 14 "+dayOfMonth+" "+month+" ?");*/
		/*LocalDate now = LocalDate.now();
		System.out.println(LocalDate.ofEpochDay(04));
		if(now.getDayOfMonth() == 3) {
			System.out.println("----------");
		}
	
		/*System.out.println(LocalDate.now());
		System.out.println(LocalDate.now().plusDays(2));
		String str = buildCronExpression(LocalDate.now());
		String str1 = buildCronExpression(LocalDate.now().plusDays(2));
		
		System.out.println(str);
		System.out.println(str1);*/
		
		LocalDate date = next2WorkDays(LocalDate.now());
		LocalDate localDate = nextWorkDays(LocalDate.now() , 10);
		System.out.println(localDate);
		System.out.println(date);
		System.out.println(13/7);
		System.out.println(13%7);
	}
}
