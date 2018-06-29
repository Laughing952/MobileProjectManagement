package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AMaterialManagementBinding;
import com.project.ui.mvpview.MaterialManagementView;
import com.project.ui.presenter.MaterialManagementrPresenter;
import com.project.ui.viewmodel.AddMemberVM;

/**
 * 项目-物资（管理）MaterialManagement
 * <p>
 * 作者：Laughing on 2018/5/18 13:37
 * 邮箱：719240226@qq.com
 */

public class A_Material_Management extends TitleActivity implements MaterialManagementView {

    private AMaterialManagementBinding mBinding;
    private MaterialManagementrPresenter mPresenter;
    private AddMemberVM mAddMemberVM;

    public static void startActivity(Context context, String pId, String pName) {
        Intent intent = new Intent(context, A_Material_Management.class);
        intent.putExtra("pId", pId);
        intent.putExtra("pName", pName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_material_management);
        initTitle();
        initData();
        initListener();
    }

    private void initData() {
        mAddMemberVM = new AddMemberVM();
        mPresenter = new MaterialManagementrPresenter(this, this, this);
    }

    private void initListener() {
        mBinding.setClick(v -> mPresenter.click(v));
    }

    public void initTitle() {
        setTitleText("物资管理");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("出库");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        A_Outbound.startActivity(this);
    }

    @Override
    public String getPId() {
        return getIntent().getStringExtra("pId");
    }

    @Override
    public String getPName() {
        return getIntent().getStringExtra("pName");
    }
}