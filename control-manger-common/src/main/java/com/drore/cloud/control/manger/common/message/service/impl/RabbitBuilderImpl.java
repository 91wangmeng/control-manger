package com.drore.cloud.control.manger.common.message.service.impl;

import com.drore.cloud.control.manger.common.message.constant.RabbitConstant;
import com.drore.cloud.control.manger.common.message.service.RabbitBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 21:04 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Component
public class RabbitBuilderImpl implements RabbitBuilder {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendDirect(String routingKey, Object object) {
        rabbitTemplate.convertAndSend(routingKey, object);
    }

    @Override
    public void sendServerInvokeLog(Object object) {
        rabbitTemplate.convertAndSend(RabbitConstant.SERVER_INVOKE_LOG_ROUTINGKEY, object);
    }

    @Override
    public void sendHttpRequestLog(Object object) {
        rabbitTemplate.convertAndSend(RabbitConstant.HTTP_REQUEST_LOG_ROUTINGKEY, object);
    }
}
