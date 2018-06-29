package com.unistrong.working.ui.presenter;

import android.content.Intent;
import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.activity.A_Punch_Record;
import com.unistrong.working.ui.activity.A_Working_Task;
import com.unistrong.working.ui.mvpview.WorkingView;
import com.waterbase.dialog.DialogUtils;
import com.waterbase.ui.BaseActivity;

/**
 * 工作台
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class WorkingPresenter {
    private WorkingView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public WorkingPresenter(WorkingView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) throws ClassNotFoundException {
        if (view.getId() == R.id.ll_working_general_approval) {
            //通用申请
            A_General_Approval.startActivity(activity);
        } else if (view.getId() == R.id.ll_working_tasks) {
            //工作任务
            A_Working_Task.startActivity(activity);
        } else if (view.getId() == R.id.ll_working_today_punch) {
            //当天打卡
            A_Punch_Record.startActivity(activity);

        } else if (view.getId() == R.id.ll_working_plan) {
            //测试按钮(待处理计划)
            Intent intent = new Intent(activity, Class.forName("com.qrcode.ui.ScanActivity"));

            activity.startActivity(intent);

        } else if (view.getId() == R.id.ll_working_sys_notice) {
            //测试按钮(系统公告)
            final DialogUtils dialogUtils = new DialogUtils();
            dialogUtils.showProgress(activity, "nihao");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dialogUtils.dismissProgress();
                }
            }).start();

        }

    }
}
