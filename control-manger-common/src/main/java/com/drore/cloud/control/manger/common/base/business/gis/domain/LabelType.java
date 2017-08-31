package com.drore.cloud.control.manger.common.base.business.gis.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年08月30日 13:56 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
public class LabelType implements Serializable {
    private static Logger LOGGER = LoggerFactory.getLogger(LabelType.class);

    @SerializedName("src")
    @JsonProperty("src")
    @JSONField(name = "src")
    private String src;
    @SerializedName("page_src")
    @JsonProperty("page_src")
    @JSONField(name = "page_src")
    private String pageSrc;
    @SerializedName("name")
    @JsonProperty("name")
    @JSONField(name = "name")
    private String name;
    @SerializedName("id")
    @JsonProperty("id")
    @JSONField(name = "id")
    private String id;
    @SerializedName("label_code")
    @JsonProperty("label_code")
    @JSONField(name = "label_code")
    private String labelCode;

}
