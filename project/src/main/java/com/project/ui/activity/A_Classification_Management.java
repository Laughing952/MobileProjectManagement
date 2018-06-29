package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AOutboundRecordsBinding;
import com.project.ui.adapter.Adapter_Outbound_Records;
import com.project.ui.mvpview.ClassificationManagementView;
import com.project.ui.presenter.ClassificationManagementPresenter;
import com.project.ui.viewmodel.OutboundRecordsVM;
import com.waterbase.utile.ToastUtil;

/**
 * 项目-分类管理
 * <p>
 * 作者：Laughing on 2018/5/18 13:37
 * 邮箱：719240226@qq.com
 */

public class A_Classification_Management extends TitleActivity implements ClassificationManagementView {

    private AOutboundRecordsBinding mBinding;
    private ClassificationManagementPresenter mPresenter;
    private OutboundRecordsVM mVM;
    private Adapter_Outbound_Records mAdapter;
    private Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_outbound_records);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mVM = new OutboundRecordsVM();
        mPresenter = new ClassificationManagementPresenter(this, this, this);
        mAdapter = new Adapter_Outbound_Records();
        mBinding.rvOutboundRecords.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvOutboundRecords.setAdapter(mAdapter);
        mPresenter.initData();//首次进入页面加载数据


    }

    private void initListener() {

        mAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void itemClick(View v, Object o, int index) {
                ToastUtil.showToast(mContext, "click");
            }
        });
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Classification_Management.class));
    }

    public void initTitle() {
        setTitleText("分类管理");//标题
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_add);
    }

    @Override
    protected void rightOneImageOnClick() {
        super.rightOneImageOnClick();
        ToastUtil.showToast(mContext, "rightOneImageOnClick");
    }
}
