package com.unistrong.baidumaplibrary.ui.mvpview;

import com.unistrong.baidumaplibrary.rep.AttendanceSettingRep;
import com.unistrong.baidumaplibrary.req.AddCustomAttendanceReq;

import java.util.List;

/**定位打卡
 * 作者：Laughing on 2018/5/26 17:01
 * 邮箱：719240226@qq.com
 */

public interface LocationView {

    void backData(List<AttendanceSettingRep> response);

    /**
     * 返回查询到的打卡名字列表
     * @param rep
     */
    void setPerformerResult(AttendanceSettingRep rep);

    /**
     * 考勤点信息拿到经纬度与定位的经纬度用来计算考勤距离
     * @param flag
     * @param response
     */
    void backPointInfo(int flag, AddCustomAttendanceReq response);

    void finishiPage();
}
