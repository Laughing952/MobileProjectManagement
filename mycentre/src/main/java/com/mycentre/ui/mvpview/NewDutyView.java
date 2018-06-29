package com.mycentre.ui.mvpview;

import com.mycentre.ui.viewmodel.JurisdictionVM;

import java.util.List;

/**
 * 新建/修改职务
 * Created by Water on 2018/5/9.
 */

public interface NewDutyView {

    void showData(List<JurisdictionVM> vmList);

    void addResponse();

    void updataResponse();

    void showName(String name);
}
