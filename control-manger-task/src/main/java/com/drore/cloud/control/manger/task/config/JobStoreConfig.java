package com.drore.cloud.control.manger.task.config;

import lombok.Data;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月29日 14:18 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
public class JobStoreConfig {
    private String dbName;
    private String collectionPrefix;
    private boolean jobDataAsBase64;
    private int misfireThreshold;
}
