package com.project.ui.presenter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.response.ProjectDoingRep;
import com.project.ui.mvpview.ProjectDoingView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 正在进行的项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class ProjectDoingPresenter {
    private ProjectDoingView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private Map<String, Object> map;
    private int currentPage = 1;
    private int pageSize = 10;
    private final HttpManager httpManager;

    public ProjectDoingPresenter(ProjectDoingView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        httpManager = new HttpManager(activity, lifecycleProvider);
        map = new HashMap<>();
    }

    public void click(View view) {
//        if (view.getId() == R.id.rl_add_member_group) {
//            //分组管理
//            A_Grouping.startActivity(activity);
//
//        }
    }
    /**
     * 归档项目
     */
    public void archiveProject(String projectId){
        AlertDialog dialog1 = new AlertDialog.Builder(activity)
                .setCancelable(false)

                .setMessage("是否归档")
                .setPositiveButton("确定", (dialog, which) -> {
                    dialog.dismiss();
                    uploadArchiveProject(projectId);
                }).setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .create();
        dialog1.show();
        dialog1.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
        dialog1.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(R.color.color_cancel));
    }
    /**
     * 删除项目
     */
    public void deleteProject(ProjectDoingRep data) {
        AlertDialog dialog1 = new AlertDialog.Builder(activity)
        .setCancelable(false)
        .setMessage("删除项目会同步删除与项目有关的任务、报销、申请、合同、记账、发票、记工、" +
                "物资等数据，且数据不可恢复，请慎重选择。确定删除项目"+data.getItemname())
        .setPositiveButton("确定", (dialog, which) -> {
            dialog.dismiss();
            uploadDeleteProject(data.getItemid());
        }).setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
        .create();
        dialog1.show();
        dialog1.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
        dialog1.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(R.color.color_cancel));
    }
    public void uploadDeleteProject(String itemId){
        httpManager.doHttpDeal(RetrofitHelper.getApiService().deleteProject(itemId)
                , new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.refreshResult();
                    }
                });
    }
    public void uploadArchiveProject(String itemId){
        httpManager.doHttpDeal(RetrofitHelper.getApiService().archiveProject(itemId)
                , new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.refreshResult();
                    }
                });
    }

    /**
     * 初始化数据
     */
    public void initData() {
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getProjectDoingData(map)
                , new DefaultObserver<List<ProjectDoingRep>>() {
                    @Override
                    public void onSuccess(List<ProjectDoingRep> involvedInfoRep) {
                        if (currentPage == 1)
                            mView.initDataResult(involvedInfoRep);
                        else
                            mView.loadMoreResult(involvedInfoRep);
                    }
                });
    }

    public void refData() {
        currentPage = 1;
        initData();
    }

    public void loadMoreData() {
        currentPage++;
        initData();
        LogUtil.e("TAG", "laughing-------------------currentPage--->   " + currentPage);
    }

}
