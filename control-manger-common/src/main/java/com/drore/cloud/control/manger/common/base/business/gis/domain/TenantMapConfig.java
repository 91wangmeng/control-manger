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
 * 创建日期: 2017年08月31日 15:07 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
public class TenantMapConfig implements Serializable{
    private static Logger LOGGER = LoggerFactory.getLogger(TenantMapConfig.class);

    @SerializedName("tenant_id")
    @JsonProperty("tenant_id")
    @JSONField(name = "tenant_id")
    private String tenantId;
    @SerializedName("map_id")
    @JsonProperty("map_id")
    @JSONField(name = "map_id")
    private String mapId;
    @SerializedName("map_name")
    @JsonProperty("map_name")
    @JSONField(name = "map_name")
    private String mapName;
    @SerializedName("id")
    @JsonProperty("id")
    @JSONField(name = "id")
    private String id;

}
