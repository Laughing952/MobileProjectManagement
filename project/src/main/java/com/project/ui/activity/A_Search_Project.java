package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;

import com.global.listener.ProjectItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.global.util.PickerViewUtil;
import com.global.util.RequestTransformUtil;
import com.global.viewmodel.DeleteAndArchivingViewModel;
import com.project.R;
import com.project.databinding.ASearchProjectBinding;
import com.project.response.ProjectDoingRep;
import com.project.ui.adapter.Adapter_Search_Project;
import com.project.ui.mvpview.SearchProjectView;
import com.project.ui.presenter.SearchProjectPresenter;
import com.waterbase.widget.recycleview.IFullSpan;
import com.waterbase.widget.recycleview.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目-搜索项目
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Search_Project extends TitleActivity implements SearchProjectView {

    private ASearchProjectBinding mBinding;
    private SearchProjectPresenter mPresenter;
    private Adapter_Search_Project mAdapter;
    private Context mContext=this;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Search_Project.class));
    }

    public static void startActivityAndFlag(Context context) {
        Intent intent = new Intent(context, A_Search_Project.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_search_project);
        initTitle();
        initData();
        initListener();
    }

    private void initListener() {
        mBinding.setClick(v -> finish());

        mBinding.srlSearchProject.setOnRefreshListener(() -> {
            mPresenter.doHttpRequest();
        });

        mAdapter.openAutoLoadMore(true);
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMoreData();
            }
        });
        mBinding.etSearchProject.setOnKeyListener((v, keyCode, event) -> {
            switch (keyCode){
                case KeyEvent.KEYCODE_ENTER:
                    switch (event.getAction()){
                        case KeyEvent.ACTION_UP:
                            getBoxText();
                            mPresenter.search();
                            break;
                    }
                    break;
            }
            return false;
        });
        mAdapter.setProjectItemClickListener(new ProjectItemClickListener<ProjectDoingRep>() {
            @Override
            public void leftImageClick(View v, ProjectDoingRep doingRep, int index) {
                A_Project_Home.startActivity(A_Search_Project.this, doingRep.getItemid(), doingRep.getItemname());
            }

            @Override
            public void centerContentClick(View v, ProjectDoingRep doingRep, int index) {
                A_Project_Home.startActivity(A_Search_Project.this, doingRep.getItemid(), doingRep.getItemname());

            }

            @Override
            public void rightImageClick(View v, ProjectDoingRep data, int index) {
                deleteAndArchiving(data);
            }
        });
    }

    private void initData() {
        mPresenter = new SearchProjectPresenter(this, this, this);
        mAdapter = new Adapter_Search_Project();
        mBinding.rvSearchProject.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvSearchProject.setAdapter(mAdapter);
    }

    public void initTitle() {
        setTitleText("搜索项目");//标题
    }


    /**
     * 获取搜索框文本内容
     */
    @Override
    public void getBoxText() {
        mPresenter.setStringData(mBinding.etSearchProject.getText().toString().trim());//传递数据到Presenter
    }

    /**
     * 初始化 搜索 返回的数据
     *
     * @param involvedInfoRep
     */
    @Override
    public void initDataResult(List<ProjectDoingRep> involvedInfoRep) {
        mBinding.srlSearchProject.setRefreshing(false);
        mAdapter.setData(involvedInfoRep);
        mBinding.etSearchProject.setText("");//清空搜索框
    }


    @Override
    public void loadMoreResult(List<ProjectDoingRep> involvedInfoRep) {
        if (involvedInfoRep == null || involvedInfoRep.size() == 0) {
            mAdapter.loadCompleted();
        } else {
            mAdapter.addData(involvedInfoRep);//把数据加到原来集合中
        }
    }

    @Override
    public void refreshResult() {
        mPresenter.doHttpRequest();
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
                    A_Project_Summary.startActivity(A_Search_Project.this,data.getItemid());//跳转到项目概况
                } else if (id.equals("1")) {
                    mPresenter.deleteProject(data);
                } else if (id.equals("2")) {
                    mPresenter.archiveProject(data.getItemid());
                }
            }

        }, deleteAndArchivingViewModels);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0 && resultCode==RESULT_OK){
            mPresenter.doHttpRequest();
        }
    }
}
