package com.drore.cloud.control.manger.web.control;

import com.drore.cloud.control.manger.common.base.domain.vo.Result;
import com.drore.cloud.control.manger.task.domain.ScheduleTaskDetailEntity;
import com.drore.cloud.control.manger.task.service.ScheduleTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月29日 14:58 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@RestController
@RequestMapping("/task")
@Api(value = "任务调度", description = "定时任务相关接口(如:定时任务的增删改查,任务列表,任务暂停,任务执行等接口)")
public class ScheduleTaskController {
    @Resource
    private ScheduleTaskService scheduleTaskService;

    /**
     * Add result.
     *
     * @param taskDetailEntity the task detail entity
     * @return the result
     */
    @RequestMapping(value = "/addTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增定时任务")
    public Result addTask(@RequestBody ScheduleTaskDetailEntity taskDetailEntity) {
        return scheduleTaskService.addTask(taskDetailEntity);
    }

    /**
     * Update task result.
     *
     * @param taskDetailEntity the task detail entity
     * @return the result
     */
    @RequestMapping("/updateTask")
    public Result updateTask(@RequestBody ScheduleTaskDetailEntity taskDetailEntity) {
        return scheduleTaskService.addTask(taskDetailEntity);
    }

    /**
     * Delete task result.
     *
     * @param taskId the task id
     * @return the result
     */
    @RequestMapping("/deleteTask")
    public Result deleteTask(@RequestParam(name = "id") String taskId) {
        return scheduleTaskService.deleteTask(taskId);
    }

    @RequestMapping("/clearOverDue")
    public Result clearOverDue() {
        return scheduleTaskService.clearOverDue();
    }
}
