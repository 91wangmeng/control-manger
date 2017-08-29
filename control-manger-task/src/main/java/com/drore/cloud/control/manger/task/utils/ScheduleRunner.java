package com.drore.cloud.control.manger.task.utils;

import com.drore.cloud.control.manger.common.base.exception.CMException;
import com.drore.cloud.control.manger.common.base.utils.ControlHttpUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 执行定时任务
 *
 * @author chenshun
 * @email sunlightcs @gmail.com
 * @date 2016年11月30日 下午12:49:33
 */
public class ScheduleRunner {
    private String taskId;
    private String taskName;
    private String triggerUrl;
    private Map<String,Object> params;

    /**
     * Instantiates a new Schedule runner.
     *
     * @param taskId     the task id
     * @param taskName   the task name
     * @param triggerUrl the trigger url
     * @param params     the params
     * @throws NoSuchMethodException the no such method exception
     * @throws SecurityException     the security exception
     */
    public ScheduleRunner(String taskId, String taskName, String triggerUrl, Map<String,Object> params) throws NoSuchMethodException, SecurityException {
        this.taskId = taskId;
        this.taskName = taskName;
        this.triggerUrl = triggerUrl;
        this.params = params;
    }

    /**
     * Run string.
     *
     * @return the string
     */
    public String run() {
        String result;
        try {
            if (StringUtils.isEmpty(params)) {
                result = ControlHttpUtils.get(taskName, triggerUrl);
            } else {
                result = ControlHttpUtils.postJson(taskName, triggerUrl, params);
            }
        } catch (Exception e) {
            throw new CMException("执行定时任务失败", e);
        }
        return result;
    }

}
