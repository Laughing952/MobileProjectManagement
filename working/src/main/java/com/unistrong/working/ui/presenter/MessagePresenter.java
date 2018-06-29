package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.ui.mvpview.MessageView;
import com.waterbase.ui.BaseActivity;

/**
 * 消息
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class MessagePresenter {
    private MessageView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public MessagePresenter(MessageView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

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
