package com.project.ui.presenter;

import android.view.View;

import com.project.ui.mvpview.ChooseProjectView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

/**
 * 创建项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class ChooseProjectPresenter {
    private ChooseProjectView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public ChooseProjectPresenter(ChooseProjectView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
//        if (view.getId() == R.id.rl_add_member_group) {
//            //分组管理
//            A_Grouping.startActivity(activity);
//
//        }
    }

}
