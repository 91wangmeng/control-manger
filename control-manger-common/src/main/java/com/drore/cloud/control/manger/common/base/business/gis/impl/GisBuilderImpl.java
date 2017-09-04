package com.drore.cloud.control.manger.common.base.business.gis.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.drore.cloud.control.manger.common.base.business.gis.GisBuilder;
import com.drore.cloud.control.manger.common.base.business.gis.domain.*;
import com.drore.cloud.control.manger.common.base.exception.CMException;
import com.drore.cloud.control.manger.common.base.utils.ControlHttpUtils;
import com.drore.cloud.control.manger.common.base.utils.ControlStringUtils;
import com.drore.cloud.control.manger.common.cache.service.RedisBuilder;
import com.drore.cloud.sdk.client.CloudQueryRunner;
import com.drore.cloud.sdk.domain.Pagination;
import com.drore.cloud.sdk.domain.util.RequestExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年08月30日 14:03 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@Component
@ConditionalOnExpression("'${spring.application.name}'!='control-manger'")
public class GisBuilderImpl implements GisBuilder {
    private static Logger LOGGER = LoggerFactory.getLogger(GisBuilderImpl.class);

    private static final String LABEL_TAB = "labelTab";
    private static final String LABEL_INFO = "labelInfo";
    private static final String TENANT_CONFIG = "tenant_config";
    private static final String TENANT_GIS_MAP = "tenant_gis_map";
    private static final String MAP_MAIN = "map_main";

    @Value("${gis.url}")
    private String gis_url;
    @Value("${gis.find}")
    private String gis_find;
    @Value("${gis.map_main}")
    private String gis_map_main;
    @Value("${gis.common_find_by_id}")
    private String gis_common_find_by_id;
    @Value("${gis.find_by_map_ids_and_subtypes}")
    private String gis_find_by_map_ids_and_subtypes;
    @Value("${gis.get_label_by_distance_and_type}")
    private String gis_get_label_by_distance_and_type;
    @Value("${gis.get_map_id_by_triggerDevice}")
    private String gis_get_map_id_by_triggerDevice;

    @Resource
    private RedisBuilder redisBuilder;


    @Resource
    private CloudQueryRunner tenantRunner;
    @Resource
    private CloudQueryRunner gisRunner;

    @Override
    public TenantConfig getTentInfo() {
        TenantConfig tenantConfig;
        if (redisBuilder.exitKey(TENANT_CONFIG)) {
            tenantConfig = JSON.toJavaObject((JSON) redisBuilder.get(TENANT_CONFIG), TenantConfig.class);
        } else {
            tenantConfig = tenantRunner.queryFirstByRName(TenantConfig.class, TENANT_CONFIG, new HashMap());
            redisBuilder.set(TENANT_CONFIG, tenantConfig);
        }
        return tenantConfig;
    }

    @Override
    public List<MapInfo> getAllMaps() {
        List<MapInfo> mapInfos = Collections.EMPTY_LIST;
        if (redisBuilder.exitKey(MAP_MAIN)) {
            mapInfos = (List<MapInfo>) redisBuilder.get(MAP_MAIN);
        } else {
            //获取租户地图信息
            Pagination<TenantMapConfig> tenantMapConfigPagination = tenantRunner.queryListByExample(TenantMapConfig.class, TENANT_GIS_MAP, 1, 100);
            if (tenantMapConfigPagination.getCount() > 0) {
                RequestExample req = new RequestExample();
                RequestExample.Criteria rc = req.create();
                RequestExample.Param param = req.createParam();
                tenantMapConfigPagination.getData()
                        .stream().
                        map(tenantMapConfig -> tenantMapConfig.getMapId())
                        .forEach(mapId -> param.addTerm("id", mapId));
                rc.getShould().add(param);
                req.setPageSize(100);
                Pagination<MapInfo> mapInfoPagination = gisRunner.queryListByExample(MapInfo.class, MAP_MAIN, req);
                mapInfos = mapInfoPagination.getData();
                redisBuilder.setEX(MAP_MAIN, mapInfos, 24 * 60 * 60);
            }
        }
        return mapInfos;
    }

    @Override
    public List<LabelType> getAllLabelTypes() {
        List<LabelType> labelTypes = Collections.EMPTY_LIST;
        if (redisBuilder.exitKey(LABEL_TAB)) {
            labelTypes = (List<LabelType>) redisBuilder.get(LABEL_TAB);
        } else {
            String url = MessageFormat.format(gis_url + gis_find, LABEL_TAB);
            JSONObject jsonObject = gisParam(1, 100, new JSONObject());
            String allLabelTypesStr = ControlHttpUtils.postJson("获取所有标签类型", url, jsonObject);

            if (ControlStringUtils.isNotEmpty(allLabelTypesStr)) {
                Pagination<LabelType> pagination = JSON.parseObject(allLabelTypesStr, Pagination.class);
                try {
                    labelTypes = pagination.getData();
                    redisBuilder.setEX(LABEL_TAB, labelTypes, 12 * 60 * 60);
                } catch (Exception e) {
                    throw new CMException("获取所有标签类型失败");
                }
            }

        }
        return labelTypes;
    }


