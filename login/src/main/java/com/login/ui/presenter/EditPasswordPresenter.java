package com.login.ui.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.login.api.RetrofitHelper;
import com.login.help.CheckHelp;
import com.login.request.UserRegisterReq;
import com.login.response.UserLoginRep;
import com.login.ui.mvpview.EditPasswordView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.PreferencesManager;

/**
 * Created by edz on 2018/3/26.
 */

public class EditPasswordPresenter {

    private static final String TAG = "RegisterPresenter";
    // View接口
    private EditPasswordView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public EditPasswordPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, EditPasswordView view) {
        this.mView = view;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void editPassword(String phoneNum, String password, String confirmPassword, String code, String oldPassword) {
        Log.e(TAG, "phoneNum:" + phoneNum + "  password:" + password);
        CheckHelp.CheckResult result = null;
        result = CheckHelp.checkPhoneNum(phoneNum);
        if (!result.isSucceed) {
            mView.showFailureMessage(result.msg);
            return;
        }
        result = CheckHelp.checkPassword(oldPassword);//校验旧密码格式
        if (!result.isSucceed) {
            mView.showFailureMessage(result.msg);
            return;
        }
        result = CheckHelp.checkPassword(password);
        if (!result.isSucceed) {
            mView.showFailureMessage(result.msg);
            return;
        }
        result = CheckHelp.checkPassword(confirmPassword);
        if (!result.isSucceed) {
            mView.showFailureMessage(result.msg);
            return;
        }
        if (!password.equals(confirmPassword)) {
            mView.checkPhoneNumFailure("您输入的密码不一致");
            return;
        }
//        if (StrUtil.isEmpty(code)) {
//            mView.showFailureMessage("验证码不能为空");
//            return;
//        }
        UserRegisterReq registerReq = new UserRegisterReq();
        registerReq.setMobile(phoneNum);
        registerReq.setOldPassword(oldPassword);
        registerReq.setPassword(password);
//        registerReq.setVerifyCode(code);
        // 注册
        LogUtil.d(TAG, "---" + new Gson().toJson(registerReq));

        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().updatePassword(registerReq)
                , new DefaultObserver<UserLoginRep>() {
                    @Override
                    public void onSuccess(UserLoginRep loginRep) {
                        LogUtil.d(TAG, "修改成功");
                        mView.registerSucceed(loginRep);
                        PreferencesManager preferencesManager = PreferencesManager.getInstance(BaseApplication.getAppContext());
                        preferencesManager.put("token", loginRep.getToken());
                        preferencesManager.put("mobile", loginRep.getMobile());
                        preferencesManager.put("userId", loginRep.getUserId());
                    }
                });
    }

    public void getCode(String phoneNum) {
        Log.d(TAG, "phoneNum:" + phoneNum);
        CheckHelp.CheckResult result = CheckHelp.checkPhoneNum(phoneNum);
        if (!result.isSucceed)
            mView.showFailureMessage(result.msg);
        else {
            // 获取验证码
            HttpManager manager = new HttpManager(activity, lifecycleProvider);
            manager.doHttpDeal(RetrofitHelper.getApiService().sms(phoneNum)
                    , new DefaultObserver<String>() {
                        @Override
                        public void onSuccess(String response) {
                            LogUtil.d(TAG, "获取验证按成功" + response);
                            mView.getCodeSucceed();
                            mView.countdown();
                        }
                    });
        }
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
    public void checkPassword1(String psw) {
        CheckHelp.CheckResult result = CheckHelp.checkPassword(psw);
        if (result.isSucceed)
            mView.checkPsw1Succeed();
        else
            mView.checkPsw1Failure(result.msg);
    }

    /**
     * 密码格式校验
     *
     * @param psw 密码
     */
    public void checkPassword2(String psw) {
        CheckHelp.CheckResult result = CheckHelp.checkPassword(psw);
        if (result.isSucceed)
            mView.checkPsw2Succeed();
        else
            mView.checkPsw2Failure(result.msg);
    }
}
