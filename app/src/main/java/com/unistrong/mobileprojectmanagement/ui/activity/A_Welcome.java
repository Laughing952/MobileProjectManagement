package com.unistrong.mobileprojectmanagement.ui.activity;

import android.os.Bundle;

import com.login.ui.activity.A_Login;
import com.unistrong.mobileprojectmanagement.R;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.PreferencesManager;

public class A_Welcome extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_welcome);
//        String token = PreferencesManager.getInstance(BaseApplication.getAppContext()).get("token");
        String userId = PreferencesManager.getInstance(BaseApplication.getAppContext()).get("userId");
//        if (StrUtil.isEmpty(userId)) {
        if (false) A_Login.startActivity(this, false);
        else A_Home.startActivity(this);
        finish();
    }
}
