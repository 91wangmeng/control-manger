package com.drore.cloud.control.manger.log.domain;

import com.drore.cloud.control.manger.common.log.entity.HttpRequestLogEntity;
import com.drore.cloud.control.manger.log.constant.LogConstant;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: mongodb网络请求实体类<br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 23:16 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
@NoArgsConstructor
@Document(collection = LogConstant.HTTP_REQUEST_LOG_MONGO)
public class HttpRequestLogMongoEntity extends HttpRequestLogEntity {
    /**
     * Instantiates a new Http request log mongo entity.
     *
     * @param httpRequestLogEntity the http request log entity
     */
    public HttpRequestLogMongoEntity(HttpRequestLogEntity httpRequestLogEntity) {
        this.setConsumeTime(httpRequestLogEntity.getConsumeTime());
        this.setDescribe(httpRequestLogEntity.getDescribe());
        this.setFailCause(httpRequestLogEntity.getFailCause());
        this.setParam(httpRequestLogEntity.getParam());
        this.setParentId(httpRequestLogEntity.getParentId());
        this.setResponse(httpRequestLogEntity.getResponse());
        this.setSuccess(httpRequestLogEntity.isSuccess());
        this.setUrl(httpRequestLogEntity.getUrl());
        this.setId(httpRequestLogEntity.getId());
    }
}
