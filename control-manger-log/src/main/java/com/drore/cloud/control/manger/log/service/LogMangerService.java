package com.drore.cloud.control.manger.log.service;

import com.drore.cloud.control.manger.log.domain.HttpRequestLogMongoEntity;
import com.drore.cloud.control.manger.log.domain.ServerInvokeLogMongoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月24日 00:08 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public interface LogMangerService {
    /**
     * Gets list.
     *
     * @param pageable the pageable
     * @return the list
     */
    Page<ServerInvokeLogMongoEntity> getList(Pageable pageable);

    /**
     * Gets list.
     *
     * @param id       the id
     * @param pageable the pageable
     * @return the list
     */
    Page<HttpRequestLogMongoEntity> getList(String id, Pageable pageable);


}
