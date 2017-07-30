package com.drore.cloud.control.manger.task.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.drore.cloud.control.manger.common.base.domain.BaseEntity;
import com.drore.cloud.control.manger.common.base.utils.DateUtils;
import com.drore.cloud.control.manger.task.constant.StatusType;
import com.drore.cloud.control.manger.task.constant.TaskConstant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
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
    @JSONField(name = "task_name")
    private String taskName;
    /**
     * cron表达式
     */
    @JSONField(name = "corn_express")

    private String cornExpress;
    /**
     * 开始时间 默认当前时间
     */
    @JSONField(name = "start_time", format = DateUtils.YMD_DASH_WITH_TIME)

    private Date startTime = Calendar.getInstance().getTime();
    /**
     * 结束时间 默认到2099年结束
     */
    @JSONField(name = "end_time", format = DateUtils.YMD_DASH_WITH_TIME)

    private Date endTime = TaskConstant.DEFAULT_END_TIME;
    /**
     * 触发url
     */
    @JSONField(name = "trigger_url")

    private String triggerUrl;
    /**
     * 触发参数
     */
    @JSONField(name = "param")
    private Object param;
    /**
     * 任务状态
     */
    private int status = StatusType.RUNNING.getValue();

    /**
     * 失败时通知邮箱地址,预留
     */
    private String email;


}
