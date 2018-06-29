package com.project.ui.presenter;

import android.view.View;

import com.global.util.PickerViewUtil;
import com.project.R;
import com.project.ui.activity.A_Project_Doing;
import com.project.ui.activity.A_Project_Over;
import com.project.ui.activity.A_Record_Merit;
import com.project.ui.mvpview.ProjectView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;

/**
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class ProjectPresenter {
    private ProjectView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public ProjectPresenter(ProjectView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void click(View view) {
        if (view.getId() == R.id.rl_project_program_doing) {
            //正在执行的项目
            A_Project_Doing.startActivity(activity);
        } else if (view.getId() == R.id.rl_project_program_over) {
            //已完成的项目
            A_Project_Over.startActivity(activity);
        }
    }
}
