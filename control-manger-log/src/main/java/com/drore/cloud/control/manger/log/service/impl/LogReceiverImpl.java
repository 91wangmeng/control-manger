package com.drore.cloud.control.manger.log.service.impl;

import com.drore.cloud.control.manger.common.log.entity.HttpRequestLogEntity;
import com.drore.cloud.control.manger.common.log.entity.ServerInvokeLogEntity;
import com.drore.cloud.control.manger.log.service.LogReceiver;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Service;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 22:11 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Service
public class LogReceiverImpl implements LogReceiver {
    @Override
    @RabbitHandler
    public void receiveServerInvokeLog(ServerInvokeLogEntity serverInvokeLogEntity) {
        System.out.println(serverInvokeLogEntity);

    }

    @Override
    @RabbitHandler
    public void receiveHttpRequestLog(HttpRequestLogEntity httpRequestLogEntity) {
        System.out.println(httpRequestLogEntity);

    }
}
