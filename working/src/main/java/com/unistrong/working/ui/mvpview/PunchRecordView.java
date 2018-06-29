package com.unistrong.working.ui.mvpview;

import com.unistrong.working.response.PunchRecordRep;

import java.util.List;

/**
 * 打卡记录
 * 作者：Laughing on 2018/5/19 16:21
 * 邮箱：719240226@qq.com
 */

public interface PunchRecordView<T> {
    /**
     * 初始化列表数据
     */
    void initRepData(List<PunchRecordRep> list);

    /**
     * 加载更多
     *
     * @param response
     */
    void loadMoreResult(List<PunchRecordRep> response);
}

