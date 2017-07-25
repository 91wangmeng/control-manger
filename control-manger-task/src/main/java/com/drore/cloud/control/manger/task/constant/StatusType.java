package com.drore.cloud.control.manger.task.constant;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月25日 15:08 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public enum StatusType {

    /**
     * Running status type.
     * 运行中
     */
    RUNNING(-1, "运行中"),

    /**
     * Pause status type.
     * 暂停
     */
    PAUSE(1, "暂停"),

    /**
     * Shutdown status type.
     * 停止
     */
    SHUTDOWN(2, "停止"),

    /**
     * Overdue status type.
     * 过期
     */
    OVERDUE(3, "过期");

    private static final long serialVersionUID = -1211016710017589286L;
    private final int value;
    private final String name;
    private static final String INVALID_TYPE = "无效类型";

    StatusType(int value, String name) {
        this.value = value;
        this.name = name;
    }


    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets name.
     *
     * @param value the value
     * @return the name
     */
    public String getName(int value) {
        String name;
        if (value == RUNNING.getValue()) {
            name = RUNNING.getName();
        } else if (value == PAUSE.getValue()) {
            name = PAUSE.getName();
        } else if (value == SHUTDOWN.getValue()) {
            name = SHUTDOWN.getName();
        } else if (value == OVERDUE.getValue()) {
            name = OVERDUE.getName();
        } else {
            name = INVALID_TYPE;
        }
        return name;
    }

}
