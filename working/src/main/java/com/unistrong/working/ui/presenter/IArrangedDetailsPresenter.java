package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.ui.mvpview.IArrangedDetailsView;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;

/**
 * 我安排的-详情
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class IArrangedDetailsPresenter {
    private IArrangedDetailsView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public IArrangedDetailsPresenter(IArrangedDetailsView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
        if (view.getId() == R.id.tv_i_arranged_details_send) {
            //发送任务动态
            ToastUtil.showToast(activity, "click");
            mView.sendTaskDynamic();

        }
    }
}
