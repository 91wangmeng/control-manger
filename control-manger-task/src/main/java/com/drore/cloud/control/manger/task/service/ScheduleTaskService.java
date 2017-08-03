package com.drore.cloud.control.manger.task.service;

import com.drore.cloud.control.manger.common.base.domain.vo.Result;
import com.drore.cloud.control.manger.task.domain.ScheduleTaskDetailEntity;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月25日 16:38 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public interface ScheduleTaskService {
    /**
     * Run task result.
     *
     * @param taskId the task id
     * @return the result
     */
    Result runTask(String taskId);

    /**
     * Pause task result.
     *
     * @param taskId the task id
     * @return the result
     */
    Result pauseTask(String taskId);

    /**
     * Recume task result.
     *
     * @param taskId the task id
     * @return the result
     */
    Result resumeTask(String taskId);

    /**
     * Add task result.
     *
     * @param taskDetailEntity the task detail entity
     * @return the result
     */
    Result addTask(ScheduleTaskDetailEntity taskDetailEntity);

    /**
     * Update task result.
     *
     * @param taskDetailEntity the task detail entity
     * @return the result
     */
    Result updateTask(ScheduleTaskDetailEntity taskDetailEntity);

    /**
     * Delete task result.
     *
     * @param taskId the task id
     * @return the result
     */
    Result deleteTask(String taskId);

    /**
     * Clear over due result.
     *
     * @return the result
     */
    Result clearOverDue();
}
