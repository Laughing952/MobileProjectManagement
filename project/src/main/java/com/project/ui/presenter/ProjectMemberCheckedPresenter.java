package com.project.ui.presenter;

import android.view.View;

import com.global.util.UserIdUtil;
import com.project.api.RetrofitHelper;
import com.project.response.PersonRep;
import com.project.ui.mvpview.ProjectMemberCheckedView;
import com.project.ui.viewmodel.ProjectMemberCheckedVM;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;

import java.util.ArrayList;

/**
 * 选择项目成员（checkbox）
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class ProjectMemberCheckedPresenter {
    private ProjectMemberCheckedView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private ArrayList<ProjectMemberCheckedVM> vmList;

    public ProjectMemberCheckedPresenter(ProjectMemberCheckedView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
        //        if (view.getId() == R.id.rl_create_project_member) {
        //            //项目成员
        //            A_Project_Member.startActivity(activity);
        //
        //        }
    }

    /**
     * 查询人员信息
     *
     */
    public void queryPersonInfo(ArrayList<PersonRep> mPersonReps) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
//        String userId = PreferencesManager.getInstance(BaseApplication.getAppContext()).get("userId");
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getPersonInfoList(UserIdUtil.getUserIdLong().toString()),
                new DefaultObserver<ArrayList<ProjectMemberCheckedVM>>() {
                    @Override
                    public void onSuccess(ArrayList<ProjectMemberCheckedVM> mProjectMemberCheckedVMS) {
                        if (mPersonReps.isEmpty()) {
                            mView.showData(mProjectMemberCheckedVMS);
                        } else {
                            for (PersonRep data : mPersonReps) {
                                for (ProjectMemberCheckedVM vm : mProjectMemberCheckedVMS) {
                                    if (data.getId().equals(vm.getUserId())) {
                                        vm.setSel(true);
                                        break;
                                    }
                                }
                            }
                            mView.showData(mProjectMemberCheckedVMS);
                        }
                    }
                });
    }

}
