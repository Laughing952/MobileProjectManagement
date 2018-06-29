package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.ui.mvpview.GeneralRequestRecordView;
import com.unistrong.working.ui.viewmodel.GeneralApprovalIApprovedVM;
import com.waterbase.ui.BaseActivity;

/**
 * 我提交的-通用申请（取消）
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class GeneralRequestICommitDetailsPresenter {
    private GeneralRequestRecordView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public GeneralRequestICommitDetailsPresenter(GeneralRequestRecordView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
        if (view.getId() == R.id.rl_general_request_i_commit_content) {
            //点击查看-内容
            mView.jump();
        }
    }

    public void create(GeneralApprovalIApprovedVM generalApprovalIApprovedVM) {
        // TODO: 2018/5/4 审批通用申请
        createProject();
        mView.cancelApprovalSuccess();
    }

    private void createProject() {

    }
}
