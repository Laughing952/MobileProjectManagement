package com.project.ui.mvpview;

/**
 * 出库:确认信息
 * Created by Water on 2018/5/24.
 */

public interface OutStockVerifyView {
    void initReceiver(String name);

    void initDate(String dateStr);

    void initMarker(String items);

    String getpId();
}
