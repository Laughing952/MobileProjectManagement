package com.login.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;

import com.global.even.FinishEven;
import com.global.even.RefreshEven;
import com.login.R;
import com.login.bean.UserInfo;
import com.login.databinding.ALoginBinding;
import com.login.response.UserLoginRep;
import com.login.ui.mvpview.LoginView;
import com.login.ui.presenter.LoginPresenter;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 登录
 * Created by edz on 2018/3/23.
 */

public class A_Login extends BaseActivity implements LoginView<UserLoginRep> {

    private ALoginBinding binding;
    private UserInfo userInfo;
    private LoginPresenter presenter;

    private boolean isRefresh = false;

    /**
     * @param context
     * @param isRefresh 登录成功后是否要刷新页面，一般在token失效后重新登陆使用这个参数，true为刷新
     */
    public static void startActivity(Context context, boolean isRefresh) {
        Intent intent = new Intent(context, A_Login.class);
        intent.putExtra("isRefresh", isRefresh);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTitleText("登录");
//        binding = setView(R.layout.a_login);
        EventBus.getDefault().register(this);
        binding = DataBindingUtil.setContentView(this, R.layout.a_login);
        presenter = new LoginPresenter(this, this, this);
        userInfo = new UserInfo();
        binding.setLoginInfo(userInfo);
        initListener();
    }

    private void initListener() {
        binding.etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.checkPhoneNum(userInfo.getPhoneNum());
            }
        });
        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.checkPassword(userInfo.getPassword());
            }
        });
        binding.setClick(v -> {
            int i = v.getId();
            if (i == R.id.tv_login) {
                presenter.login(userInfo.getPhoneNum(), userInfo.getPassword());
            } else if (i == R.id.tv_forget_password) {
                A_Forget_Password.startActivity(this);
            } else if (i == R.id.tv_go_register) {
                A_Register.startActivity(this);
            } else if (i == R.id.tv_change_password) {
                A_Edit_Password.startActivity(this);
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
    public void showData(UserLoginRep loginRep) {
        if (getIntent().getBooleanExtra("isRefresh", false)) {
            EventBus.getDefault().post(new RefreshEven());
            finish();
        } else {
            try {
                finish();
                Intent intent = new Intent(this, Class.forName("com.unistrong.mobileprojectmanagement.ui.activity.A_Home"));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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
        binding.tilPhone.setErrorEnabled(false);
    }

    @Override
    public void checkPhoneNumFailure(String msg) {
        binding.tilPhone.setError(msg);
    }

    @Override
    public void checkPasswordSucceed() {
        binding.tilPassword.setErrorEnabled(false);
    }

    @Override
    public void checkPasswordFailure(String msg) {
        binding.tilPassword.setError(msg);
    }

    @Subscribe
    public void event(FinishEven finishEven) {
        showData(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
