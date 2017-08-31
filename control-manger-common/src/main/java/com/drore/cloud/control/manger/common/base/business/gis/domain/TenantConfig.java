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
 * 创建日期: 2017年08月31日 14:13 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
public class TenantConfig implements Serializable {
    private static Logger LOGGER = LoggerFactory.getLogger(TenantConfig.class);

    @SerializedName("tenant_id")
    @JsonProperty("tenant_id")
    @JSONField(name = "tenant_id")
    private String tenantId;
    @SerializedName("country")
    @JsonProperty("country")
    @JSONField(name = "country")
    private String country;
    @SerializedName("multi_language")
    @JsonProperty("multi_language")
    @JSONField(name = "multi_language")
    private String multiLanguage;
    @SerializedName("logo_url")
    @JsonProperty("logo_url")
    @JSONField(name = "logo_url")
    private String logoUrl;
    @SerializedName("cn_full_name")
    @JsonProperty("cn_full_name")
    @JSONField(name = "cn_full_name")
    private String cnFullName;
    @SerializedName("mobile")
    @JsonProperty("mobile")
    @JSONField(name = "mobile")
    private String mobile;
    @SerializedName("province_name")
    @JsonProperty("province_name")
    @JSONField(name = "province_name")
    private String provinceName;
    @SerializedName("district_name")
    @JsonProperty("district_name")
    @JSONField(name = "district_name")
    private String districtName;
    @SerializedName("cn_short_name")
    @JsonProperty("cn_short_name")
    @JSONField(name = "cn_short_name")
    private String cnShortName;
    @SerializedName("city_name")
    @JsonProperty("city_name")
    @JSONField(name = "city_name")
    private String cityName;
    @SerializedName("theme")
    @JsonProperty("theme")
    @JSONField(name = "theme")
    private String theme;
    @SerializedName("id")
    @JsonProperty("id")
    @JSONField(name = "id")
    private String id;

}
