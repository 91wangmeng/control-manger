package com.drore.cloud.control.manger.common.base.config;

import com.drore.cloud.sdk.basic.CloudBasicConnection;
import com.drore.cloud.sdk.basic.CloudBasicDataSource;
import com.drore.cloud.sdk.basic.CloudPoolingConnectionManager;
import com.drore.cloud.sdk.builder.UcBuilder;
import com.drore.cloud.sdk.builder.impl.UcBuilderImpl;
import com.drore.cloud.sdk.client.CloudQueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/8/28.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@Configuration
public class BaseApplicationConfig {
    private static Logger LOGGER = LoggerFactory.getLogger(BaseApplicationConfig.class);

    @Value("${control.url}")
    private String control_url;
    @Value("${control.appId}")
    private String control_appId;
    @Value("${control.appSecret}")
    private String control_appSecret;


    /**
     * Gets uc builder.
     *
     * @return the uc builder
     */
    @Bean(name = "ucBuilder")
    public UcBuilder getUcBuilder() {
        UcBuilderImpl ucBuilder = new UcBuilderImpl();
        return ucBuilder;
    }

    /**
     * Gets pool task executor.
     *
     * @return the pool task executor
     */
    @Bean(name = "taskExecutor")
    public static ThreadPoolTaskExecutor getPoolTaskExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(5);
        poolTaskExecutor.setKeepAliveSeconds(30000);
        poolTaskExecutor.setMaxPoolSize(1000);
        poolTaskExecutor.setQueueCapacity(200);
        return poolTaskExecutor;
    }

    /**
     * Gets basic connection.
     *
     * @return the basic connection
     */
    @Bean
    public CloudBasicConnection getBasicConnection() {
        return new CloudBasicConnection(control_url, 80, control_appId, control_appSecret);

    }

    /**
     * Gets cloud pooling connection manager.
     *
     * @return the cloud pooling connection manager
     */
    @Bean
    public CloudPoolingConnectionManager getCloudPoolingConnectionManager() {
        CloudPoolingConnectionManager cloudPoolingConnectionManager = new CloudPoolingConnectionManager();
        cloudPoolingConnectionManager.setConnection(getBasicConnection());
        return cloudPoolingConnectionManager;

    }

    /**
     * Gets cloud basic data source.
     *
     * @return the cloud basic data source
     */
    @Bean
    public CloudBasicDataSource getCloudBasicDataSource() {
        CloudBasicDataSource cloudBasicDataSource = new CloudBasicDataSource();
        cloudBasicDataSource.setCloudPoolingConnectionManager(getCloudPoolingConnectionManager());
        return cloudBasicDataSource;

    }

    /**
     * Gets cloud query runner.
     *
     * @return the cloud query runner
     */
    @Bean
    public CloudQueryRunner getCloudQueryRunner() {
        CloudQueryRunner cloudQueryRunner = new CloudQueryRunner();
        cloudQueryRunner.setDataSource(getCloudBasicDataSource());
        return cloudQueryRunner;

    }
}
