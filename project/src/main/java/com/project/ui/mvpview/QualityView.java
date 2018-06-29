package com.project.ui.mvpview;

import com.project.response.QualityListRep;

import java.util.List;

/**
 * 质量检查
 * Created by Water on 2018/5/10.
 */

public interface QualityView {

    /**
     * 列表数据
     *
     * @param qualityListReps
     */
    void responseList(List<QualityListRep> qualityListReps);

    void responseMoerList(List<QualityListRep> qualityListReps);

    /**
     * 选择状态的返回
     *
     * @param stater
     */
    void selStaterResult(String stater);

    /**
     * 选择状态的返回
     *
     * @param date
     */
    void selDateResult(String date);

    /**
     * 获取项目ID
     * @return
     */
    String getPId();

}
