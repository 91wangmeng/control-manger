package com.drore.cloud.control.manger.task.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月29日 14:11 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Configuration
@EnableConfigurationProperties(TaskProperties.class)
public class TaskAutoConfiguration {
    @Resource
    private TaskProperties taskProperties;


    /**
     * Scheduler factory bean scheduler factory bean.
     *
     * @return the scheduler factory bean
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setSchedulerName("CMScheduler");
        Properties properties = new Properties();
        properties.setProperty("org.quartz.threadPool.threadCount", String.valueOf(taskProperties.getThreadPool().getThreadCount()));
        properties.setProperty("org.quartz.threadPool.class", taskProperties.getThreadPool().getClazz());
        properties.setProperty("org.quartz.threadPool.threadNamePrefix", taskProperties.getThreadPool().getThreadNamePrefix());
        properties.setProperty("org.quartz.threadPool.threadPriority", String.valueOf(taskProperties.getThreadPool().getThreadPriority()));
        factory.setQuartzProperties(properties);
        //延时启动
        factory.setStartupDelay(30);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        //可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factory.setAutoStartup(true);

        return factory;
    }
}
