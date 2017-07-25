package com.drore.cloud.control.manger.common.log.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.drore.cloud.control.manger.common.base.domain.BaseEntity;
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
    /**
     * 服务描述
     */
    @JSONField(name = "server_description")
    private String serverDescription;
    /**
     * 服务接口
     */
    @JSONField(name = "server_api")
    private String serverApi;
    /**
     * 调用端ip
     */
    @JSONField(name = "client_ip")
    private String clientIp;
    /**
     * 接口调用者
     */
    @JSONField(name = "invoker")
    private Integer[] invoker;
    /**
     * 调用时间
     */
    @JSONField(name = "invoke_time")
    private Long invokeTime;
    /**
     * 是否成功
     */
    @JSONField(name = "is_success")
    private boolean isSuccess;
    /**
     * 失败原因
     */
    @JSONField(name = "fail_cause")
    private String failCause;
    /**
     * 响应结果
     */
    @JSONField(name = "response")
    private String response;
    /**
     * 服务类型
     */
    @JSONField(name = "server_type")
    private int serverType;
    /**
     * 消耗时间
     */
    @JSONField(name = "consume_time")
    private long consumeTime;
}
