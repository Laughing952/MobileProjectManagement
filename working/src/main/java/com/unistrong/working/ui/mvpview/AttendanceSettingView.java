package com.unistrong.working.ui.mvpview;

import com.unistrong.working.response.AttendanceSettingRep;

import java.util.List;

/**
 * 考勤设置
 * 作者：Laughing on 2018/5/22 16:51
 * 邮箱：719240226@qq.com
 */

public interface AttendanceSettingView<T> {
    /**
     * 首次进入页面加载数据
     *
     * @param response
     */
    void loadData(List<AttendanceSettingRep> response);

    /**
     * 上拉加载更多
     *
     * @param response
     */
    void loadMoreData(List<AttendanceSettingRep> response);
}
