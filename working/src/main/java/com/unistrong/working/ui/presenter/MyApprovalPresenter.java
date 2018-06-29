package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.ui.mvpview.MyApprovalView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;

import java.util.List;

/**
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class MyApprovalPresenter {
    private MyApprovalView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public MyApprovalPresenter(MyApprovalView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }
    public void getHttpRequestData(long id){
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
            httpManager.doHttpDeal(RetrofitHelper.getApiService().getRequestApprovalDetails(id),
                    new DefaultObserver<List<GeneralRequestItemDetailRep>>() {
                @Override
                public void onSuccess(List<GeneralRequestItemDetailRep> response) {
                    mView.resultRequestApprovalData(response);
                }
            });
    }
    public void click(View view) {
//        if (view.getId() == R.id.rl_project_program_doing) {
//            //正在执行的项目
//            A_Project_Doing.startActivity(activity);
//        } else if (view.getId() == R.id.rl_project_program_over) {
//            //已完成的项目
//            A_Project_Over.startActivity(activity);
//
//        }
    }
}
