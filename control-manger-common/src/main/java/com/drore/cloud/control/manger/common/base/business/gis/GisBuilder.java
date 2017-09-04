package com.drore.cloud.control.manger.common.base.business.gis;

import com.drore.cloud.control.manger.common.base.business.gis.domain.*;

import java.util.List;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年08月30日 13:56 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public interface GisBuilder {
    /**
     * Gets tent info. 获取租户信息
     *
     * @return the tent info
     */
    TenantConfig getTentInfo();

    /**
     * Gets all maps. 获取所有地图信息
     *
     * @return the all maps
     */
    List<MapInfo> getAllMaps();

    /**
     * Gets label type. 获取所有标签类型
     *
     * @return the label type
     */
    List<LabelType> getAllLabelTypes();


    /**
     * Gets all labels by map id. 根据地图编号获取所有标签
     *
     * @param mapId the map id
     * @return the all labels by map id
     */
    List<LabelInfo> getAllLabelsByMapId(String mapId);

    /**
     * Gets labels by type and distance. 获取指定范围内指定类型的标签列表
     *
     * @param distance the distance
     * @param subTypes the sub types
     * @return the labels by type and distance
     */

    List<LabelInfo> getLabelsByTypeAndDistance(double x, double y, int distance, String... subTypes);

    /**
     * Gets labels by type and map id. 根据标签类型和地图编号(可选)获取标签列表
     *
     * @param mapId    the map id
     * @param subTypes the sub types
     * @return the labels by type and map id
     */
    List<List<LabelInfo>> getLabelsByTypeAndMapId(String mapId, String... subTypes);


    /**
     * Gets label by device info. 根据设备信息获取标签信息
     *
     * @param deviceInfo the device info
     * @return the label by device info
     */

    LabelInfo getLabelByDeviceInfoAndSubType(String deviceInfo, String subType);

    /**
     * Gets road. 根据点位获取路线
     *
     * @param points the points
     * @return the road
     */
    LabelInfo getRoad(List<Point> points);

    /**
     * Gets map id by point. 根据经纬度获取mapId
     *
     * @param x the x
     * @param y the y
     * @return the map id by point
     */
    String getMapIdByPoint(double x, double y);

}
