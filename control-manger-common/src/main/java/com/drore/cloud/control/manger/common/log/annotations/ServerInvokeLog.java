package com.drore.cloud.control.manger.common.log.annotations;


import com.drore.cloud.control.manger.common.log.constant.InvokerType;
import com.drore.cloud.control.manger.common.log.constant.LogType;

import java.lang.annotation.*;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/4/20.
 * email：6492178@gmail.com
 * description: 服务调用注解
 *
 * @author wmm
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServerInvokeLog {

    /**
     * Server description string.//服务描述
     *
     * @return the string
     */
    String serverDescription() default "";

    /**
     * Invoker string.//调用者信息
     *
     * @return the string
     */
    InvokerType[] invoker() default InvokerType.CONTROL_WEB_INVOKER_TYPE;


    /**
     * Log type log type.//日志类型
     *
     * @return the log type
     */
    LogType logType() default LogType.API_LOG_TYPE;
}
