package com.drore.cloud.control.manger.common.log.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/4/20.
 * email：6492178@gmail.com
 * description: 服务调用日志实体类
 *
 * @author wmm
 */
@Data
@ToString
public class ServerInvokeLogEntity extends BaseEntity {
    @JSONField(name = "server_description")
    private String serverDescription;
    @JSONField(name = "server_api")
    private String serverApi;
    @JSONField(name = "client_ip")
    private String clientIp;
    @JSONField(name = "invoker")
    private int invoker;
    @JSONField(name = "invoke_time")
    private Long invokeTime;
    @JSONField(name = "is_success")
    private boolean isSuccess;
    @JSONField(name = "fail_cause")
    private String failCause;
    @JSONField(name = "response")
    private String response;
    @JSONField(name = "server_type")
    private int serverType;
    @JSONField(name = "consume_time")
    private long consumeTime;
}
