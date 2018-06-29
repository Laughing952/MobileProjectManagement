package com.mycentre.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.AMyCenterAccountSettingsBinding;
import com.mycentre.ui.mvpview.AccountSettingView;
import com.mycentre.ui.presenter.AccountSettingPresenter;
import com.mycentre.ui.viewmodel.MyCenterAccountSettingVM;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.waterbase.http.HttpManager;
import com.waterbase.utile.ToastUtil;

/**
 * 个人中心-账号设置
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_My_Center_Account_Settings extends TitleActivity implements AccountSettingView {

    private AMyCenterAccountSettingsBinding mBinding;
    private Context mContext = this;
    private AccountSettingPresenter mAccountSettingPresenter;
    private MyCenterAccountSettingVM mMyCenterAccountSettingVM;
    private String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, //写存储
            Manifest.permission.READ_EXTERNAL_STORAGE,}; //读存储

    private AlertDialog.Builder builderDownLoadAPK;
    private AlertDialog.Builder builderExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_my_center_account_settings);
        initTitle();
        initData();
        initListener();
    }

    private void initData() {
        mAccountSettingPresenter = new AccountSettingPresenter(this, this, this);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_My_Center_Account_Settings.class));
    }

    public void initTitle() {
        setTitleText("账号设置");//标题
    }

    private void initListener() {
        mBinding.setClick(v -> mAccountSettingPresenter.click(v));


    }

    @Override
    public void showDownLoadAPKDialog(String strUrl) {

        if (builderDownLoadAPK == null) {
            builderDownLoadAPK = new AlertDialog.Builder(this);
            builderDownLoadAPK.setCancelable(false);
            builderDownLoadAPK.setTitle(R.string.apk_refresh);
            builderDownLoadAPK.setMessage(R.string.new_apk_refresh);
            // 更新
            builderDownLoadAPK.setPositiveButton(R.string.refresh, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    // 显示下载对话框
                    Intent serviceIntent = null;
                    try {
                        serviceIntent = new Intent(A_My_Center_Account_Settings.this, Class.forName("com.login.api.S_DownLoadApp"));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    serviceIntent.putExtra("url", strUrl);
                    ToastUtil.showToast(mContext, "开始下载");
                    startService(serviceIntent);
                }
            });
            // 稍后更新
            builderDownLoadAPK.setNegativeButton(R.string.refresh_late, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderDownLoadAPK.create();
        }
        builderDownLoadAPK.show();


    }

    @Override
    public void doNotUpdate() {
        ToastUtil.showToast(mContext, "已经是最新版本");
    }


    @Override
    public void updateApp() {
        RxPermissions rxPermissions = new RxPermissions(A_My_Center_Account_Settings.this);
        rxPermissions
                .request(perms)
                .subscribe(granted -> {
                    if (granted) {
                        startDownload();// 开始下载
                    } else {
                        ToastUtil.showToast(mContext, "您拒绝了下载权限");

                    }
                });

    }

    /**
     * 开始下载
     */
    private void startDownload() {
        HttpManager manager = new HttpManager(this, this);
        mMyCenterAccountSettingVM = new MyCenterAccountSettingVM(manager, this);
        mMyCenterAccountSettingVM.checkVersion();
    }
}
