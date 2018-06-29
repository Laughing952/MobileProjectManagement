package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.bean.ChooseProjectBean;
import com.project.databinding.AChooseProjectBinding;
import com.project.ui.adapter.Adapter_Choose_Project;
import com.project.ui.mvpview.ChooseProjectView;
import com.project.ui.presenter.ChooseProjectPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择项目
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Choose_Project extends TitleActivity implements ChooseProjectView {

    private AChooseProjectBinding mBinding;
    private ChooseProjectPresenter mChooseProjectPresenter;
    private Adapter_Choose_Project mAdapter_choose_project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_choose_project);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mChooseProjectPresenter = new ChooseProjectPresenter(this, this, this);
        mAdapter_choose_project = new Adapter_Choose_Project();
        mBinding.rvChooseProject.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvChooseProject.setAdapter(mAdapter_choose_project);
        List<ChooseProjectBean> chooseProjectBean = new ArrayList<>();
        chooseProjectBean.add(null);
        chooseProjectBean.add(null);
        chooseProjectBean.add(null);
        chooseProjectBean.add(null);
        mAdapter_choose_project.setData(chooseProjectBean);
    }

    private void initListener() {

        mBinding.setClick(v -> mChooseProjectPresenter.click(v));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Choose_Project.class));
    }

    public void initTitle() {
        setTitleText("选择项目");//标题

    }

}
