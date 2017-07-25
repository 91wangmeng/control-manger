package com.drore.cloud.control.manger.common.log.constant;

import java.io.Serializable;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: 日志类型<br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 21:55 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public enum LogType implements Serializable {
    /**
     * Api log type log type.
     */
    API_LOG_TYPE(0),
    /**
     * Operation log type log type.
     */
    OPERATION_LOG_TYPE(1);

    private static final long serialVersionUID = -1211016710017589286L;
    private final int value;

    LogType(int value) {
        this.value = value;
    }

    /**
     * Get value int.
     *
     * @return the int
     */
    public int getValue() {
        return value;
    }
}
