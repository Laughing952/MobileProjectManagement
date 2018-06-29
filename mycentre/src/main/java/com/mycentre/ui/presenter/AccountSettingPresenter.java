package com.mycentre.ui.presenter;

import android.view.View;

import com.mycentre.R;
import com.mycentre.ui.activity.A_My_Center_Change_Phone_Number;
import com.mycentre.ui.mvpview.AccountSettingView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

/**
 * 账号设置
 * 作者：Laughing on 2018/5/2 13:54
 * 邮箱：719240226@qq.com
 */

public class AccountSettingPresenter {

    private AccountSettingView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public AccountSettingPresenter(AccountSettingView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void click(View view) {
        if (view.getId() == R.id.rl_my_center_setting_change_phone_num) {
            //  更换手机号
            A_My_Center_Change_Phone_Number.startActivity(activity);
        } else if (view.getId() == R.id.rl_my_center_setting_change_update_app) {
            //  版本更新
            mView.updateApp();

        }

    }

}
