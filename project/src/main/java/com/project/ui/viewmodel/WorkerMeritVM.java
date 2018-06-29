package com.project.ui.viewmodel;

import com.project.request.WorkerReq;
import com.project.response.WorkerRep;

/**
 * 工人记功
 * Created by Water on 2018/5/18.
 */
public class WorkerMeritVM {

    private WorkerRep workerRep;
    private int index;
    private String name;
    private String hours;

    public WorkerMeritVM(WorkerRep workerRep, int index) {
        this.workerRep = workerRep;
        this.index = index;
    }

    public String getIndex() {
        return String.valueOf(index);
    }

    public String getName() {
        return workerRep.getName();
    }

    public String getHours() {
        return String.format("%.2f", workerRep.getHours());
    }
}
