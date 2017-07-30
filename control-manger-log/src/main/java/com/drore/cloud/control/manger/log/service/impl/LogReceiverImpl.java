package com.drore.cloud.control.manger.log.service.impl;

import com.drore.cloud.control.manger.common.log.entity.HttpRequestLogEntity;
import com.drore.cloud.control.manger.common.log.entity.ServerInvokeLogEntity;
import com.drore.cloud.control.manger.common.message.constant.RabbitConstant;
import com.drore.cloud.control.manger.log.dao.HttpRequestInvokeRepository;
import com.drore.cloud.control.manger.log.dao.ServerInvokeRepository;
import com.drore.cloud.control.manger.log.domain.HttpRequestLogMongoEntity;
import com.drore.cloud.control.manger.log.domain.ServerInvokeLogMongoEntity;
import com.drore.cloud.control.manger.log.service.LogReceiver;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 22:11 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Service
@RabbitListener(queues = {RabbitConstant.HTTP_REQUEST_LOG_ROUTINGKEY, RabbitConstant.SERVER_INVOKE_LOG_ROUTINGKEY})
public class LogReceiverImpl implements LogReceiver {
    @Resource
    private HttpRequestInvokeRepository httpRequestInvokeRepository;
    @Resource
    private ServerInvokeRepository serverInvokeRepository;

    @Override
    @RabbitHandler
    public void receiveServerInvokeLog(ServerInvokeLogEntity serverInvokeLogEntity) {
        serverInvokeRepository.save(new ServerInvokeLogMongoEntity(serverInvokeLogEntity));

    }

    @Override
    @RabbitHandler()
    public void receiveHttpRequestLog(HttpRequestLogEntity httpRequestLogEntity) {
        httpRequestInvokeRepository.save(new HttpRequestLogMongoEntity(httpRequestLogEntity));

    }
}
