package com.drore.cloud.control.manger;

import com.drore.cloud.control.manger.common.base.utils.ControlDateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/7/20.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@SpringBootApplication
//开启注册发现客户端
@EnableDiscoveryClient
//开启eureka服务器
@EnableEurekaServer
//开启rabbitmq
@EnableRabbit
//开启mongodb自动装配
@EnableMongoRepositories
//开启自动注入配置文件
@EnableConfigurationProperties

@EnableSwagger2

public class ControlMangerWebApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(ControlMangerWebApplication.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        LOGGER.debug("系统启动时间:{}", ControlDateUtils.format(LocalDateTime.now()));
        SpringApplication.run(ControlMangerWebApplication.class);
    }
}
