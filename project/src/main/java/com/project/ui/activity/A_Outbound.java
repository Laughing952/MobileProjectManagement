package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AOutboundBinding;
import com.project.ui.mvpview.OutboundView;
import com.project.ui.presenter.OutboundPresenter;

/**
 * 项目-出库
 * <p>
 * 作者：Laughing on 2018/5/18 13:37
 * 邮箱：719240226@qq.com
 */

public class A_Outbound extends TitleActivity implements OutboundView {

    private AOutboundBinding mBinding;
    private OutboundPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_outbound);
        initTitle();
        initData();
        initListener();
    }

    private void initData() {
        mPresenter = new OutboundPresenter(this, this, this);
    }

    private void initListener() {

        mBinding.setClick(v -> mPresenter.click(v));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Outbound.class));
    }

    public void initTitle() {
        setTitleText("出库");//标题
//        setRightOneImageVisibity(true);
//        setRightOneImagePic(R.mipmap.ic_add);
    }

//    @Override
//    protected void rightOneImageOnClick() {
//        super.rightOneImageOnClick();
//        ToastUtil.showToast(this, "click");
//
//    }
}
