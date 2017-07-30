package com.drore.cloud.control.manger.task.utils;


import com.alibaba.fastjson.JSON;
import com.drore.cloud.control.manger.common.base.utils.SpringContextUtils;
import com.drore.cloud.control.manger.task.constant.TaskConstant;
import com.drore.cloud.control.manger.task.domain.ScheduleTaskDetailEntity;
import com.drore.cloud.control.manger.task.domain.ScheduleTaskLogEntity;
import com.drore.cloud.control.manger.task.service.ScheduleTaskLogService;
import com.drore.cloud.control.manger.task.service.impl.ScheduleTaskLogServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 定时任务
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月30日 下午12:44:21
 */
public class ScheduleJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String jsonJob = context.getMergedJobDataMap().getString(TaskConstant.SCHEDULE_TASK_KEY);
        ScheduleTaskDetailEntity scheduleJob = JSON.parseObject(jsonJob, ScheduleTaskDetailEntity.class);

        //获取spring bean
        ScheduleTaskLogService scheduleJobLogService = SpringContextUtils.getBean("scheduleTaskLogService", ScheduleTaskLogServiceImpl.class);
        //数据库保存执行记录
        ScheduleTaskLogEntity log = new ScheduleTaskLogEntity();
        String taskName = scheduleJob.getTaskName();
        String taskId = scheduleJob.getId();
        log.setTaskId(taskId);
        String triggerUrl = scheduleJob.getTriggerUrl();
        log.setTriggerUrl(triggerUrl);
        Object param = scheduleJob.getParam();
        log.setParam(param);
        String cornExpress = scheduleJob.getCornExpress();
        log.setCornExpress(cornExpress);
        log.setCreateTime(System.currentTimeMillis());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            logger.debug("任务准备执行，任务ID：" + taskId);
            ScheduleRunner task = new ScheduleRunner(taskId,taskName, triggerUrl, param);
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> task.run(), service);

            String result = completableFuture.get();
            log.setResult(result);
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setConsumeTime(times);
            //任务状态    0：成功    1：失败
            log.setSuccess(true);

            logger.debug("任务执行完毕，任务ID：" + scheduleJob.getId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + scheduleJob.getId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setConsumeTime(times);

            //任务状态    0：成功    1：失败
            log.setSuccess(false);
            log.setFailCause(e.getMessage());
        } finally {
            scheduleJobLogService.save(log);
        }
    }
}
