package com.dgq.quartzMail;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
	
	@Bean
	public JobDetail sendMailJobDetail() {
		return JobBuilder.newJob(DynamicCreateQuartzJob.class).
				withIdentity("sendMailJob").storeDurably().build();
	}
	
	@Bean
	public Trigger sendMailJobTrigger() {
		return TriggerBuilder.newTrigger().forJob(sendMailJobDetail())
				.withIdentity("sendMailJob")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 10 15 * ?"))
//				.withSchedule(CronScheduleBuilder.cronSchedule("0 35 10 17 * ?"))
				.build();
	}
}
