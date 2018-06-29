package com.unistrong.working.ui.mvpview;

/**
 * 打卡设置
 * 作者：Laughing on 2018/5/22 15:44
 * 邮箱：719240226@qq.com
 */

public interface AddCustomAttendanceView {

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
     * 添加自定义考勤设置成功
     */
    void uploadDataSuccess();
}
