package com.project.ui.presenter;

import android.view.View;

import com.project.ui.mvpview.GroupingView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

/**
 * 项目-创建项目-添加成员-分组管理
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class GroupingPresenter {
    private GroupingView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public GroupingPresenter(GroupingView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
//        if (view.getId() == R.id.rl_create_project_member) {
//            //项目成员
//            A_Create_Project_Member.startActivity(activity);
//
//        }
    }
}
