package com.drore.cloud.control.manger.common.task.domain;

import lombok.*;

import java.util.Date;
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
@AllArgsConstructor
@Builder
public class ScheduleTaskDetailEntity {


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
    private Map<String, Object> param;

}
