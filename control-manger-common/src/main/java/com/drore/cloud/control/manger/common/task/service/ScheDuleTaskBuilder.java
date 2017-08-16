package com.drore.cloud.control.manger.common.task.service;

import com.drore.cloud.control.manger.common.base.domain.vo.Result;
import com.drore.cloud.control.manger.common.task.domain.ScheduleTaskDetailEntity;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年08月16日 15:35 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public interface ScheDuleTaskBuilder {
    /**
     * Run task result.
     *
     * @param name   the name 任务名称
     * @param taskId the task id 任务ID
     * @return the result
     */
    Result runTask(String name, String taskId);

    /**
     * Pause task result.
     *
     * @param name   the name 任务名称
     * @param taskId the task id 任务ID
     * @return the result
     */
    Result pauseTask(String name, String taskId);

    /**
     * Recume task result.
     *
     * @param name   the name 任务名称
     * @param taskId the task id 任务ID
     * @return the result
     */
    Result resumeTask(String name, String taskId);

    /**
     * Add task result.
     *
     * @param name             the name 任务名称
     * @param taskDetailEntity the task detail entity 任务详情
     * @return the result
     */
    Result addTask(String name, ScheduleTaskDetailEntity taskDetailEntity);

    /**
     * Update task result.
     *
     * @param name             the name 任务名称
     * @param taskDetailEntity the task detail entity 任务详情
     * @return the result
     */
    Result updateTask(String name, ScheduleTaskDetailEntity taskDetailEntity);

    /**
     * Delete task result.
     *
     * @param name   the name 任务名称
     * @param taskId the task id 任务ID
     * @return the result
     */
    Result deleteTask(String name, String taskId);
}