    private static JSONObject gisParam(int pageNo, int pageSize, JSONObject fields) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo", 1);
        jsonObject.put("pageSize", 5000);
        jsonObject.put("fields", fields);
        return jsonObject;
    }

    @Override
    public List<LabelInfo> getAllLabelsByMapId(String mapId) {
        List<LabelInfo> allLabels = Collections.EMPTY_LIST;
        if (redisBuilder.exitKey(mapId)) {
            allLabels = (List<LabelInfo>) redisBuilder.get(mapId);
        } else {
            List<LabelType> allLabelTypes = JSON.parseArray(JSON.toJSONString(getAllLabelTypes()), LabelType.class);
            String subTypes = allLabelTypes.parallelStream().map(labelType -> labelType.getId()).reduce((s, s2) -> s + "," + s2).get();
            String url = MessageFormat.format(gis_url + gis_find_by_map_ids_and_subtypes, LABEL_INFO);
            JSONObject fields = new JSONObject();
            fields.put("subtypes", subTypes);
            fields.put("mapId", mapId);
            JSONObject jsonObject = gisParam(1, 5000, fields);

            String allLabelsStr = ControlHttpUtils.postJson("获取所有标签信息", url, jsonObject);
            if (ControlStringUtils.isNotEmpty(allLabelsStr)) {
                Pagination<LabelInfo> pagination = JSONObject.parseObject(allLabelsStr, new TypeReference<Pagination<LabelInfo>>() {
                });
                if (pagination.getCount() > 0) {
                    List<LabelInfo> data = pagination.getData();
                    Map<String, List<LabelInfo>> mapLabel = data.stream()
                            .collect(groupingBy(labelInfo -> labelInfo.getMapId()));
                    mapLabel.forEach((map, labelInfos) ->
                            redisBuilder.set(map, labelInfos));

                }
            }
        }
        return allLabels;
    }

    @Override
    public List<LabelInfo> getLabelsByTypeAndDistance(double x, double y, int distance, String... subTypes) {
        String url = gis_url + gis_get_label_by_distance_and_type;
        String labelType = Arrays.stream(subTypes).reduce((s, s2) -> s + "," + s2).get();
        JSONObject param = new JSONObject();
        param.put("x", x);
        param.put("y", y);
        param.put("distance", distance);
        param.put("labelType", labelType);
        String labels = ControlHttpUtils.postJson("根据距离和类型获取点位周边标签", url, param);

        Pagination<LabelInfo> pagination;
        if (ControlStringUtils.isNotEmpty(labels)) {
            pagination = JSONObject.parseObject(labels, new TypeReference<Pagination<LabelInfo>>() {
            });
            return pagination.getData();
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<List<LabelInfo>> getLabelsByTypeAndMapId(String mapId, String... subTypes) {
        List<LabelInfo> allLabelsByMapId = this.getAllLabelsByMapId(mapId);
        List<String> strings = Arrays.asList(subTypes);
        Map<String, List<LabelInfo>> typeLabels = allLabelsByMapId.stream()
                .collect(groupingBy(labelInfo -> labelInfo.getSubtype()));
        List<List<LabelInfo>> lists = new LinkedList<>();
        strings.forEach(s -> {
            if (typeLabels.containsKey(s)) {
                lists.add(typeLabels.get(s));
            } else {
                lists.add(Collections.EMPTY_LIST);
            }
        });
        return lists;
    }

    @Override
    public LabelInfo getLabelByDeviceInfoAndSubType(String deviceInfo, String subType) {
        List<LabelInfo> allLabelsByMapId = this.getAllLabelsByMapId("");
        Optional<LabelInfo> any = allLabelsByMapId.stream().filter(labelInfo -> deviceInfo.equals(labelInfo.getDeviceInfo()) && subType.equals(labelInfo.getSubtype())).findAny();
        LabelInfo labelInfo = any.orElseThrow(() -> new CMException("地图不存在该点位"));
        return labelInfo;
    }


    @Override
    public LabelInfo getRoad(List<Point> points) {
        return null;
    }

    @Override
    public String getMapIdByPoint(double x, double y) {
        return null;
    }
}
