package com.drore.cloud.control.manger.task.domain;

import com.drore.cloud.control.manger.common.base.domain.BaseEntity;
import com.drore.cloud.control.manger.task.constant.TaskConstant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月25日 14:48 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
@ToString
@NoArgsConstructor
@Document(collection = TaskConstant.SCHEDULE_TASK_LOG_MONGO)
public class ScheduleTaskLogEntity extends BaseEntity {
    /**
     * 所属任务id
     */
    @Indexed
    private String taskId;
    /**
     * 执行结果
     */
    private String result;
    /**
     * 消耗时间
     */
    private long consumeTime;
    /**
     * 是否成功
     */
    private boolean isSuccess;
    /**
     * 失败原因
     */
    private String failCause;
    /**
     * cron表达式
     */
    private String cornExpress;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 触发url
     */
    private String triggerUrl;
    /**
     * 触发参数
     */
    private Map<String,Object> param;
}
