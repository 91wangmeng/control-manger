package com.drore.cloud.control.manger.task.utils;

import com.alibaba.fastjson.JSON;
import com.drore.cloud.control.manger.common.base.exception.CMException;
import com.drore.cloud.control.manger.task.constant.StatusType;
import com.drore.cloud.control.manger.task.constant.TaskConstant;
import com.drore.cloud.control.manger.task.domain.ScheduleTaskDetailEntity;
import org.quartz.*;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: 调度工具类<br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月29日 09:48 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public class ScheduleUtils {
    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(String taskId) {
        return TriggerKey.triggerKey(taskId);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(String taskId) {
        return JobKey.jobKey(taskId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String taskId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(taskId));
        } catch (Exception e) {
            throw new CMException("获取定时任务CronTrigger出现异常", e);
        }
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleTaskDetailEntity taskDetailEntity) {
        try {
            //构建job信息

            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(taskDetailEntity.getId())).build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskDetailEntity.getCornExpress())
                    .withMisfireHandlingInstructionDoNothing();

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(taskDetailEntity.getId()))
                    .withSchedule(scheduleBuilder)
                    .startAt(taskDetailEntity.getStartTime())
                    .endAt(taskDetailEntity.getEndTime())
                    .build();
            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(TaskConstant.SCHEDULE_TASK_KEY, JSON.toJSONString(taskDetailEntity));

            scheduler.scheduleJob(jobDetail, trigger);

            //暂停任务
            if (taskDetailEntity.getStatus() == StatusType.PAUSE.getValue()) {
                pauseJob(scheduler, taskDetailEntity.getId());
            }
        } catch (Exception e) {
            throw new CMException("创建定时任务失败", e);
        }
    }

    /**
     * 更新定时任务
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleTaskDetailEntity taskDetailEntity) {
        try {
            TriggerKey triggerKey = getTriggerKey(taskDetailEntity.getId());

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskDetailEntity.getCornExpress())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, taskDetailEntity.getId());

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder()
                    .startAt(taskDetailEntity.getStartTime())
                    .endAt(taskDetailEntity.getEndTime())
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();

            //参数
            trigger.getJobDataMap().put(TaskConstant.SCHEDULE_TASK_KEY, JSON.toJSONString(taskDetailEntity));

            scheduler.rescheduleJob(triggerKey, trigger);

            //暂停任务
            if (taskDetailEntity.getStatus() == StatusType.PAUSE.getValue()) {
                pauseJob(scheduler, taskDetailEntity.getId());
            }

        } catch (Exception e) {
            throw new CMException("更新定时任务失败", e);
        }
    }

    /**
     * 立即执行任务
     */
    public static void run(Scheduler scheduler, ScheduleTaskDetailEntity taskDetailEntity) {
        try {
            //参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(TaskConstant.SCHEDULE_TASK_KEY, JSON.toJSONString(taskDetailEntity));
            scheduler.triggerJob(getJobKey(taskDetailEntity.getId()), dataMap);
        } catch (Exception e) {
            throw new CMException("立即执行定时任务失败", e);
        }
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, String taskId) {
        try {
            scheduler.pauseJob(getJobKey(taskId));
        } catch (Exception e) {
            throw new CMException("暂停定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, String taskId) {
        try {
            scheduler.resumeJob(getJobKey(taskId));
        } catch (Exception e) {
            throw new CMException("暂停定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, String taskId) {
        try {
            scheduler.deleteJob(getJobKey(taskId));
        } catch (Exception e) {
            throw new CMException("删除定时任务失败", e);
        }
    }
}
