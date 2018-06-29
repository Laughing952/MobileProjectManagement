package com.project.ui.presenter;

import android.app.AlertDialog;
import android.view.View;

import com.global.even.RefreshEven;
import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.response.ImageRep;
import com.project.response.QualityListRep;
import com.project.ui.activity.A_New_Quality;
import com.project.ui.mvpview.QualityDetailsView;
import com.project.ui.mvpview.QualityView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 质量检查详情
 * Created by Water on 2018/5/11.
 */

public class QualityDetailsPresenter {

    private static final String TAG = "QualityDetailsPresenter";

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private QualityDetailsView mView;
    private QualityListRep rep;

    private String p_id;
    private String e_id;

    public QualityDetailsPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, QualityDetailsView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void request(String p_id, String e_id) {
        this.p_id = p_id;
        this.e_id = e_id;
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().queryById(e_id, p_id)
                , new DefaultObserver<QualityListRep>() {
                    @Override
                    public void onSuccess(QualityListRep qualityListRep) {
                        rep = qualityListRep;
                        mView.responseQualityDetails(rep);
                    }
                });
    }

    public void click(View v) {
        int i = v.getId();
        if (i == R.id.ll_del) {
            new AlertDialog.Builder(activity)
                    .setTitle("提示:")
                    .setMessage("您确定要删除该记录?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        del();
                    })
                    .setNegativeButton("取消", (dialog, which) -> {
                    })
                    .create()
                    .show();
        } else if (i == R.id.ll_updata) {
            A_New_Quality.startActivity(activity, p_id, rep, A_New_Quality.UPDATA);
        } else if (i == R.id.ll_abarbeitung) {
            ToastUtil.showToast(activity, "还没实现该功能");
        }
    }

    private void del() {
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().delQuality(e_id)
                , new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        activity.finish();
                        EventBus.getDefault().post(new RefreshEven());
                    }
                });
    }
}
