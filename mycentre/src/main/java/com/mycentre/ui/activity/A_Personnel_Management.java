package com.mycentre.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.RecyclerviewBinding;
import com.mycentre.ui.adapter.Adapter_Personnel_Management;
import com.mycentre.ui.mvpview.PersonnelManagementView;
import com.mycentre.ui.presenter.PersonnelManagementPresenter;
import com.mycentre.response.PersonnelManagementRep;

import java.util.List;

/**
 * 人员管理
 * Created by Water on 2018/5/8.
 */

public class A_Personnel_Management extends TitleActivity implements PersonnelManagementView<List<PersonnelManagementRep>> {


    private RecyclerviewBinding binding;
    private PersonnelManagementPresenter presenter;
    private Adapter_Personnel_Management mAdapter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Personnel_Management.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText("人员管理");
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_add);
        binding = setView(R.layout.recyclerview);
        initView();
        initData();
    }

    private void initView() {
        binding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.request();
            }
        });
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new Adapter_Personnel_Management();
        mAdapter.setClickListener(new Adapter_Personnel_Management.ClickListener<PersonnelManagementRep>() {
            @Override
            public void updata(View item, PersonnelManagementRep rep, int index) {
                // 修改
                A_New_Personnel.startActivityForResult(A_Personnel_Management.this, rep.getUserId(), A_New_Personnel.UPDATA);
            }

            @Override
            public void del(View item, PersonnelManagementRep rep, int index) {
                new AlertDialog.Builder(A_Personnel_Management.this)
                        .setTitle("提示：")
                        .setMessage("您将删除该项人员?")
                        .setPositiveButton("确定", (dialog, which) -> {
                            presenter.del(rep);
                        })
                        .setNegativeButton("取消", null)
                        .create()
                        .show();
            }
        });
        binding.recyclerview.setAdapter(mAdapter);
    }

    private void initData() {
        presenter = new PersonnelManagementPresenter(this, this, this);
        presenter.request();
    }

    @Override
    protected void rightOneImageOnClick() {
        A_New_Personnel.startActivityForResult(this, A_New_Personnel.NEW);
    }

    @Override
    public void response(List<PersonnelManagementRep> repList) {
        binding.swiperefreshlayout.setRefreshing(false);
        mAdapter.setData(repList);
    }

    @Override
    public void delSucceed(PersonnelManagementRep rep) {
        mAdapter.removeData(rep);
    }
}
