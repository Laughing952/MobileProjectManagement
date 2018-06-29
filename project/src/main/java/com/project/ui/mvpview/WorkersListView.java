package com.project.ui.mvpview;

import com.project.response.WorkerRep;
import com.project.widget.CNPinyin;

import java.util.List;

/**
 * 我的工人名册
 * Created by Water on 2018/5/15.
 */

public interface WorkersListView {

    void responseList(List<CNPinyin<WorkerRep>> pinyinList);

    void rollRV(int position, int offset);

    String getPId();
}
