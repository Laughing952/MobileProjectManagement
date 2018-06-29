package com.project.ui.mvpview;

import com.project.response.WorkerTypeRep;

import java.util.List;

/**
 * 选择工种
 * Created by Water on 2018/5/14.
 */

public interface SelWorkerTypeView {

    void response(List<WorkerTypeRep> typeRepList);
}
