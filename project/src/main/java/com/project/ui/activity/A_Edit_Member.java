package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AEditMemberBinding;
import com.project.ui.mvpview.EditMemberView;
import com.project.ui.presenter.EditMemberPresenter;
import com.project.ui.viewmodel.AddMemberVM;
import com.waterbase.utile.ToastUtil;

/**
 * 项目-创建项目-成员详情（编辑成员）
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Edit_Member extends TitleActivity implements EditMemberView {

    private AEditMemberBinding mBinding;
    private EditMemberPresenter mEditMemberPresenter;
    private AddMemberVM mAddMemberVM;//添加与修改的ViewModel一致

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_edit_member);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mAddMemberVM = new AddMemberVM();
        mEditMemberPresenter = new EditMemberPresenter(this, this, this);
        mBinding.setViewmodel(mAddMemberVM);
    }

    private void initListener() {

        mBinding.setClick(v -> mEditMemberPresenter.click(v));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Edit_Member.class));
    }

    public void initTitle() {
        setTitleText("成员详情");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("修改");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        mEditMemberPresenter.edit();
    }

    @Override
    public AddMemberVM getEditMemberVM() {
        return mAddMemberVM;
    }

    @Override
    public void editMemberSuccess() {
        ToastUtil.showToast(this, "修改成功");
    }
}
