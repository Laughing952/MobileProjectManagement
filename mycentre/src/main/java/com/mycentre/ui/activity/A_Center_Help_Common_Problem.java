package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.ACenterHelpCommonProblemBinding;
import com.mycentre.ui.mvpview.CenterHelpCommonProblemView;
import com.mycentre.ui.presenter.CenterHelpCommonProblemPresenter;

/**
 * 常见问题
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_Center_Help_Common_Problem extends TitleActivity implements CenterHelpCommonProblemView {

    private ACenterHelpCommonProblemBinding mBinding;
    private CenterHelpCommonProblemPresenter mPrensenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_center_help_common_problem);
        initTitle();
        initData();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Center_Help_Common_Problem.class));
    }

    public void initTitle() {
        setTitleText("常见问题");//标题
    }

    private void initData() {
        mPrensenter = new CenterHelpCommonProblemPresenter(this, this, this);
        mBinding.setClick(v -> mPrensenter.click(v));
    }

}
