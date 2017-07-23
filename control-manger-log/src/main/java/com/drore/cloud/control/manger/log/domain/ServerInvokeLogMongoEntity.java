package com.drore.cloud.control.manger.log.domain;

import com.drore.cloud.control.manger.log.constant.LogConstant;
import com.drore.cloud.control.manger.common.log.entity.ServerInvokeLogEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: mongodb服务调用实体类<br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 23:16 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
@NoArgsConstructor
@Document(collection = LogConstant.SERVER_INVOKE_LOG_MONGO)
public class ServerInvokeLogMongoEntity extends ServerInvokeLogEntity {
    /**
     * Instantiates a new Server invoke log mongo entity.
     *
     * @param serverInvokeLogEntity the server invoke log entity
     */
    public ServerInvokeLogMongoEntity(ServerInvokeLogEntity serverInvokeLogEntity) {
        this.setClientIp(serverInvokeLogEntity.getClientIp());
        this.setConsumeTime(serverInvokeLogEntity.getConsumeTime());
        this.setFailCause(serverInvokeLogEntity.getFailCause());
        this.setInvoker(serverInvokeLogEntity.getInvoker());
        this.setInvokeTime(serverInvokeLogEntity.getInvokeTime());
        this.setServerApi(serverInvokeLogEntity.getServerApi());
        this.setServerDescription(serverInvokeLogEntity.getServerDescription());
        this.setServerType(serverInvokeLogEntity.getServerType());
        this.setResponse(serverInvokeLogEntity.getResponse());
        this.setSuccess(serverInvokeLogEntity.isSuccess());
        this.setId(serverInvokeLogEntity.getId());
    }
}
