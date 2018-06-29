package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.request.AlterTaskScheduleReq;
import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.ui.mvpview.SeekBarTaskProgressView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;

import java.util.ArrayList;

/**
 * 任务反馈-进度
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class SeekBarTaskProgressPresenter {
    private SeekBarTaskProgressView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private final HttpManager httpManager;

    public SeekBarTaskProgressPresenter(SeekBarTaskProgressView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        httpManager = new HttpManager(activity, lifecycleProvider);
    }

    public void click(View view) {

    }

    /**
     * 修改任务进度
     */
    public void alterTaskSchedule(AlterTaskScheduleReq alterTaskScheduleReq) {
        httpManager.doHttpDeal(RetrofitHelper.getApiService().alterTaskSchedule(alterTaskScheduleReq),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.confirm();//结束页面
                    }
                });
    }
}
