package com.drore.cloud.control.manger.task.utils;

import com.drore.cloud.control.manger.common.base.exception.CMException;
import com.drore.cloud.control.manger.common.http.utils.HttpRequestUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 执行定时任务
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月30日 下午12:49:33
 */
public class ScheduleRunner {
    private String taskId;
    private String taskName;
    private String triggerUrl;
    private Map<String,Object> params;

    public ScheduleRunner(String taskId, String taskName, String triggerUrl, Map<String,Object> params) throws NoSuchMethodException, SecurityException {
        this.taskId = taskId;
        this.taskName = taskName;
        this.triggerUrl = triggerUrl;
        this.params = params;
    }

    public String run() {
        String result;
        try {
            if (StringUtils.isEmpty(params)) {
                result = HttpRequestUtils.get(taskName, triggerUrl);
            } else {
                result = HttpRequestUtils.postJson(taskName, triggerUrl, params);
            }
        } catch (Exception e) {
            throw new CMException("执行定时任务失败", e);
        }
        return result;
    }

}
