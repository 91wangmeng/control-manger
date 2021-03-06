package com.drore.cloud.control.manger.task.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.drore.cloud.control.manger.common.base.domain.BaseEntity;
import com.drore.cloud.control.manger.common.base.utils.ControlDateUtils;
import com.drore.cloud.control.manger.task.constant.StatusType;
import com.drore.cloud.control.manger.task.constant.TaskConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: 任务详情实体类<br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月25日 13:47 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
@ToString
@NoArgsConstructor
@Document(collection = TaskConstant.SCHEDULE_TASK_DETAIL_MONGO)
@ApiModel
public class ScheduleTaskDetailEntity extends BaseEntity {

    /**
     * 任务名称
     */
    @JSONField(name = "task_name")
    @JsonProperty(value = "task_name")
    @ApiModelProperty(value = "任务名称")
    private String taskName;

    /**
     * cron表达式
     */
    @JSONField(name = "corn_express")
    @JsonProperty(value = "corn_express")
    @ApiModelProperty(value = "cron表达式", example = "0 0/1 * * * *")
    private String cornExpress;

    /**
     * 开始时间 默认当前时间
     */
    @JSONField(name = "start_time", format = ControlDateUtils.YMD_DASH_WITH_TIME)
    @JsonProperty(value = "start_time")
    @ApiModelProperty(value = "任务开始时间", notes = "默认当前时间", example = "2017-08-08 08:08:08")
    private LocalDateTime startTime = LocalDateTime.now();

    /**
     * 结束时间 默认到2099年结束
     */
    @JSONField(name = "end_time", format = ControlDateUtils.YMD_DASH_WITH_TIME)
    @JsonProperty(value = "end_time")
    @ApiModelProperty(value = "任务结束时间", notes = "默认2099-01-01 00:00:00", example = "2099-01-01 00:00:00")
    private LocalDateTime endTime = TaskConstant.DEFAULT_END_TIME;

    /**
     * 触发url
     */
    @JSONField(name = "trigger_url")
    @JsonProperty(value = "trigger_url")
    @ApiModelProperty(value = "任务触发地址", example = "http://www.baidu.com")
    private String triggerUrl;

    /**
     * 触发参数
     */
    @ApiModelProperty(value = "任务触发参数", notes = "Map<String,Object>格式")
    private Map<String, Object> param;

    /**
     * 任务状态
     */
    @ApiModelProperty(hidden = true)
    private int status = StatusType.RUNNING.getValue();

    /**
     * 失败时通知邮箱地址,预留
     */
    @ApiModelProperty(hidden = true)
    private String email;


}
