package com.drore.cloud.control.manger.log.service;

import com.drore.cloud.control.manger.common.log.entity.HttpRequestLogEntity;
import com.drore.cloud.control.manger.common.log.entity.ServerInvokeLogEntity;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 21:41 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public interface LogReceiver {

    /**
     * Receive server invoke log.
     *
     * @param serverInvokeLogEntity the server invoke log mongo entity
     */
    @RabbitHandler
    void receiveServerInvokeLog(ServerInvokeLogEntity serverInvokeLogEntity);

    /**
     * Receive http request log.
     *
     * @param httpRequestLogEntity the http request log mongo entity
     */
    @RabbitHandler()
    void receiveHttpRequestLog(HttpRequestLogEntity httpRequestLogEntity);
}
