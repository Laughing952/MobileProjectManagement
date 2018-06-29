package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.AMyCenterHelpCenterBinding;
import com.mycentre.ui.mvpview.MyCenterHelpCenterView;
import com.mycentre.ui.presenter.MyCenterHelpCenterPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 个人中心-帮助中心
 * 作者：Laughing on 2018/5/19 19:53
 * 邮箱：719240226@qq.com
 */

public class A_My_Center_Help_Center extends TitleActivity implements MyCenterHelpCenterView {

    private List<Map<String, Object>> mDatas;
    private AMyCenterHelpCenterBinding mBinding;
    private MyCenterHelpCenterPresenter mPrensenter;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_my_center_help_center);
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initView() {
        mDatas = new ArrayList<>();

    }

    private void initData() {
        mPrensenter = new MyCenterHelpCenterPresenter(this, this, this);
        mBinding.setClick(v -> mPrensenter.click(v));
    }

    private void initListener() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_My_Center_Help_Center.class));
    }

    public void initTitle() {
        setTitleText("帮助中心");//标题
    }


}
