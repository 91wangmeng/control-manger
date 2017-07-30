package com.drore.cloud.control.manger.task.service.impl;

import com.drore.cloud.control.manger.common.base.domain.vo.Result;
import com.drore.cloud.control.manger.task.constant.StatusType;
import com.drore.cloud.control.manger.task.dao.ScheduleTaskDetailRepository;
import com.drore.cloud.control.manger.task.domain.ScheduleTaskDetailEntity;
import com.drore.cloud.control.manger.task.service.ScheduleTaskService;
import com.drore.cloud.control.manger.task.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.UUID;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月29日 09:44 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
    @Resource
    private Scheduler scheduler;
    @Resource
    private ScheduleTaskDetailRepository detailRepository;

    /**
     * Init. 项目启动时初始化任务
     */
    @PostConstruct
    public void init() {
        Pageable pageable = new PageRequest(0, 10000);
        Page<ScheduleTaskDetailEntity> allTask = detailRepository.findAllByStatus(StatusType.RUNNING.getValue(), pageable);
        allTask.getContent()
                .stream()
                .forEach(taskDetailEntity -> {
                    CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, taskDetailEntity.getId());
                    //如果不存在，则创建
                    if (cronTrigger == null) {
                        ScheduleUtils.createScheduleJob(scheduler, taskDetailEntity);
                    } else {
                        ScheduleUtils.updateScheduleJob(scheduler, taskDetailEntity);
                    }
                });
    }

    /**
     * Run task result.
     *
     * @param taskId the task id
     * @return the result
     */
    @Override
    public Result runTask(String taskId) {
        ScheduleUtils.run(scheduler, detailRepository.findOne(taskId));
        return Result.success("开始运行任务");
    }

    /**
     * Pause task result.
     *
     * @param taskId the task id
     * @return the result
     */
    @Override
    public Result pauseTask(String taskId) {
        return null;
    }

    /**
     * Recume task result.
     *
     * @param taskId the task id
     * @return the result
     */
    @Override
    public Result resumeTask(String taskId) {
        return null;
    }

    /**
     * Add task result.
     *
     * @param taskDetailEntity the task detail entity
     * @return the result
     */
    @Override
    @Transactional
    public Result addTask(ScheduleTaskDetailEntity taskDetailEntity) {
        String id = UUID.randomUUID().toString();
        taskDetailEntity.setId(id);
        taskDetailEntity.setStatus(StatusType.RUNNING.getValue());
        detailRepository.save(taskDetailEntity);
        ScheduleUtils.createScheduleJob(scheduler, taskDetailEntity);
        return Result.success("新增任务成功");
    }

    /**
     * Delete task result.
     *
     * @param taskId the task id
     * @return the result
     */
    @Override
    public Result deleteTask(String taskId) {
        return null;
    }

    /**
     * Clear over due result.
     *
     * @return the result
     */
    @Override
    public Result clearOverDue() {
        return null;
    }


}
