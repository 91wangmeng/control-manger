package com.drore.cloud.control.manger;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/7/20.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaServer
@EnableConfigServer
@EnableRabbit
@EnableMongoRepositories
@EnableScheduling
@EnableConfigurationProperties
@EnableTransactionManagement
public class ControlMangerWebApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(ControlMangerWebApplication.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        LOGGER.debug("系统启动时间:{}", DateFormatUtils.format(new Date(), DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
        SpringApplication.run(ControlMangerWebApplication.class);
    }
}
