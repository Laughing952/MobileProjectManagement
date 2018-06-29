package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ProjectItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.global.util.PickerViewUtil;
import com.global.util.RequestTransformUtil;
import com.global.viewmodel.DeleteAndArchivingViewModel;
import com.project.R;
import com.project.databinding.AProjectOverBinding;
import com.project.response.ProjectDoingRep;
import com.project.ui.adapter.Adapter_Project_Over;
import com.project.ui.mvpview.ProjectOverView;
import com.project.ui.presenter.ProjectOverPresenter;
import com.waterbase.widget.recycleview.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目-已归档的项目
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Project_Over extends TitleActivity implements ProjectOverView<List<ProjectDoingRep>> {

    private AProjectOverBinding mBinding;
    private ProjectOverPresenter mPresenter;
    private Adapter_Project_Over mAdapter;
    private Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_project_over);
        initTitle();
        initData();
        initListener();
    }

    private void initListener() {

        mAdapter.setProjectItemClickListener(new ProjectItemClickListener<ProjectDoingRep>() {
            @Override
            public void leftImageClick(View v, ProjectDoingRep data, int index) {
                // TODO: 2018/5/8 模拟数据
                A_Project_Home.startActivity(A_Project_Over.this, "163", "这是模拟项目");
            }

            @Override
            public void centerContentClick(View v, ProjectDoingRep data, int index) {
                // TODO: 2018/5/8 模拟数据
                A_Project_Home.startActivity(A_Project_Over.this, "163", "这是模拟项目");
            }

            @Override
            public void rightImageClick(View v, ProjectDoingRep data, int index) {
                deleteProject(data);
            }
        });


    }

    private void deleteProject(ProjectDoingRep data) {
        ArrayList<DeleteAndArchivingViewModel> deleteAndArchivingViewModels = RequestTransformUtil.initDeleteData();
        PickerViewUtil.showOptionsPickerView(mContext, "选择操作", new PickerViewUtil.SelectResultListener<DeleteAndArchivingViewModel>() {
            @Override
            public void result(DeleteAndArchivingViewModel model) {
                String id = model.getId();
                if (id.equals("0")) {
                    mPresenter.cancelArchiveProject(data.getItemid());
                }
            }

        }, deleteAndArchivingViewModels);
    }


    private void initData() {
        mAdapter = new Adapter_Project_Over();
        mPresenter = new ProjectOverPresenter(this, this, this);
        mBinding.rvProjectOver.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvProjectOver.setAdapter(mAdapter);
        mPresenter.initData();//首次进入页面加载数据
        mBinding.srlProjectOver.setOnRefreshListener(() -> {
            mPresenter.initData();
        });

        mAdapter.openAutoLoadMore(true);
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMoreData();
            }
        });

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Project_Over.class));
    }

    public void initTitle() {
        setTitleText("已归档的项目");//标题
    }


    @Override
    public void initDataResult(List<ProjectDoingRep> projectDoingReps) {
        mBinding.srlProjectOver.setRefreshing(false);
        mAdapter.setData(projectDoingReps);
    }

    @Override
    public void refreshResult() {
        mPresenter.initData();
    }

    @Override
    public void loadMoreResult(List<ProjectDoingRep> projectDoingReps) {
        if (projectDoingReps == null || projectDoingReps.size() == 0) {
            mAdapter.loadCompleted();
        } else {
            mAdapter.addData(projectDoingReps);
        }
    }
}
