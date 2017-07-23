package com.drore.cloud.control.manger.common.log.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/7/20.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@Data
public class HttpRequestLogEntity extends BaseEntity {
    private static Logger LOGGER = LoggerFactory.getLogger(HttpRequestLogEntity.class);
    @JSONField(name = "parent_id")
    private String parentId;
    @JSONField(name = "is_success")
    private boolean isSuccess;
    @JSONField(name = "url")
    private String url;
    @JSONField(name = "param")
    private Object param;
    @JSONField(name = "consumeTime")
    private long consumeTime;
    @JSONField(name = "response")
    private Object response;
    @JSONField(name = "describe")
    private String describe;
    @JSONField(name = "fail_cause")
    private String failCause;
}
