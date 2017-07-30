package com.drore.cloud.control.manger.task.config;

import com.mongodb.MongoClient;
import com.novemberain.quartz.mongodb.MongoDBJobStore;
import org.quartz.simpl.SimpleThreadPool;
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

    @Bean
    public MongoDBJobStore getMongoDBJobStore(MongoClient mongoClient) {
        JobStoreConfig jobStore = taskProperties.getJobStore();
        MongoDBJobStore mongoDBJobStore = new MongoDBJobStore(mongoClient);
        mongoDBJobStore.setDbName(jobStore.getDbName());
        mongoDBJobStore.setCollectionPrefix(jobStore.getCollectionPrefix());
        mongoDBJobStore.setJobDataAsBase64(jobStore.isJobDataAsBase64());
        mongoDBJobStore.setMisfireThreshold(jobStore.getMisfireThreshold());
        return mongoDBJobStore;
    }

    @Bean
    public SimpleThreadPool getSimpleThreadPool() {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
        ThreadPoolConfig threadPool = taskProperties.getThreadPool();
        simpleThreadPool.setThreadPriority(threadPool.getThreadPriority());
        simpleThreadPool.setThreadNamePrefix(threadPool.getThreadNamePrefix());
        return simpleThreadPool;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setSchedulerName("CMScheduler");
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
