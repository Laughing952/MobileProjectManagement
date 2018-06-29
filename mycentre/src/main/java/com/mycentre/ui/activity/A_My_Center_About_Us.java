package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.AMyCenterAboutUsBinding;

/**
 * 个人中心-关于我们
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_My_Center_About_Us extends TitleActivity {

    private AMyCenterAboutUsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_my_center_about_us);
        initTitle();

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_My_Center_About_Us.class));
    }

    public void initTitle() {
        setTitleText("关于我们");//标题
    }

}
