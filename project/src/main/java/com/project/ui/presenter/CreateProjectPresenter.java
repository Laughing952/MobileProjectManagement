package com.project.ui.presenter;

import android.view.View;

import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.request.CreateProjectReq;
import com.project.response.UserInfoRep;
import com.project.ui.mvpview.CreateProjectView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

/**
 * 创建项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class CreateProjectPresenter {
    private CreateProjectView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public CreateProjectPresenter(CreateProjectView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void click(View view) {
        if (view.getId() == R.id.rl_create_project_member) {
            //进入项目成员列表进行选择
            mView.jumpAndGetData();
        }
    }


    public void create(CreateProjectReq mCreateProjectReq) {
        if (StrUtil.isEmpty(mCreateProjectReq.getItemNote())) {
            ToastUtil.showToast(activity, "项目简称不能为空");
            return;
        }
        if (StrUtil.isEmpty(mCreateProjectReq.getItemName())) {
            ToastUtil.showToast(activity, "项目全称不能为空");
            return;
        }
        if (StrUtil.isEmpty(mCreateProjectReq.getItemHead())) {
            ToastUtil.showToast(activity, "项目负责人不能为空");
            return;
        }

        // 发起网络请求，创建项目
        uploadAddPersonDetailsData(mCreateProjectReq);
    }


    public void uploadAddPersonDetailsData(CreateProjectReq addReq) {
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().createProject(addReq),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.showResult();
                    }
                });
    }

    /**
     * 获取成员列表
     *
     * @param userIdLong
     */
    public void downloadUserInfo(Long userIdLong) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getUserInfo(userIdLong),
                new DefaultObserver<UserInfoRep>() {
                    @Override
                    public void onSuccess(UserInfoRep userInfoRep) {
                        if (userInfoRep != null) {
                            mView.showName(userInfoRep);

                        }
                    }


                });

    }


}
