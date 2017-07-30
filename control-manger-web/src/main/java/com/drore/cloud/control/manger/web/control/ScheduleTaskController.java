package com.drore.cloud.control.manger.web.control;

import com.drore.cloud.control.manger.common.base.domain.vo.Result;
import com.drore.cloud.control.manger.task.domain.ScheduleTaskDetailEntity;
import com.drore.cloud.control.manger.task.service.ScheduleTaskService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class ScheduleTaskController {
    @Resource
    private ScheduleTaskService scheduleTaskService;

    /**
     * Add result.
     *
     * @param taskDetailEntity the task detail entity
     * @return the result
     */
    @RequestMapping("/addTask")
    public Result add(@RequestBody ScheduleTaskDetailEntity taskDetailEntity) {
        return scheduleTaskService.addTask(taskDetailEntity);
    }
}
