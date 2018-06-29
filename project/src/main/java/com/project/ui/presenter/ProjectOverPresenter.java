package com.project.ui.presenter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.response.ProjectDoingRep;
import com.project.ui.mvpview.ProjectOverView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 已完成的项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class ProjectOverPresenter {
    private ProjectOverView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private Map<String, Object> map;
    private int currentPage = 1;
    private int pageSize = 10;
    private HttpManager httpManager;

    public ProjectOverPresenter(ProjectOverView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        map = new HashMap<>();
        httpManager = new HttpManager(activity, lifecycleProvider);
    }

    public void click(View view) {
//        if (view.getId() == R.id.rl_add_member_group) {
//            //分组管理
//            A_Grouping.startActivity(activity);
//
//        }
    }

    /**
     * 初始化数据
     */
    public void initData() {
        currentPage = 1;
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getProjectOverData(map)
                , new DefaultObserver<List<ProjectDoingRep>>() {
                    @Override
                    public void onSuccess(List<ProjectDoingRep> involvedInfoRep) {
                        mView.initDataResult(involvedInfoRep);
                    }
                });
    }

    /**
     * 归档项目
     */
    public void cancelArchiveProject(String projectId){
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

    public void uploadArchiveProject(String itemId){
        httpManager.doHttpDeal(RetrofitHelper.getApiService().cancelArchiveProject(itemId)
                , new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.refreshResult();
                    }
                });
    }

    public void loadMoreData() {
        currentPage++;
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getProjectOverData(map)
                , new DefaultObserver<List<ProjectDoingRep>>() {
                    @Override
                    public void onSuccess(List<ProjectDoingRep> involvedInfoRep) {
                        mView.loadMoreResult(involvedInfoRep);
                    }
                });
    }

}
