package com.mycentre.ui.presenter;

import android.view.View;

import com.mycentre.R;
import com.mycentre.api.RetrofitHelper;
import com.mycentre.ui.activity.A_My_Center_About_Us;
import com.mycentre.ui.activity.A_My_Center_Account_Settings;
import com.mycentre.ui.activity.A_My_Center_Feedback;
import com.mycentre.ui.activity.A_My_Center_Help_Center;
import com.mycentre.ui.activity.A_System_Settings;
import com.mycentre.ui.mvpview.MyCentreView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.ToastUtil;

/**
 * Created by Water on 2018/4/27.
 */

public class MyCentrePresenter {

    private MyCentreView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public MyCentrePresenter(MyCentreView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void click(View view) {
        if (view.getId() == R.id.iv_more) {
            // TODO: 2018/5/8 更多
            A_System_Settings.startActivity(activity);
        } else if (view.getId() == R.id.tv_loginout) {
            //  退出登录
            loginOut();
        } else if (view.getId() == R.id.rl_personal_center_account_settings) {
            //   账号设置
            A_My_Center_Account_Settings.startActivity(activity);
        } else if (view.getId() == R.id.rl_personal_center_help_center) {
            //   帮助中心
            A_My_Center_Help_Center.startActivity(activity);
        } else if (view.getId() == R.id.rl_personal_center_feedback) {
            //  意见反馈
            A_My_Center_Feedback.startActivity(activity);
        } else if (view.getId() == R.id.rl_personal_center_clear_cache) {
            //  清除缓存
            ToastUtil.showToast(activity, "缓存已清除");
        } else if (view.getId() == R.id.rl_personal_center_about_us) {
            //  关于我们
            A_My_Center_About_Us.startActivity(activity);
        } else if (view.getId() == R.id.iv_my_center_head_image) {
            //  (点击头像)跳转到个人信息中心
            mView.jump2PersonalInfoPage();
        } else if (view.getId() == R.id.tv_my_center_personal_info) {
            //  (点击“个人资料”四个字)跳转到个人信息中心
            mView.jump2PersonalInfoPage();
        } else if (view.getId() == R.id.ll_my_center_call_us) {
            //  打电话给我们
            mView.call();

        }

    }

    private void loginOut() {
        mView.loginOutResult();
        PreferencesManager preferencesManager = PreferencesManager.getInstance(activity);
        preferencesManager.remove("userId");
        preferencesManager.remove("mobile");

//        HttpManager manager = new HttpManager(activity, lifecycleProvider);
//        manager.doHttpDeal(RetrofitHelper.getApiService().loginOut()
//                , new DefaultObserver<Object>() {
//                    @Override
//                    public void onSuccess(Object deviceRepList) {
//                        mView.loginOutResult();
//                        PreferencesManager preferencesManager = PreferencesManager.getInstance(activity);
//                        preferencesManager.remove("token");
//                        preferencesManager.remove("mobile");
//                    }
//                });
    }

}
