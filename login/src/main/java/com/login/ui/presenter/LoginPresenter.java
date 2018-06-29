package com.login.ui.presenter;

import android.util.Log;

import com.login.api.RetrofitHelper;
import com.login.help.CheckHelp;
import com.login.request.UerLoginReq;
import com.login.response.UserLoginRep;
import com.login.ui.mvpview.LoginView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.PreferencesManager;


/**
 * Created by edz on 2018/3/23.
 */

public class LoginPresenter {
    private static final String TAG = "LoginPresenter";
    // View接口
    private LoginView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public LoginPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, LoginView view) {
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        this.mView = view;
    }

    public void login(String phoneNum, String password) {
        Log.d(TAG, "phoneNum:" + phoneNum + "  password:" + password);
        CheckHelp.CheckResult result = null;
        result = CheckHelp.checkPhoneNum(phoneNum);
        if (!result.isSucceed) {
            mView.showFailureMessage(result.msg);
            return;
        }
        result = CheckHelp.checkPassword(password);
        if (!result.isSucceed) {
            mView.showFailureMessage(result.msg);
            return;
        }
        // 登录
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        UerLoginReq loginReq = new UerLoginReq();
        loginReq.setMobile(phoneNum);
        loginReq.setPassword(password);
        manager.doHttpDeal(RetrofitHelper.getApiService().login(loginReq)
                , new DefaultObserver<UserLoginRep>() {
                    @Override
                    public void onSuccess(UserLoginRep response) {
                        LogUtil.d(TAG, "" + response.getToken());
                        mView.showData(response);
                        //登录成功保存 token 与用户信息
                        PreferencesManager preferencesManager = PreferencesManager.getInstance(BaseApplication.getAppContext());
                        preferencesManager.put("token", response.getToken());
                        preferencesManager.put("mobile", response.getMobile());
                        preferencesManager.put("userId", response.getUserId());
                    }
                });
    }

    /**
     * 手机号码校验
     *
     * @param phoneNum 电话号码
     */
    public void checkPhoneNum(String phoneNum) {
        CheckHelp.CheckResult result = CheckHelp.checkPhoneNum(phoneNum);
        if (result.isSucceed)
            mView.checkPhoneNumSucceed();
        else
            mView.checkPhoneNumFailure(result.msg);
    }

    /**
     * 密码格式校验
     *
     * @param psw 密码
     */
    public void checkPassword(String psw) {
        CheckHelp.CheckResult result = CheckHelp.checkPassword(psw);
        if (result.isSucceed)
            mView.checkPasswordSucceed();
        else
            mView.checkPasswordFailure(result.msg);
    }
}
