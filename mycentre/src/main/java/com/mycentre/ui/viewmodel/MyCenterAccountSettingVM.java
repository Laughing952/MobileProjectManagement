package com.mycentre.ui.viewmodel;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.mycentre.R;
import com.mycentre.api.RetrofitHelper;
import com.mycentre.bean.VersionInfo;
import com.mycentre.ui.mvpview.AccountSettingView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.Utils;

import io.reactivex.Observable;

/**
 * 个人设置VM
 * Created by kb.zhang on 2018/4/13.
 */

public class MyCenterAccountSettingVM {
    private HttpManager manager;
    private AccountSettingView view;

    public MyCenterAccountSettingVM(HttpManager manager, AccountSettingView view) {
        this.manager = manager;
        this.view = view;
    }

    /**
     * 验证版本
     */

    public void checkVersion() {
        String string = BaseApplication.getAppContext().getString(R.string.dialog_check_version);
        Observable<VersionInfo> version = RetrofitHelper.getApiService().getVersion();
        manager.doHttpDeal(string, version,

                new DefaultObserver<VersionInfo>() {
                    @Override
                    public void onSuccess(VersionInfo response) {
                        PackageManager packageManager = BaseApplication.getAppContext().getPackageManager();
                        PackageInfo info;
                        try {
                            /*获取本地版本号*/
                            info = packageManager.getPackageInfo(Utils.getContext().getPackageName(), 0);
                            int versionCode = info.versionCode;
                            if (versionCode < response.getVersionCode()) {//本地版本低于服务器版本，则更新APK
                                view.showDownLoadAPKDialog(response.getAppPath());

                            }else{
                                view.doNotUpdate();
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
