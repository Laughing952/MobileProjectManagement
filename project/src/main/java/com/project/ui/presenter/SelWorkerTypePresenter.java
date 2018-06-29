package com.project.ui.presenter;

import com.google.gson.Gson;
import com.project.api.RetrofitHelper;
import com.project.response.WorkerRep;
import com.project.response.WorkerTypeChildRep;
import com.project.response.WorkerTypeRep;
import com.project.ui.mvpview.QualityView;
import com.project.ui.mvpview.SelWorkerTypeView;
import com.project.widget.CNPinyinFactory;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 选择工种
 * Created by Water on 2018/5/14.
 */

public class SelWorkerTypePresenter {

    private static final String TAG = "SelWorkerTypePresenter";

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private SelWorkerTypeView mView;

    private int staterIndex;
    private String date;


    public SelWorkerTypePresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, SelWorkerTypeView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void initData( String childID) {
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().queryWorkType()
                , new DefaultObserver<List<WorkerTypeRep>>() {
                    @Override
                    public void onSuccess(List<WorkerTypeRep> repList) {
                        // 让选中的默认选中
                        if (!StrUtil.isEmpty(childID)) {
                            outterLoop:
                            for (WorkerTypeRep workerTypeRep : repList) {
                                for (WorkerTypeChildRep childRep : workerTypeRep.getChildRepList()) {
                                    if (childID.equals(childRep.getChildId())) {
                                        childRep.setSel(true);
                                        break outterLoop;
                                    }
                                }
                            }
                        }
                        mView.response(repList);
                    }
                });
    }
}