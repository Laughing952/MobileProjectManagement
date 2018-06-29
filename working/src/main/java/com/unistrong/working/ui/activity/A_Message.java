package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter;
import com.unistrong.working.R;
import com.unistrong.working.databinding.AMessageBinding;
import com.unistrong.working.ui.mvpview.MessageView;
import com.unistrong.working.ui.presenter.MessagePresenter;
import com.unistrong.working.ui.viewmodel.MessageVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 消息
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Message extends TitleActivity implements MessageView {


    private List<Map<String, Object>> mDatas;
    private GridViewAddImageAdapter mAdapter;
    private MessagePresenter mPresenter;
    private MessageVM mMessageVM;
    private AMessageBinding mBinding;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_message);
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initView() {
        mDatas = new ArrayList<>();
        mAdapter = new GridViewAddImageAdapter(mDatas, this);
//        mBinding.gvCreateNewTask.setAdapter(mAdapter);

    }

    private void initData() {
        mPresenter = new MessagePresenter(this, this, this);
//        mBinding.setViewmodel(mCreateNewTaskVM);//绑定数据
    }

    private void initListener() {


    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Message.class));
    }

    public void initTitle() {
        setTitleText("消息");//标题
    }

}
