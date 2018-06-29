package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.AMyCenterChangePhoneNumberBinding;
import com.mycentre.request.UserChangePhoneReq;
import com.mycentre.ui.mvpview.MyCenterChangePhoneNumberView;
import com.mycentre.ui.presenter.CenterChangePhoneNumberPresenter;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

/**
 * 个人中心-关于我们
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_My_Center_Change_Phone_Number extends TitleActivity implements MyCenterChangePhoneNumberView {

    private AMyCenterChangePhoneNumberBinding mBinding;
    private CenterChangePhoneNumberPresenter mPresenter;
    private Context mContext = this;
    private UserChangePhoneReq mViewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_my_center_change_phone_number);

        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mBinding.setViewmodel(new UserChangePhoneReq());

        mPresenter = new CenterChangePhoneNumberPresenter(this, this, this);
    }

    private void initListener() {
        mBinding.setClick(v -> mPresenter.click(v));


    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_My_Center_Change_Phone_Number.class));
    }

    public void initTitle() {
        setTitleText("更换手机号");//标题
    }


    @Override
    public void doVerification() {
        mViewmodel = mBinding.getViewmodel();
        if (!StrUtil.isMobileNo(mViewmodel.getMobile()) || !StrUtil.isMobileNo(mViewmodel.getOldMobile())) {
            ToastUtil.showToast(this, "请检查手机号");

        } else {

            PreferencesManager preferencesManager = PreferencesManager.getInstance(BaseApplication.getAppContext());
            String userId = preferencesManager.get("userId", "");// 获取userId
//        preferencesManager.put("token", loginRep.getToken());
//        preferencesManager.put("mobile", loginRep.getMobile());
//        preferencesManager.put("userId", loginRep.getUserId());
            // TODO: 2018/5/25  userId需要传到服务器

            UserChangePhoneReq registerReq = new UserChangePhoneReq();
//            registerReq.setUserId("76");
            registerReq.setUserId(userId);
            registerReq.setOldMobile(mViewmodel.getOldMobile());//旧手机号
            registerReq.setMobile(mViewmodel.getMobile());//新手机号
            mPresenter.doChangePhoneNum(registerReq);
        }
//
//        if (!StrUtil.isMobileNo(mViewmodel.getOldMobile())) {
//            ToastUtil.showToast(this, "请检查新手机号");
//
//        } else {
//            UserChangePhoneReq registerReq = new UserChangePhoneReq();
//            registerReq.setUserId("76");
//            registerReq.setOldMobile(mViewmodel.getMobile());
//            registerReq.setMobile(mViewmodel.getOldMobile());
//            mPresenter.doChangePhoneNum(registerReq);
//        }

    }

    @Override
    public void finishPage() {
        finish();

    }
}
