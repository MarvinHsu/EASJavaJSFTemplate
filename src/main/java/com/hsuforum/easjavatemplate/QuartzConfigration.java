package com.hsuforum.easjavatemplate;

import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.hsuforum.easjavatemplate.job.SampleCornJob;
import com.hsuforum.easjavatemplate.job.SampleJob;
import com.hsuforum.eass.entity.schedule.JobSystem;

@Configuration
public class QuartzConfigration {
	@Bean
	JobSystem jobSystem() {
		JobSystem jobSystem= JobSystem.EAS;
		return jobSystem;
	}
	@Bean
	JobDetailFactoryBean sampleJobDetail() {
        
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(SampleJob.class);
        jobDetailFactory.setDescription("Invoke Sample Job");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }
	@Bean
	SimpleTriggerFactoryBean sampleJobTrigger(JobDetail sampleJobDetail) {
		
		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setName("sampleJobTrigger");
		trigger.setJobDetail(sampleJobDetail);
		trigger.setStartDelay(5);
		trigger.setRepeatInterval(5000);
		trigger.afterPropertiesSet();
		return trigger;
    }
	@Bean
	JobDetailFactoryBean sampleCornJobDetail() {
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setJobClass(SampleCornJob.class);
		jobDetail.setDescription("Invoke Sample Corn Job");
		jobDetail.setDurability(true);
        return jobDetail;
        
    }
	@Bean
	CronTriggerFactoryBean sampleCornJobTrigger(JobDetail sampleCornJobDetail) {
		//1 minute run once
		String cron = "0 0/1 * * * ?";
        
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(sampleCornJobDetail);  
        trigger.setCronExpression(cron);
        return trigger;
    }
}
