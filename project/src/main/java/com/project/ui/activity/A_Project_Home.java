package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.bean.ActionBean;
import com.project.databinding.AProjectHomeBinding;
import com.project.response.ProjectHomeRep;
import com.project.ui.adapter.Adapter_Action;
import com.project.ui.mvpview.ProjectHomeView;
import com.project.ui.presenter.ProjectHomePresenter;
import com.project.ui.viewmodel.ProjectHomeVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目详情页面
 * Created by Water on 2018/5/7.
 */

public class A_Project_Home extends TitleActivity implements ProjectHomeView<ProjectHomeRep> {

    private AProjectHomeBinding binding;
    private Adapter_Action adapterApply;
    private Adapter_Action adapterMsg;
    private ProjectHomePresenter presenter;
    private String pId;
    private String pName;

    public static void startActivity(Context context, String pId, String pName) {
        Intent intent = new Intent(context, A_Project_Home.class);
        intent.putExtra("pId", pId);
        intent.putExtra("pName", pName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        pId = getIntent().getStringExtra("pId");
        pId = "1";
        pName = getIntent().getStringExtra("pName");
        setTitleText(pName);
        binding = setView(R.layout.a_project_home);
        initView();
        initTittle();
        initListerner();
        initData();
    }

    private void initTittle() {
//        setRightOneImageVisibity(true);
//        setRightOneImagePic(R.mipmap.ic_add);
    }


    protected void initView() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            // TODO: 2018/5/7 刷新的方法
            presenter.request(pId);

        });
        binding.rvApply.setLayoutManager(new GridLayoutManager(this, 4));
        adapterApply = new Adapter_Action();
        binding.rvApply.setAdapter(adapterApply);
        binding.rvMsg.setLayoutManager(new GridLayoutManager(this, 4));
        adapterMsg = new Adapter_Action();
        binding.rvMsg.setAdapter(adapterMsg);
    }

    private void initListerner() {
        binding.setClick(v -> {
            if (v.getId() == R.id.ll_designSchedule) {
                // TODO: 2018/5/7 计划进度
            } else if (v.getId() == R.id.ll_manpower) {
                // TODO: 2018/5/7 人工 
            } else if (v.getId() == R.id.ll_safetyAbarbeitung) {
                // TODO: 2018/5/7 安全整改
            } else if (v.getId() == R.id.ll_qualityAbarbeitung) {
                // TODO: 2018/5/7 质量整改
            }
        });
        adapterApply.setItemClickListener(new ItemClickListener<ActionBean>() {
            @Override
            public void itemClick(View v, ActionBean action, int index) {
                switch (action.getId()) {
                    case 1: // 质量
                        A_Quality.startActivity(A_Project_Home.this, pId, pName);
                        break;
                    case 2: // 记工
                        A_Record_Merit.startActivity(A_Project_Home.this, pId, pName);
                        break;
                    case 3: // 物资
                        A_Material_Management.startActivity(A_Project_Home.this, pId, pName);
                        break;
                }
            }

        });

        adapterMsg.setItemClickListener(new ItemClickListener<ActionBean>() {
            @Override
            public void itemClick(View v, ActionBean action, int index) {
                switch (action.getId()) {
                    case 1: // todo 任务
                        break;
                }
            }
        });
    }


    private void initData() {
        List<ActionBean> applyActionList = new ArrayList<>();
        applyActionList.add(new ActionBean(1, "质量", R.mipmap.quality));
        applyActionList.add(new ActionBean(2, "记工", R.mipmap.record_merit));
        applyActionList.add(new ActionBean(3, "物资", R.mipmap.material_management));
        adapterApply.setData(applyActionList);
        List<ActionBean> msgActionList = new ArrayList<>();
        msgActionList.add(new ActionBean(1, "任务", R.mipmap.address_book));
        adapterMsg.setData(msgActionList);
        presenter = new ProjectHomePresenter(this, this, this);
        presenter.request(pId);
    }

    @Override
    public void callBack(ProjectHomeRep projectHomeRep) {
        binding.swipeRefreshLayout.setRefreshing(false);
        binding.setProjectHomeVM(new ProjectHomeVM(projectHomeRep));
    }
}
