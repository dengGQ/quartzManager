package com.dgq.quartzMail;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
/*
* @Description: 定时器管理
* @author dgq 
* @date 2018年4月25日
*/
@Component
public class QuartzManager{
	
	private static Logger logger = LoggerFactory.getLogger(QuartzManager.class);
	
	@Autowired
	private SchedulerFactoryBean schedulerFactory;
	
    /**
     * 启动定时器
     */
    public boolean start(){
    	try {
    		schedulerFactory.getScheduler().start();
			return true;
		} catch ( SchedulerException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * 关闭调度器
     * @return
     */
    public boolean shutdown(){
    	try {
    		schedulerFactory.getScheduler().shutdown(true);
			return true;
		} catch (Exception e) {
			logger.info("定时器停止失败................");
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * 新增一个job
     * @param jobName job名称
     * @param jobClass  job类，该类必须继承: org.quartz.job
     * @param cronExpression "0/5 * * ? * *"
     * @throws ClassNotFoundException 
     * @throws SchedulerException 
     */
    public void addJob(String jobName, String jobClassPath, String cronExpression) throws ClassNotFoundException, SchedulerException{
        	
		JobKey jobKey = new JobKey(jobName+"_job", jobName+"_group");
    	
    	JobDetail jobDetail = schedulerFactory.getScheduler().getJobDetail(jobKey);
    	if(!schedulerFactory.getScheduler().checkExists(jobKey)){
    		@SuppressWarnings("unchecked")
			Class<? extends Job> targetJob = (Class<? extends Job>) Class.forName(jobClassPath);
    		jobDetail = JobBuilder
    				.newJob(targetJob)
    				.withIdentity(jobKey)
    				.build();
    		Trigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity(jobName+"_trigger", jobName+"_trigger_group")
    				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
    				.build();
    		
    		schedulerFactory.getScheduler().scheduleJob(jobDetail, trigger);
    	}
		
    }
    
    /** 
     * 暂停定时任务 
     * @param allPushMessage 
     * @throws Exception 
     */  
    public void pauseJob(String jobName) throws Exception {  
        JobKey jobKey = JobKey.jobKey(jobName+"_job", jobName+"_group");  
        try {
        	schedulerFactory.getScheduler().pauseJob(jobKey); 
        } catch (SchedulerException e) {  
        	logger.info("暂停定时任务失败"+e);  
            throw new Exception("暂停定时任务失败");  
        }  
    }  
  
    /** 
     * 恢复任务 
     * @param 
     * @param 
     * @param 
     * @throws Exception 
     */  
    public void resumeJob(String jobName) throws Exception {
  
        JobKey jobKey = JobKey.jobKey(jobName+"_job", jobName+"_group");  
        try {
        	schedulerFactory.getScheduler().resumeJob(jobKey);
        } catch (SchedulerException e) {  
        	logger.info("恢复定时任务失败"+e);  
            throw new Exception("恢复定时任务失败");  
        }  
    }
    
    /**
     * 删除任务
     * @param jobName
     * @throws Exception
     */
    public void deleteJob(String jobName) throws Exception {
    	JobKey jobKey = JobKey.jobKey(jobName+"_job", jobName+"_group"); 
        try {
        	schedulerFactory.getScheduler().deleteJob(jobKey);
        } catch (SchedulerException e) {
        	logger.info("删除定时任务失败"+e);
            throw new Exception("删除定时任务失败");  
        }
    }
    
    /**
     * 修改一个触发器的触发规则cron Expression
     * @param triggerName
     * @param cron
     * @return
     */
    public boolean updateTrigger(String triggerName, String cron){
    	try {
    		CronTrigger oldtrigger = (CronTrigger) schedulerFactory.getScheduler().getTrigger(TriggerKey.triggerKey(triggerName+"_trigger", triggerName+"_trigger_group"));
			TriggerBuilder<CronTrigger> tb = oldtrigger.getTriggerBuilder();
    		Trigger newTrigger = tb.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
    		
    		schedulerFactory.getScheduler().rescheduleJob(oldtrigger.getKey(), newTrigger);
			return true;
		} catch (Exception e) {
			logger.info("修改定触发器失败................");
			e.printStackTrace();
			return false;
		}
    }
}
