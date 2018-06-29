package com.mycentre.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.RecyclerviewBinding;
import com.mycentre.ui.adapter.Adapter_Divisional_Management;
import com.mycentre.ui.mvpview.DivisionalManagementView;
import com.mycentre.ui.presenter.DivisionalManagementPresenter;
import com.mycentre.response.DivisionalManagementRep;
import com.waterbase.utile.ToastUtil;

import java.util.List;

/**
 * 部门管理
 * Created by Water on 2018/5/8.
 */

public class A_Divisional_Management extends TitleActivity implements DivisionalManagementView<List<DivisionalManagementRep>> {

    private RecyclerviewBinding binding;
    private DivisionalManagementPresenter presenter;
    private Adapter_Divisional_Management mAdapter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Divisional_Management.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_add);
        setTitleText("部门管理");
        binding = setView(R.layout.recyclerview);
        initView();
        initData();
    }

    protected void initView() {
        binding.swiperefreshlayout.setOnRefreshListener(() -> presenter.request());
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new Adapter_Divisional_Management();
        mAdapter.setClickListener(new Adapter_Divisional_Management.ClickListener<DivisionalManagementRep>() {
            @Override
            public void updata(View item, DivisionalManagementRep rep, int index) {
                // TODO: 2018/5/8  修改
                presenter.updata(rep);
            }

            @Override
            public void del(View item, DivisionalManagementRep rep, int index) {
                // TODO: 2018/5/8  删除
                new AlertDialog.Builder(A_Divisional_Management.this)
                        .setTitle("提示：")
                        .setMessage("您将删除该项以及其子项")
                        .setPositiveButton("确定", (dialog, which) -> {
                            presenter.del(rep, index);
                        })
                        .setNegativeButton("取消", null)
                        .create()
                        .show();
            }
        });
        binding.recyclerview.setAdapter(mAdapter);
    }

    private void initData() {
        presenter = new DivisionalManagementPresenter(this, this, this);
        presenter.request();
    }

    @Override
    public void callBack(List<DivisionalManagementRep> repList) {
        binding.swiperefreshlayout.setRefreshing(false);
        mAdapter.setData(repList);
    }

    @Override
    public void delSuccss(DivisionalManagementRep rep) {
        ToastUtil.showToast(A_Divisional_Management.this, "删除成功");
        mAdapter.removeData(rep);
    }

    @Override
    protected void rightOneImageOnClick() {
        A_New_Department.startActivityForResult(this, A_New_Department.NEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
