package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AAddMemberBinding;
import com.project.ui.mvpview.AddMemberView;
import com.project.ui.presenter.AddMemberPresenter;
import com.project.ui.viewmodel.AddMemberVM;
import com.waterbase.utile.ToastUtil;

/**
 * 项目-创建项目-添加成员
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Add_Member extends TitleActivity implements AddMemberView {

    private AAddMemberBinding mBinding;
    private AddMemberPresenter mAddMemberPresenter;
    private AddMemberVM mAddMemberVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_add_member);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mAddMemberVM = new AddMemberVM();
        mAddMemberPresenter = new AddMemberPresenter(this, this, this);
        mBinding.setViewmodel(mAddMemberVM);
    }

    private void initListener() {

        mBinding.setClick(v -> mAddMemberPresenter.click(v));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Add_Member.class));
    }

    public void initTitle() {
        setTitleText("添加成员");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("确定");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        mAddMemberPresenter.Add();
    }

    @Override
    public AddMemberVM getAddMemberVM() {
        return mAddMemberVM;
    }

    @Override
    public void addMemberSuccess() {
        ToastUtil.showToast(this, "添加成功");
    }
}
