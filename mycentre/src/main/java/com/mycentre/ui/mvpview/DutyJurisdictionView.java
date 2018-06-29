package com.mycentre.ui.mvpview;

import com.mycentre.response.DutyRep;

import java.util.List;

/**
 * 职务设置
 * Created by Water on 2018/5/9.
 */

public interface DutyJurisdictionView {

    /**
     * 获取职务列表的返回
     *
     * @param dutyReps
     */
    void responseList(List<DutyRep> dutyReps);

    /**
     * 排序成功
     */
    void orderReult();

    /**
     * 删除成功
     */
    void delResult(DutyRep dutyRep);

}
