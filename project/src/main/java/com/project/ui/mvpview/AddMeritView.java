package com.project.ui.mvpview;

import com.project.response.WorkerRep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Water on 2018/5/18.
 */

public interface AddMeritView {

    void initDate(String date);

    void initNormalHours(String normalHours);

    void initWorkerList(List<WorkerRep> workerRepList);

    ArrayList<WorkerRep> getAdapterData();
}
