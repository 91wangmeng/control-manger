package com.drore.cloud.control.manger.common.base.business.gis.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drore.cloud.control.manger.common.base.domain.vo.LabelInfo;
import com.drore.cloud.control.manger.common.base.domain.vo.LabelType;
import com.drore.cloud.control.manger.common.base.domain.vo.Point;
import com.drore.cloud.control.manger.common.base.exception.CMException;
import com.drore.cloud.control.manger.common.base.business.gis.GisBuilder;
import com.drore.cloud.control.manger.common.base.utils.ControlHttpUtils;
import com.drore.cloud.control.manger.common.base.utils.ControlStringUtils;
import com.drore.cloud.control.manger.common.cache.service.RedisBuilder;
import com.drore.cloud.sdk.domain.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

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


    @Resource
    private RedisBuilder redisBuilder;


    @Override
    public List<LabelType> getAllLabelTypes() {
        List<LabelType> labelTypes = Collections.EMPTY_LIST;
        if (redisBuilder.exitKey(LABEL_TAB)) {
            labelTypes = (List<LabelType>) redisBuilder.get(LABEL_TAB);
        } else {
            String url = MessageFormat.format(gis_url + gis_find, LABEL_TAB);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pageNo", 1);
            jsonObject.put("pageSize", 100);
            jsonObject.put("fields", new JSONObject());
            String allLabelTypesStr = ControlHttpUtils.postJson("获取所有标签类型", url, jsonObject);

            if (ControlStringUtils.isNotEmpty(allLabelTypesStr)) {
                Pagination<LabelType> pagination = JSON.parseObject(allLabelTypesStr, Pagination.class);
                try {
                    labelTypes = pagination.getData();
                    redisBuilder.setEX(LABEL_TAB,labelTypes,12*60*60);
                } catch (Exception e) {
                    throw new CMException("获取所有标签类型失败");
                }
            }

        }
        return labelTypes;
    }

    @Override
    public List<LabelInfo> getAllLabels() {
        List<LabelType> allLabelTypes = getAllLabelTypes();
        String subTypes = allLabelTypes.parallelStream().map(labelType -> labelType.getName()).reduce((s, s2) -> s + "," + s2).get();
        String url = MessageFormat.format(gis_url + gis_find_by_map_ids_and_subtypes, LABEL_INFO);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo", 1);
        jsonObject.put("pageSize", 10000);
        JSONObject fields = new JSONObject();
        fields.put("subtypes", subTypes);
        String mapId = "";
        fields.put("mapId", mapId);
        jsonObject.put("fields", fields);
        String allLabelsStr = ControlHttpUtils.postJson("获取所有标签信息", url, jsonObject);
        if (ControlStringUtils.isNotEmpty(allLabelsStr)) {
            Pagination<LabelInfo> pagination = JSONObject.parseObject(allLabelsStr, Pagination.class);
            if (pagination.getCount() > 0) {
                List<LabelInfo> data = pagination.getData();
                Map<String, List<LabelInfo>> typeLabel = data.stream().collect(Collectors.groupingBy(labelinfo -> labelinfo.getSubtype()));
                LinkedList<LabelInfo> labelInfos = new LinkedList<>();
                Arrays.stream(subTypes.split(",")).forEach(s -> {
                    if (typeLabel.containsKey(s)) {
                        labelInfos.addAll(typeLabel.get(s));
                    } else {
                        labelInfos.addAll(Collections.EMPTY_LIST);
                    }
                });
                return labelInfos;
            }
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<LabelInfo> getLabelsByTypeAndDistance(int distance, String... subTypes) {
        return null;
    }

    @Override
    public List<LabelInfo> getLabelsByTypeAndMapId(String mapId, String... subTypes) {
        return null;
    }

    @Override
    public LabelInfo getLabelByDeviceInfo(String deviceInfo) {
        return null;
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
