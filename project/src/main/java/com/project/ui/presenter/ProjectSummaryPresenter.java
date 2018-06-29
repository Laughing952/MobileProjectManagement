package com.project.ui.presenter;

import android.view.View;

import com.global.util.ImageUtils;
import com.global.util.PickerViewUtil;
import com.global.util.UserIdUtil;
import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.response.ImageRep;
import com.project.ui.mvpview.ProjectSummaryView;
import com.project.ui.viewmodel.CreateProjectVM;
import com.project.ui.viewmodel.ProjectMemberCheckedVM;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;

import okhttp3.MultipartBody;

/**
 * 项目概况-可编辑
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class ProjectSummaryPresenter {
    private ProjectSummaryView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private ArrayList<ProjectMemberCheckedVM> itemHeadList;
    private final HttpManager httpManager;

    public ProjectSummaryPresenter(ProjectSummaryView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        httpManager = new HttpManager(activity, lifecycleProvider);
    }

    public void click(View view) {
        if (view.getId() == R.id.iv_project_summary_project_image) {
            //选择图像
            mView.chooseImage();
        } else if (view.getId() == R.id.rl_project_summary_member) {
            //编辑成员
            mView.chooseProjectMember();
        }else if (view.getId() == R.id.rl_project_summary_manager) {
            //编辑负责人
            queryPersonInfo();
        }

    }

    /**
     * 获取编辑项目信息
     *
     */
    public void getEditProjectInfo(String projectId) {
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getEditProjectInfo(projectId),
                new DefaultObserver<CreateProjectVM>() {
                    @Override
                    public void onSuccess(CreateProjectVM response) {
                        mView.resultProjectInfo(response);
                    }
                });
    }

    /**
     * 上传图片
     * */
    public void uploadImage(String imgPath) {
        MultipartBody.Part part = ImageUtils.fileToMultipartBodyPart(imgPath);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadImage(part),
                new DefaultObserver<ImageRep>() {
                    @Override
                    public void onSuccess(ImageRep response) {
                        mView.showUserHeadPhoto(response);
                    }
                });
    }

    /**
     * 查询人员信息
     *
     */
    public void queryPersonInfo() {
//        String userId = PreferencesManager.getInstance(BaseApplication.getAppContext()).get("userId");
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getPersonInfoList(UserIdUtil.getUserIdLong().toString()),
                new DefaultObserver<ArrayList<ProjectMemberCheckedVM>>() {
                    @Override
                    public void onSuccess(ArrayList<ProjectMemberCheckedVM> mProjectMemberCheckedVMS) {
                        itemHeadList=mProjectMemberCheckedVMS;
                        selectItemHead();
                    }
                });
    }

    /**
     * 选择项目负责人
     */
    private void selectItemHead() {
        PickerViewUtil.showOptionsPickerView(activity, "请选择项目负责人", new PickerViewUtil.SelectResultListener<ProjectMemberCheckedVM>() {
            @Override
            public void result(ProjectMemberCheckedVM rep) {
                mView.resultItemHead(rep);
            }
        }, itemHeadList);
    }

    /**
     * 确定添加成员
     */
    public void Add(CreateProjectVM mCreateProjectVM) {
        if (StrUtil.isEmpty(mCreateProjectVM.getItemName())) {
            ToastUtil.showToast(activity, "项目名不能为空");
            return;
        }
        if (StrUtil.isEmpty(mCreateProjectVM.getItemHead())) {
            ToastUtil.showToast(activity, "负责人不能为空");
            return;
        }
        mView.editProject();//提交编辑
    }

    /**
     * 编辑项目信息
     */
    public void commitEditProject(CreateProjectVM mCreateProjectVM) {
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadEditProject(mCreateProjectVM),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.showResult();
                    }
                });
    }
}
