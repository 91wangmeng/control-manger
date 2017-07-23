package com.drore.cloud.control.manger.message.config;

import com.drore.cloud.control.manger.common.message.constant.RabbitConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 22:31 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Configuration
public class RabbitConfig {
    /**
     * Server invoke queue queue.
     *
     * @return the queue
     */
    @Bean
    public Queue serverInvokeQueue() {
        return new Queue(RabbitConstant.SERVER_INVOKE_LOG_ROUTINGKEY);
    }

    /**
     * Http request queue queue.
     *
     * @return the queue
     */
    @Bean
    public Queue httpRequestQueue() {
        return new Queue(RabbitConstant.HTTP_REQUEST_LOG_ROUTINGKEY);
    }
}
