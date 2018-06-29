package com.project.ui.presenter;

import com.project.api.RetrofitHelper;
import com.project.response.ProjectHomeRep;
import com.project.ui.mvpview.ProjectHomeView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.PreferencesManager;

/**
 * 项目详情页面
 * Created by Water on 2018/5/7.
 */

public class ProjectHomePresenter {

    private static final String TAG = "ProjectHomePresenter";

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private ProjectHomeView mView;// View接口

    public ProjectHomePresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, ProjectHomeView mView) {
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        this.mView = mView;
    }

    public void request(String pId) {
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        PreferencesManager preferencesManager = PreferencesManager.getInstance(BaseApplication.getAppContext(), "");
        String userId = preferencesManager.get("userId");
        manager.doHttpDeal(RetrofitHelper.getApiService().projectHome(pId, userId)
                , new DefaultObserver<ProjectHomeRep>() {
                    @Override
                    public void onSuccess(ProjectHomeRep response) {
                        mView.callBack(response);
                    }
                });
    }
}
