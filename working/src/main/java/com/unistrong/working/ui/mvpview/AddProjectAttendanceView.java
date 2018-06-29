package com.unistrong.working.ui.mvpview;

import com.unistrong.working.response.CreateNewTask_ProjectNameRep;

/**
 * 新建——项目考勤点
 * 作者：Laughing on 2018/5/22 15:44
 * 邮箱：719240226@qq.com
 */

public interface AddProjectAttendanceView {

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
     * 设置项目名到TextView上
     *
     * @param rep
     */
    void setPerformerResult(CreateNewTask_ProjectNameRep rep);

    /**
     * 新建以项目名作为考勤点名字成功
     */
    void uploadDataSuccess();
}
