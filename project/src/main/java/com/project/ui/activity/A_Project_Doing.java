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
import com.project.databinding.AProjectDoingBinding;
import com.project.response.ProjectDoingRep;
import com.project.ui.adapter.Adapter_Project_Doing;
import com.project.ui.mvpview.ProjectDoingView;
import com.project.ui.presenter.ProjectDoingPresenter;
import com.waterbase.widget.recycleview.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目-正在进行的项目
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Project_Doing extends TitleActivity implements ProjectDoingView<List<ProjectDoingRep>> {

    private AProjectDoingBinding mBinding;
    private ProjectDoingPresenter mPresenter;
    private Adapter_Project_Doing mAdapter;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_project_doing);
        initTitle();
        initData();
        initListener();

    }

    private void initListener() {

        mBinding.srlProjectDoing.setOnRefreshListener(() -> {
            mPresenter.refData();
        });

        mAdapter.openAutoLoadMore(true);

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMoreData();
            }
        });

        mAdapter.setProjectItemClickListener(new ProjectItemClickListener<ProjectDoingRep>() {
            @Override
            public void leftImageClick(View v, ProjectDoingRep doingRep, int index) {
                A_Project_Home.startActivity(A_Project_Doing.this, doingRep.getItemid(), doingRep.getItemname());
            }

            @Override
            public void centerContentClick(View v, ProjectDoingRep doingRep, int index) {
                A_Project_Home.startActivity(A_Project_Doing.this, doingRep.getItemid(), doingRep.getItemname());

            }

            @Override
            public void rightImageClick(View v, ProjectDoingRep data, int index) {
                deleteAndArchiving(data);
            }
        });

    }

    private void initData() {
        mAdapter = new Adapter_Project_Doing();
        mBinding.rvProjectDoing.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvProjectDoing.setAdapter(mAdapter);
        mPresenter = new ProjectDoingPresenter(this, this, this);
        mPresenter.initData();//首次进入页面加载数据

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Project_Doing.class));
    }

    public void initTitle() {
        setTitleText("正在进行的项目");//标题
    }

    /**
     * 删除或归档
     */
    private void deleteAndArchiving(ProjectDoingRep data) {
        ArrayList<DeleteAndArchivingViewModel> deleteAndArchivingViewModels = RequestTransformUtil.initDeleteAndArchivingData();
        PickerViewUtil.showOptionsPickerView(mContext, "选择操作", new PickerViewUtil.SelectResultListener<DeleteAndArchivingViewModel>() {
            @Override
            public void result(DeleteAndArchivingViewModel model) {
                String id = model.getId();
                if (id.equals("0")) {
                    A_Project_Summary.startActivity(A_Project_Doing.this, data.getItemid());//跳转到项目概况
                } else if (id.equals("1")) {
                    mPresenter.deleteProject(data);
                } else if (id.equals("2")) {
                    mPresenter.archiveProject(data.getItemid());
                }
            }

        }, deleteAndArchivingViewModels);
    }

    /**
     * 第一次加载数据或者下拉刷新
     *
     * @param projectDoingReps
     */
    @Override
    public void initDataResult(List<ProjectDoingRep> projectDoingReps) {
        mBinding.srlProjectDoing.setRefreshing(false);
        mAdapter.setData(projectDoingReps);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            mPresenter.refData();
        }
    }

    @Override
    public void refreshResult() {
        mPresenter.refData();
    }

//    @Override
//    public void refreshResult(List<ProjectDoingRep> projectDoingReps) {
//        mAdapter.setData(projectDoingReps);
//        mBinding.srlProjectDoing.setRefreshing(false);
//    }

    /**
     * 加载更多
     *
     * @param projectDoingReps
     */
    @Override
    public void loadMoreResult(List<ProjectDoingRep> projectDoingReps) {
        if (projectDoingReps == null || projectDoingReps.isEmpty()) {
            mAdapter.loadCompleted();//没有数据
            return;
        }
        mAdapter.addData(projectDoingReps);//有数据
    }

}
