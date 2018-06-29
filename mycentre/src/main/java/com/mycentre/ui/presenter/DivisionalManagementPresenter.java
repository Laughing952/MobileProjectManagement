package com.mycentre.ui.presenter;

import com.mycentre.ui.activity.A_New_Department;
import com.mycentre.ui.mvpview.DivisionalManagementView;
import com.mycentre.response.DivisionalManagementRep;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Water on 2018/5/8.
 */

public class DivisionalManagementPresenter {


    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private DivisionalManagementView mView;
    private List<DivisionalManagementRep> repList;

    public DivisionalManagementPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, DivisionalManagementView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void request() {
        repList = new ArrayList<>();
        repList.add(new DivisionalManagementRep("10001", "办公室1级1", null, 0));
        repList.add(new DivisionalManagementRep("11001", "办公室2级1", "10001", 1));
        repList.add(new DivisionalManagementRep("11101", "办公室3级1", "11001", 2));
        repList.add(new DivisionalManagementRep("11111", "办公室4级1", "11101", 3));
        repList.add(new DivisionalManagementRep("11102", "办公室3级2", "11001", 2));
        repList.add(new DivisionalManagementRep("11002", "办公室2级2", "10001", 1));
        repList.add(new DivisionalManagementRep("10002", "办公室1级2", null, 0));
        mView.callBack(repList);
    }

    public void updata(DivisionalManagementRep rep) {
        DivisionalManagementRep parendRep = new DivisionalManagementRep();
        for (DivisionalManagementRep managementRep : repList) {
            if (managementRep.getId().equals(rep.getParendId())) {
                parendRep = managementRep;
                break;
            }
        }
        A_New_Department.startActivityForResult(activity, parendRep, rep, A_New_Department.UPDATA);
    }

    public void del(DivisionalManagementRep rep, int index) {
        // TODO: 2018/5/8 删除
        mView.delSuccss(rep);
        for (DivisionalManagementRep managementRep : repList) {
            if (StrUtil.isEmpty(managementRep.getParendId()))
                continue;
            if (managementRep.getParendId().equals(rep.getId())) {
                del(managementRep, 0);
//                mView.delSuccss(managementRep);
            }
        }

    }
}
