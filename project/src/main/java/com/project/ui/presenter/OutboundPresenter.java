package com.project.ui.presenter;

import android.view.View;

import com.project.ui.mvpview.OutboundView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

/**
 * 项目-物资（管理）MaterialManagement
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class OutboundPresenter {
    private OutboundView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public OutboundPresenter(OutboundView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
//        if (view.getId() == R.id.rl_a_material_management_in_stock) {
//            //库存
//            A_In_Stock.startActivity(activity);
//
//        }
    }


}
