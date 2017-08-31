package com.drore.cloud.control.manger.common.base.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年08月30日 13:57 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
public class LabelInfo {

    @SerializedName("device_version")
    @JsonProperty("device_version")
    @JSONField(name = "device_version")
    private String deviceVersion;
    @SerializedName("factory_phone")
    @JsonProperty("factory_phone")
    @JSONField(name = "factory_phone")
    private String factoryPhone;
    @SerializedName("en_video_url")
    @JsonProperty("en_video_url")
    @JSONField(name = "en_video_url")
    private String enVideoUrl;
    @SerializedName("building_contractor")
    @JsonProperty("building_contractor")
    @JSONField(name = "building_contractor")
    private String buildingContractor;
    @SerializedName("facilities_type")
    @JsonProperty("facilities_type")
    @JSONField(name = "facilities_type")
    private String facilitiesType;
    @SerializedName("device_factory")
    @JsonProperty("device_factory")
    @JSONField(name = "device_factory")
    private String deviceFactory;
    @SerializedName("device_model")
    @JsonProperty("device_model")
    @JSONField(name = "device_model")
    private String deviceModel;
    @SerializedName("panorama_url")
    @JsonProperty("panorama_url")
    @JSONField(name = "panorama_url")
    private String panoramaUrl;
    @SerializedName("plants_genus")
    @JsonProperty("plants_genus")
    @JSONField(name = "plants_genus")
    private String plantsGenus;
    @SerializedName("map_id")
    @JsonProperty("map_id")
    @JSONField(name = "map_id")
    private String mapId;
    @SerializedName("subtype")
    @JsonProperty("subtype")
    @JSONField(name = "subtype")
    private String subtype;
    @SerializedName("software_version")
    @JsonProperty("software_version")
    @JSONField(name = "software_version")
    private String softwareVersion;
    @SerializedName("id")
    @JsonProperty("id")
    @JSONField(name = "id")
    private String id;
    @SerializedName("three_lng")
    @JsonProperty("three_lng")
    @JSONField(name = "three_lng")
    private String threeLng;
    @SerializedName("building_structure")
    @JsonProperty("building_structure")
    @JSONField(name = "building_structure")
    private String buildingStructure;
    @SerializedName("info")
    @JsonProperty("info")
    @JSONField(name = "info")
    private String info;
    @SerializedName("ol_x")
    @JsonProperty("ol_x")
    @JSONField(name = "ol_x")
    private String olX;
    @SerializedName("building_time")
    @JsonProperty("building_time")
    @JSONField(name = "building_time")
    private String buildingTime;
    @SerializedName("address")
    @JsonProperty("address")
    @JSONField(name = "address")
    private String address;
    @SerializedName("ol_y")
    @JsonProperty("ol_y")
    @JSONField(name = "ol_y")
    private String olY;
    @SerializedName("plants_category")
    @JsonProperty("plants_category")
    @JSONField(name = "plants_category")
    private String plantsCategory;
    @SerializedName("src")
    @JsonProperty("src")
    @JSONField(name = "src")
    private String src;
    @SerializedName("cn_video_url")
    @JsonProperty("cn_video_url")
    @JSONField(name = "cn_video_url")
    private String cnVideoUrl;
    @SerializedName("three_lat")
    @JsonProperty("three_lat")
    @JSONField(name = "three_lat")
    private String threeLat;
    @SerializedName("device_installation")
    @JsonProperty("device_installation")
    @JSONField(name = "device_installation")
    private String deviceInstallation;
    @SerializedName("plants_section")
    @JsonProperty("plants_section")
    @JSONField(name = "plants_section")
    private String plantsSection;
    @SerializedName("movie_url")
    @JsonProperty("movie_url")
    @JSONField(name = "movie_url")
    private String movieUrl;
    @SerializedName("gx")
    @JsonProperty("gx")
    @JSONField(name = "gx")
    private String gx;
    @SerializedName("gy")
    @JsonProperty("gy")
    @JSONField(name = "gy")
    private String gy;
    @SerializedName("device_info")
    @JsonProperty("device_info")
    @JSONField(name = "device_info")
    private String deviceInfo;
    @SerializedName("img_path")
    @JsonProperty("img_path")
    @JSONField(name = "img_path")
    private String imgPath;
    @SerializedName("name")
    @JsonProperty("name")
    @JSONField(name = "name")
    private String name;
    @SerializedName("x")
    @JsonProperty("x")
    @JSONField(name = "x")
    private String x;
    @SerializedName("y")
    @JsonProperty("y")
    @JSONField(name = "y")
    private String y;
}
