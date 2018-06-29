package com.unistrong.working.ui.mvpview;

import com.unistrong.working.request.AddCustomAttendanceReq;

/**
 * 编辑考勤点
 * 作者：Laughing on 2018/5/24 10:54
 * 邮箱：719240226@qq.com
 */

public interface AttendanceEditView {
    /**
     * 选择上下班时间
     */
    void chooseCommuteTime(int week);

    /**
     * 有效考勤范围
     */
    void setRange();

    /**
     * 跳转到地图页面获取考勤地点（包括经纬度）
     */
    void jumpAndGetData();

    /**
     * 删除考勤点
     */
    void deleteAttendancePoint();

    /**
     * 删除考勤点成功
     */
    void deleteAttendancePointResult();

    /**
     * 进入编辑页面发送网络请求考勤点信息
     * @param response
     */
    void backData(AddCustomAttendanceReq response);

    /**
     * 编辑成功
     */
    void editAttendancePointResult();
}
