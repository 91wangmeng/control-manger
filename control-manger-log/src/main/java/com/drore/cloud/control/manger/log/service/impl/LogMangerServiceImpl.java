package com.drore.cloud.control.manger.log.service.impl;

import com.drore.cloud.control.manger.log.dao.HttpRequestInvokeRepository;
import com.drore.cloud.control.manger.log.dao.ServerInvokeRepository;
import com.drore.cloud.control.manger.log.domain.HttpRequestLogMongoEntity;
import com.drore.cloud.control.manger.log.domain.ServerInvokeLogMongoEntity;
import com.drore.cloud.control.manger.log.service.LogMangerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月24日 00:09 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Service
public class LogMangerServiceImpl implements LogMangerService {
    @Resource
    private ServerInvokeRepository serverInvokeRepository;
    @Resource
    private HttpRequestInvokeRepository httpRequestInvokeRepository;

    @Override

    public Page<ServerInvokeLogMongoEntity> getList(Pageable pageable) {
        return serverInvokeRepository.findAll(pageable);
    }

    @Override
    public Page<HttpRequestLogMongoEntity> getList(String parentId, Pageable pageable) {
        return httpRequestInvokeRepository.findHttpRequestLogMongoEntitiesByParentId(parentId, pageable);
    }
}
