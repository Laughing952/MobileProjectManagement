package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ACreateNewTaskDescribeBinding;
import com.unistrong.working.inter.Back;
import com.unistrong.working.ui.mvpview.CreateNewTaskDescribeView;
import com.unistrong.working.ui.presenter.CreateNewTaskDescribePresenter;

/**
 * 创建任务-任务描述
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_Create_New_Task_Describe extends TitleActivity implements CreateNewTaskDescribeView {

    private CreateNewTaskDescribePresenter mCreateNewTaskDescribePresenter;
    private ACreateNewTaskDescribeBinding mBinding;
    private Context mContext = this;
    private Back mBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_create_new_task_describe);
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initView() {

    }

    private void initData() {
        mCreateNewTaskDescribePresenter = new CreateNewTaskDescribePresenter(this, this, this);

    }

    private void initListener() {
        mBinding.setClick(v -> mCreateNewTaskDescribePresenter.click(v));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Create_New_Task_Describe.class));
    }

    public void initTitle() {
        setTitleText("创建任务-任务描述");//标题
    }

    @Override
    public void confirm() {
        Intent intent = getIntent();
        intent.putExtra("Create_New_Task_Describe", mBinding.etCreateNewTaskDescribeDes.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }


}
