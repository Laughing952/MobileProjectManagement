package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AGroupingBinding;
import com.project.ui.mvpview.GroupingView;
import com.project.ui.presenter.GroupingPresenter;
import com.waterbase.utile.ToastUtil;

/**
 * 项目-创建项目-添加成员-分组管理
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Grouping extends TitleActivity implements GroupingView {

    private AGroupingBinding mBinding;
    private GroupingPresenter mGroupingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_grouping);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mGroupingPresenter = new GroupingPresenter(this, this, this);

    }

    private void initListener() {

        mBinding.setClick(v -> mGroupingPresenter.click(v));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Grouping.class));
    }

    public void initTitle() {
        setTitleText("分组管理");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("完成");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        ToastUtil.showToast(this, "完成");
    }
}
