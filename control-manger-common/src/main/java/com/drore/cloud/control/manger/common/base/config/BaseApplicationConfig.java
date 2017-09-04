package com.drore.cloud.control.manger.common.base.config;

import com.drore.cloud.control.manger.common.cache.utils.FastJsonRedisSerializer;
import com.drore.cloud.sdk.basic.CloudBasicConnection;
import com.drore.cloud.sdk.basic.CloudBasicDataSource;
import com.drore.cloud.sdk.basic.CloudPoolingConnectionManager;
import com.drore.cloud.sdk.builder.UcBuilder;
import com.drore.cloud.sdk.builder.impl.UcBuilderImpl;
import com.drore.cloud.sdk.client.CloudQueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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
@RefreshScope
@ConditionalOnExpression("'${spring.application.name}'!='control-manger'")
public class BaseApplicationConfig {
    private static Logger LOGGER = LoggerFactory.getLogger(BaseApplicationConfig.class);

    @Value("${cloud_host}")
    private String cloud_host;
    @Value("${control.appId}")
    private String control_appId;
    @Value("${control.appSecret}")
    private String control_appSecret;
    @Value("${config.appId}")
    private String config_appId;
    @Value("${config.appSecret}")
    private String config_appSecret;
    @Value("${gis.appId}")
    private String gis_appId;
    @Value("${gis.appSecret}")
    private String gis_appSecret;

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
     * Gets cloud query runner.
     *
     * @return the cloud query runner
     */
    @Bean(name = "cloudQueryRunner")
    @Primary
    public CloudQueryRunner getCloudQueryRunner() {
        return getQueryRunner(cloud_host, config_appId, control_appSecret);

    }

    private static CloudQueryRunner getQueryRunner(String cloud_host, String config_appId, String control_appSecret) {
        CloudBasicConnection cloudBasicConnection = new CloudBasicConnection(cloud_host, 80, config_appId, control_appSecret);

        CloudPoolingConnectionManager cloudPoolingConnectionManager = new CloudPoolingConnectionManager();
        cloudPoolingConnectionManager.setConnection(cloudBasicConnection);

        CloudBasicDataSource cloudBasicDataSource = new CloudBasicDataSource();
        cloudBasicDataSource.setCloudPoolingConnectionManager(cloudPoolingConnectionManager);

        CloudQueryRunner cloudQueryRunner = new CloudQueryRunner();
        cloudQueryRunner.setDataSource(cloudBasicDataSource);
        return cloudQueryRunner;
    }

    /**
     * Gets tenant runner.
     *
     * @return the tenant runner
     */
    @Bean(name = "tenantRunner")
    public CloudQueryRunner getTenantRunner() {
        return getQueryRunner(cloud_host, config_appId, config_appSecret);

    }

    /**
     * Gets gis runner.
     *
     * @return the gis runner
     */
    @Bean(name = "gisRunner")
    public CloudQueryRunner getGisRunner() {
        return getQueryRunner(cloud_host, gis_appId, gis_appSecret);

    }


    /**
     * Gets redis template.
     *
     * @param jedisConnectionFactory the jedis connection factory
     * @return the redis template
     */
    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    /**
     * Ge cache manager redis cache manager.
     *
     * @param redisTemplate the redis template
     * @return the redis cache manager
     */
    @Bean
    public RedisCacheManager geCacheManager(RedisTemplate<String, Object> redisTemplate) {

        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);

        return redisCacheManager;
    }
}
