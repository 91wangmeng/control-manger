package com.drore.cloud.control.manger.task.domain;

import com.drore.cloud.control.manger.common.base.domain.BaseEntity;
import com.drore.cloud.control.manger.task.constant.TaskConstant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
public class ScheduleTaskDetailEntity extends BaseEntity {

    /**
     * 任务名称
     */
    private String taskName;
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
    private Object param;
    /**
     * 任务状态
     */
    private int status;
    /**
     * 调用次数
     */
    private int count;

    /**
     * 失败时通知邮箱地址,预留
     */
    private String email;


}
