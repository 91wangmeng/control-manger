package com.drore.cloud.control.manger.common.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drore.cloud.control.manger.common.base.domain.vo.Result;
import com.drore.cloud.control.manger.common.base.utils.ControlHttpUtils;
import com.drore.cloud.control.manger.common.task.domain.ScheduleTaskDetailEntity;
import com.drore.cloud.control.manger.common.task.service.ScheDuleTaskBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年08月16日 15:40 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Component
public class ScheDuleTaskBuilderImpl implements ScheDuleTaskBuilder {
    @Value("${control-manger-ip}")
    private String control_manger_ip;
    @Value(("@{schedule.addTask}"))
    private String schedule_addTask;
    @Value(("@{schedule.updateTask}"))
    private String schedule_updateTask;
    @Value(("@{schedule.deleteTask}"))
    private String schedule_deleteTask;
    @Value(("@{schedule.runTask}"))
    private String schedule_runTask;
    @Value(("@{schedule.resumeTask}"))
    private String schedule_resumeTask;
    @Value(("@{schedule.pauseTask}"))
    private String schedule_pauseTask;

    @Override
    public Result runTask(String name, String taskId) {
        String format = MessageFormat.format("执行定时任务:{}", name);
        return doGetTaskRequest(format, schedule_runTask, taskId);
    }


    @Override
    public Result pauseTask(String name, String taskId) {
        String format = MessageFormat.format("暂停定时任务:{}", name);
        return doGetTaskRequest(format, schedule_pauseTask, taskId);
    }


    @Override
    public Result resumeTask(String name, String taskId) {
        String format = MessageFormat.format("恢复定时任务:{}", name);
        return doGetTaskRequest(format, schedule_resumeTask, taskId);
    }


    @Override
    public Result addTask(String name, ScheduleTaskDetailEntity taskDetailEntity) {
        String format = MessageFormat.format("新增定时任务:{}", name);
        return doPostTaskRequest(format, schedule_addTask, taskDetailEntity);
    }


    @Override
    public Result updateTask(String name, ScheduleTaskDetailEntity taskDetailEntity) {
        String format = MessageFormat.format("更新定时任务:{}", name);
        return doPostTaskRequest(format, schedule_updateTask, taskDetailEntity);
    }


    @Override
    public Result deleteTask(String name, String taskId) {
        String format = MessageFormat.format("删除定时任务:{}", name);
        return doGetTaskRequest(format, schedule_deleteTask, taskId);
    }

    private Result doPostTaskRequest(String describe, String url, ScheduleTaskDetailEntity taskDetailEntity) {
        JSONObject params = JSON.parseObject(taskDetailEntity.toString());
        String result = ControlHttpUtils.postJson(describe, control_manger_ip + url, params);
        return JSON.parseObject(result, Result.class);
    }

    private Result doGetTaskRequest(String describe, String url, String taskId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", taskId);
        String result = ControlHttpUtils.getWithParam(describe, control_manger_ip + url, params);
        return JSON.parseObject(result, Result.class);
    }
}
