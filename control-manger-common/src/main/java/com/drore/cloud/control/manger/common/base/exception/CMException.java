package com.drore.cloud.control.manger.common.base.exception;

import lombok.Data;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月29日 10:02 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
public class CMException extends RuntimeException {
    private static final long serialVersionUID = 7044930475015271034L;
    private String message;
    private int code = 500;

    /**
     * Instantiates a new Cm exception.
     *
     * @param msg the msg
     */
    public CMException(String msg) {
        super(msg);
        this.message = msg;
    }

    /**
     * Instantiates a new Cm exception.
     *
     * @param msg the msg
     * @param e   the e
     */
    public CMException(String msg, Throwable e) {
        super(msg, e);
        this.message = msg;
    }

    /**
     * Instantiates a new Cm exception.
     *
     * @param msg  the msg
     * @param code the code
     */
    public CMException(String msg, int code) {
        super(msg);
        this.message = msg;
        this.code = code;
    }

    /**
     * Instantiates a new Cm exception.
     *
     * @param msg  the msg
     * @param code the code
     * @param e    the e
     */
    public CMException(String msg, int code, Throwable e) {
        super(msg, e);
        this.message = msg;
        this.code = code;
    }

}
