package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.ui.mvpview.IParticipatedDetailsView;
import com.waterbase.ui.BaseActivity;


/**
 * 我参与的-详情
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class IParticipatedDetailsPresenter {
    private IParticipatedDetailsView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public IParticipatedDetailsPresenter(IParticipatedDetailsView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
        if (view.getId() == R.id.tv_i_participated_details_send) {
            //发送任务动态
            mView.sendTaskDynamic();

        }
    }
}
