package com.drore.cloud.control.manger.task.service.impl;

import com.drore.cloud.control.manger.task.dao.ScheduleTaskLogRepository;
import com.drore.cloud.control.manger.task.domain.ScheduleTaskLogEntity;
import com.drore.cloud.control.manger.task.service.ScheduleTaskLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月29日 10:53 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Service(value = "scheduleTaskLogService")
public class ScheduleTaskLogServiceImpl implements ScheduleTaskLogService {
    @Resource
    private ScheduleTaskLogRepository logRepository;

    @Override
    public void save(ScheduleTaskLogEntity logEntity) {
        logRepository.save(logEntity);
    }
}
