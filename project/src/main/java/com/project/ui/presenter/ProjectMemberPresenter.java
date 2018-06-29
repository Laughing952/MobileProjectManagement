package com.project.ui.presenter;

import android.view.View;

import com.project.api.RetrofitHelper;
import com.project.ui.mvpview.ProjectMemberView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;

import java.util.List;

/**
 * 创建项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class ProjectMemberPresenter {
    private ProjectMemberView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public ProjectMemberPresenter(ProjectMemberView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }
    public void click(View view) {
//        if (view.getId() == R.id.rl_create_project_member) {
//            //项目成员
//            A_Project_Member.startActivity(activity);
//
//        }
    }

}
