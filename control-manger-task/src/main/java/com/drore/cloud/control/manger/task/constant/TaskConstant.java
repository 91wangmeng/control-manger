package com.drore.cloud.control.manger.task.constant;

import java.time.LocalDateTime;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月25日 16:48 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public class TaskConstant {
    /**
     * The constant SUCCESS_RUN.
     */
    public static final String SUCCESS_RUN = "运行任务成功";
    /**
     * The constant SUCCESS_PAUSE.
     */
    public static final String SUCCESS_PAUSE = "暂停任务成功";
    /**
     * The constant SUCCESS_RESUME.
     */
    public static final String SUCCESS_RESUME = "恢复任务成功";
    /**
     * The constant SUCCESS_ADD.
     */
    public static final String SUCCESS_ADD = "新增任务成功";
    /**
     * The constant SUCCESS_UPDATE.
     */
    public static final String SUCCESS_UPDATE = "更新任务成功";
    /**
     * The constant SUCCESS_DELETE.
     */
    public static final String SUCCESS_DELETE = "删除任务成功";
    private static LocalDateTime time;

    static {
        time = LocalDateTime.of(2099, 12, 31, 23, 59, 59);
    }

    /**
     * The constant SCHEDULE_TASK_DETAIL_MONGO.
     */
    public static final String SCHEDULE_TASK_DETAIL_MONGO = "schedule_task_detail";

    /**
     * The constant SCHEDULE_TASK_LOG_MONGO.
     */
    public static final String SCHEDULE_TASK_LOG_MONGO = "schedule_task_log";
    /**
     * The constant SCHEDULE_TASK_KEY.
     */
    public static final String SCHEDULE_TASK_KEY = "schedule_task_key";

    /**
     * The constant DEFAULT_END_TIME.
     */
    public static final LocalDateTime DEFAULT_END_TIME = time;

}
