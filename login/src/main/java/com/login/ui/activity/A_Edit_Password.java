package com.login.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.login.R;
import com.login.bean.UserInfo;
import com.login.databinding.AEditPasswordBinding;
import com.login.ui.mvpview.EditPasswordView;
import com.login.ui.presenter.EditPasswordPresenter;
import com.waterbase.utile.ToastUtil;

/**
 * 修改密码
 * Created by Laughing on 2018/5/23.
 */

public class A_Edit_Password extends TitleActivity implements EditPasswordView {

    private AEditPasswordBinding binding;
    private EditPasswordPresenter mPresenter;
    private UserInfo userInfo;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Edit_Password.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setView(R.layout.a_edit_password);
        setTitleText("修改密码");
        initData();
        initListener();
    }

    private void initData() {
        userInfo = new UserInfo();
        binding.setLoginInfo(userInfo);
        mPresenter = new EditPasswordPresenter(this, this, this);

    }

    private void initListener() {
        binding.setClick(v -> {
            if (v.getId() == R.id.tv_edit_password_commit) {
                // 提交
                mPresenter.editPassword(userInfo.getPhoneNum(), userInfo.getPassword()
                        , userInfo.getConfirmPassword(), userInfo.getCode(), userInfo.getOldPassword());
            }
        });
    }

    @Override
    public void showLoading() {
        proDia.show();
    }

    @Override
    public void hideLoading() {
        proDia.dismiss();
    }

    @Override
    public void getCodeSucceed() {
        ToastUtil.showToast(this, "验证码已发送，注意查收");
    }

    @Override
    public void registerSucceed(Object o) {
        ToastUtil.showToast(this, "密码修改成功");
        Intent intent = null;
        try {
            intent = new Intent(this, Class.forName("com.unistrong.mobileprojectmanagement.ui.activity.A_Welcome"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }

    @Override
    public void showFailureMessage(String msg) {
        ToastUtil.showToast(this, msg);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtil.showToast(this, msg);
    }

    @Override
    public void checkPhoneNumSucceed() {

        binding.tilEditPasswordPhone.setErrorEnabled(false);
    }

    @Override
    public void checkPhoneNumFailure(String msg) {
        binding.tilEditPasswordPhone.setErrorEnabled(true);
        binding.tilEditPasswordPhone.setError(msg);
    }

    @Override
    public void checkPsw1Succeed() {

        binding.tilEditPasswordPassword1.setErrorEnabled(false);
    }

    @Override
    public void checkPsw1Failure(String msg) {
        binding.tilEditPasswordPassword1.setErrorEnabled(true);
        binding.tilEditPasswordPassword1.setError(msg);
    }

    @Override
    public void checkPsw2Succeed() {
        binding.tilEditPasswordPassword2.setErrorEnabled(false);
    }

    @Override
    public void checkPsw2Failure(String msg) {
        binding.tilEditPasswordPassword2.setErrorEnabled(true);
        binding.tilEditPasswordPassword2.setError(msg);
    }

    @Override
    public void countdown() {
//        time = 60;
//        mHandler.post(mRunnable);
//        isGet = false;
    }
}
