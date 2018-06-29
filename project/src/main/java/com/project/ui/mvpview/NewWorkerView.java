package com.project.ui.mvpview;

import com.project.request.WorkerReq;

/**
 * 新建/修改工人信息
 * Created by Water on 2018/5/14.
 */

public interface NewWorkerView {

    /**
     * 初始化数据的记过返回
     * @param workerReq
     */
    void initData(WorkerReq workerReq);

    void responseImage(String url);

    void showWorkerType(String workerType);

}
