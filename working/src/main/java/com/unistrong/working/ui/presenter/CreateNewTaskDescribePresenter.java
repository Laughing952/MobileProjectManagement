package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.ui.mvpview.CreateNewTaskDescribeView;
import com.waterbase.ui.BaseActivity;

/**
 * 创建任务-任务描述
 * 作者：Laughing on 2018/5/13 17:05
 * 邮箱：719240226@qq.com
 */

public class CreateNewTaskDescribePresenter {
    private CreateNewTaskDescribeView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public CreateNewTaskDescribePresenter(CreateNewTaskDescribeView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
        if (view.getId() == R.id.tv_create_new_task_describe_confirm) {
            //确认按钮
            mView.confirm();
        }
    }


}
