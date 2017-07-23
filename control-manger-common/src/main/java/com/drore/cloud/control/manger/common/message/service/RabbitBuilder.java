package com.drore.cloud.control.manger.common.message.service;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 21:04 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public interface RabbitBuilder {
    /**
     * Send direct.
     *
     * @param routingKey the routing key
     * @param object     the object
     */
    void sendDirect(String routingKey, Object object);

    /**
     * Send log.
     *
     * @param object the object
     */
    void sendServerInvokeLog(Object object);

    void sendHttpRequestLog(Object object);
}
