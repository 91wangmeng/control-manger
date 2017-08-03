package com.drore.cloud.control.manger.task.constant;

import java.util.Calendar;
import java.util.Date;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月25日 16:48 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public class TaskConstant {
    private static Date time;

    static {
        Calendar instance = Calendar.getInstance();
        instance.set(2099, Calendar.JANUARY, 1);
        time = instance.getTime();
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
    public static final Date DEFAULT_END_TIME = time;

}
