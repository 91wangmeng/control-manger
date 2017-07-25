package com.drore.cloud.control.manger.common.log.constant;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: 接口服务调用者类型<br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 21:50 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public enum  InvokerType {
    /**
     * Control app invoker type invoker type.//管控app
     */
    CONTROL_APP_INVOKER_TYPE(0),
    /**
     * Control web INVOKER type invoker type.//管控web前端
     */
    CONTROL_WEB_INVOKER_TYPE(1),
    /**
     * Tdp invoker type invoker type.
     */
    TDP_INVOKER_TYPE(2),
    /**
     * Uc invoker type invoker type.
     */
    UC_INVOKER_TYPE(3),
    /**
     * Gis invoker type invoker type.
     */
    GIS_INVOKER_TYPE(4);

    private static final long serialVersionUID = -1211016710017589286L;
    private final int value;

    InvokerType(int value) {
        this.value = value;
    }

    /**
     * Get value int.
     *
     * @return the int
     */
    public int getValue(){
        return value;
    }
}
