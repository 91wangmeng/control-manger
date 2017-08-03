package com.drore.cloud.control.manger.task.dao;

import com.drore.cloud.control.manger.task.domain.ScheduleTaskDetailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月25日 16:39 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public interface ScheduleTaskDetailRepository extends MongoRepository<ScheduleTaskDetailEntity, String> {
    List<ScheduleTaskDetailEntity> findAllByStatusAndEndTimeAfter(int status, Date date);
    List<ScheduleTaskDetailEntity> findAllByEndTimeBefore(Date date);
}
