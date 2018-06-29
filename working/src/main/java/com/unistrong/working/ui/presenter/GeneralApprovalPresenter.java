package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.ui.mvpview.GeneralApprovalView;
import com.waterbase.ui.BaseActivity;

/**
 * 创建项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class GeneralApprovalPresenter {
    private GeneralApprovalView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public GeneralApprovalPresenter(GeneralApprovalView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
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

    /**
     * 确定添加成员
     */
    public void Add() {
//        AddMemberVM addMemberVM = mView.getAddMemberVM();
//        if (StrUtil.isEmpty(addMemberVM.getG_name())) {
//            ToastUtil.showToast(activity, "姓名不能为空");
//            return;
//        }
//
//        if (StrUtil.isEmpty(addMemberVM.getG_phone_num())) {
//            ToastUtil.showToast(activity, "手机号不能为空");
//            return;
//        }
//        if (addMemberVM.getG_phone_num().length() != 11) {
//            ToastUtil.showToast(activity, "手机号码长度不够");
//            return;
//        }
//        // TODO: 2018/5/4 添加成员网络请求
//        mView.addMemberSuccess();
    }
}
