package com.mycentre.ui.presenter;

import android.view.View;

import com.google.gson.Gson;
import com.mycentre.R;
import com.mycentre.api.RetrofitHelper;
import com.mycentre.request.UserChangePhoneReq;
import com.mycentre.ui.mvpview.MyCenterChangePhoneNumberView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ToastUtil;

/**
 * 作者：Laughing on 2018/5/23 17:24
 * 邮箱：719240226@qq.com
 */

public class CenterChangePhoneNumberPresenter {
    private MyCenterChangePhoneNumberView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public CenterChangePhoneNumberPresenter(MyCenterChangePhoneNumberView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View v) {
        if (v.getId() == R.id.tv_submit) {
            if (v.getId() == R.id.tv_submit) {
               mView.doVerification();//效验手机号
            }
        }
    }

    public void doChangePhoneNum(UserChangePhoneReq registerReq) {

        // 修改手机号
        LogUtil.d("TAG", "---" + new Gson().toJson(registerReq));

        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().changePhoneNum(registerReq)
                , new DefaultObserver<Object>() {

                    @Override
                    public void onSuccess(Object response) {
                        ToastUtil.showToast(activity, "修改成功");
                        mView.finishPage();
                    }
                });
    }


}
