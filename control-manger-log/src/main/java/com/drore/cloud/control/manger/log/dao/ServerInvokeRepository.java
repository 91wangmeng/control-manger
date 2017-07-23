package com.drore.cloud.control.manger.log.dao;

import com.drore.cloud.control.manger.log.domain.ServerInvokeLogMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 22:50 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public interface ServerInvokeRepository extends MongoRepository<ServerInvokeLogMongoEntity, String> {
}
