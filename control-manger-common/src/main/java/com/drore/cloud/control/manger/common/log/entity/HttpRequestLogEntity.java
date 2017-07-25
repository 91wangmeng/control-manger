package com.drore.cloud.control.manger.common.log.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.drore.cloud.control.manger.common.base.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/7/20.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@Data
@ToString
public class HttpRequestLogEntity extends BaseEntity {
    /**
     * 该请求所属服务id
     */
    @JSONField(name = "parent_id")
    private String parentId;
    /**
     * 请求是否成功
     */
    @JSONField(name = "is_success")
    private boolean isSuccess;
    /**
     * 请求地址
     */
    @JSONField(name = "url")
    private String url;
    /**
     * 请求参数
     */
    @JSONField(name = "param")
    private Object param;
    /**
     * 消耗时间
     */
    @JSONField(name = "consume_time")
    private long consumeTime;
    /**
     * 响应结果
     */
    @JSONField(name = "response")
    private Object response;
    /**
     * 请求描述
     */
    @JSONField(name = "describe")
    private String describe;
    /**
     * 失败原因
     */
    @JSONField(name = "fail_cause")
    private String failCause;
}
