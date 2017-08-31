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
 * 创建日期: 2017年08月31日 13:55 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Data
public class MapInfo implements Serializable {
    private static Logger LOGGER = LoggerFactory.getLogger(MapInfo.class);

    @SerializedName("imgwidth")
    @JsonProperty("imgwidth")
    @JSONField(name = "imgwidth")
    private int imgwidth;
    @SerializedName("scefit")
    @JsonProperty("scefit")
    @JSONField(name = "scefit")
    private int scefit;
    @SerializedName("scegoogley")
    @JsonProperty("scegoogley")
    @JSONField(name = "scegoogley")
    private double scegoogley;
    @SerializedName("scegooglex")
    @JsonProperty("scegooglex")
    @JSONField(name = "scegooglex")
    private double scegooglex;
    @SerializedName("ol_tile_y")
    @JsonProperty("ol_tile_y")
    @JSONField(name = "ol_tile_y")
    private int olTileY;
    @SerializedName("ol_tile_x")
    @JsonProperty("ol_tile_x")
    @JSONField(name = "ol_tile_x")
    private int olTileX;
    @SerializedName("map_type")
    @JsonProperty("map_type")
    @JSONField(name = "map_type")
    private String mapType;
    @SerializedName("path")
    @JsonProperty("path")
    @JSONField(name = "path")
    private String path;
    @SerializedName("spinzoomx")
    @JsonProperty("spinzoomx")
    @JSONField(name = "spinzoomx")
    private double spinzoomx;
    @SerializedName("spinzoomy")
    @JsonProperty("spinzoomy")
    @JSONField(name = "spinzoomy")
    private double spinzoomy;
    @SerializedName("id")
    @JsonProperty("id")
    @JSONField(name = "id")
    private String id;
    @SerializedName("height")
    @JsonProperty("height")
    @JSONField(name = "height")
    private int height;
    @SerializedName("initlevel")
    @JsonProperty("initlevel")
    @JSONField(name = "initlevel")
    private int initlevel;
    @SerializedName("imgheight")
    @JsonProperty("imgheight")
    @JSONField(name = "imgheight")
    private int imgheight;
    @SerializedName("ol_rat_x")
    @JsonProperty("ol_rat_x")
    @JSONField(name = "ol_rat_x")
    private double olRatX;
    @SerializedName("ol_rat_y")
    @JsonProperty("ol_rat_y")
    @JSONField(name = "ol_rat_y")
    private double olRatY;
    @SerializedName("zoom")
    @JsonProperty("zoom")
    @JSONField(name = "zoom")
    private int zoom;
    @SerializedName("sceinity")
    @JsonProperty("sceinity")
    @JSONField(name = "sceinity")
    private double sceinity;
    @SerializedName("init_mercator_x")
    @JsonProperty("init_mercator_x")
    @JSONField(name = "init_mercator_x")
    private double initMercatorX;
    @SerializedName("versions")
    @JsonProperty("versions")
    @JSONField(name = "versions")
    private String versions;
    @SerializedName("sceinitx")
    @JsonProperty("sceinitx")
    @JSONField(name = "sceinitx")
    private double sceinitx;
    @SerializedName("name")
    @JsonProperty("name")
    @JSONField(name = "name")
    private String name;
    @SerializedName("width")
    @JsonProperty("width")
    @JSONField(name = "width")
    private int width;
    @SerializedName("googlecenterx")
    @JsonProperty("googlecenterx")
    @JSONField(name = "googlecenterx")
    private int googlecenterx;
    @SerializedName("googlecentery")
    @JsonProperty("googlecentery")
    @JSONField(name = "googlecentery")
    private int googlecentery;
    @SerializedName("spiny")
    @JsonProperty("spiny")
    @JSONField(name = "spiny")
    private double spiny;
    @SerializedName("init_mercator_y")
    @JsonProperty("init_mercator_y")
    @JSONField(name = "init_mercator_y")
    private double initMercatorY;
    @SerializedName("spinx")
    @JsonProperty("spinx")
    @JSONField(name = "spinx")
    private double spinx;
}
