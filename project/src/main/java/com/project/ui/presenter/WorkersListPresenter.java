package com.project.ui.presenter;

import com.project.api.RetrofitHelper;
import com.project.response.QualityListRep;
import com.project.response.WorkerRep;
import com.project.ui.mvpview.SearchProjectView;
import com.project.ui.mvpview.WorkersListView;
import com.project.widget.CNPinyin;
import com.project.widget.CNPinyinFactory;
import com.project.widget.Contact;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 我的工人名册
 * Created by Water on 2018/5/15.
 */

public class WorkersListPresenter {

    private List<CNPinyin<WorkerRep>> contactList = new ArrayList<>();

    private WorkersListView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public WorkersListPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, WorkersListView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }


    public void roll(char currentIndex) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getFirstChar() == currentIndex) {
                mView.rollRV(i, 0);
                return;
            }
        }
    }

    public void initData(ArrayList<WorkerRep> selReqList) {

        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().workersList(mView.getPId())
                , new DefaultObserver<List<WorkerRep>>() {
                    @Override
                    public void onSuccess(List<WorkerRep> repList) {
                        // 让选中的默认选中
                        if (selReqList != null) {
                            int size = repList.size();
                            for (WorkerRep rep : selReqList) {
                                for (int i = 0; i < size; i++) {
                                    if (rep.getWorkweId().equals(repList.get(i).getWorkweId())) {
                                        repList.get(i).setHours(rep.getHours());
                                        repList.get(i).setCheck(true);
                                    }
                                }
                            }
                        }
                        if (repList != null && !repList.isEmpty()) {
                            contactList = CNPinyinFactory.createCNPinyinList(repList);
                            Collections.sort(contactList);
                            mView.responseList(contactList);
                        } else
                            mView.responseList(contactList);
                    }
                });
    }
}
