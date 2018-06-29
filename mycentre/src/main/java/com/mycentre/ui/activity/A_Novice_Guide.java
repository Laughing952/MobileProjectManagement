package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.ANoviceGuideBinding;
import com.mycentre.ui.mvpview.NoviceGuideView;
import com.mycentre.ui.presenter.NoviceGuidePresenter;

/**
 * 新手引导
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_Novice_Guide extends TitleActivity implements NoviceGuideView {

    private ANoviceGuideBinding mBinding;
    private NoviceGuidePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_novice_guide);
        initTitle();
        initData();
        initListener();
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Novice_Guide.class));
    }

    public void initTitle() {
        setTitleText("新手引导");//标题
    }

    private void initData() {
        mPresenter = new NoviceGuidePresenter(this, this, this);

    }

    private void initListener() {
        mBinding.setClick(v -> mPresenter.click(v));
    }

}
