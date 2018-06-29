package com.unistrong.baidumaplibrary.ui.mvpview;

import com.unistrong.baidumaplibrary.rep.AttendanceSettingRep;

import java.util.List;

/**
 * 项目地图
 * 作者：Laughing on 2018/5/7 09:08
 * 邮箱：719240226@qq.com
 */

public interface ProjectDistributionView {
    /**
     * 获取地图状态
     */
    void getMapState();

    /**
     * 缩小
     */
    void scaleMin();

    /**
     * 放大
     */
    void scaleMax();

    /**
     * 俯仰（0 ~ -45），每次在原来的基础上再俯仰-5
     */

    void upDown();

    /**
     * 移动到固定点
     */
    void move();

    /**
     * 普通地图
     */
    void normalMode();

    /**
     * 交通地图
     */
    void trafficMode();

    /**
     * 卫星地图
     */
    void satelliteMode();

    /**
     * 服务器返回项目列表（包含位置信息-经纬度）
     *
     * @param response
     */
    void backData(List<AttendanceSettingRep> response);
}

