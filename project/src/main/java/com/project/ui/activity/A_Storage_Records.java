package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AStorageRecordsBinding;
import com.project.ui.adapter.Adapter_Storage_Records;
import com.project.ui.mvpview.StorageRecordsView;
import com.project.ui.presenter.StorageRecordsPresenter;
import com.project.ui.viewmodel.OutboundRecordsVM;
import com.waterbase.utile.ToastUtil;

/**
 * 项目-入库记录
 * <p>
 * 作者：Laughing on 2018/5/18 13:37
 * 邮箱：719240226@qq.com
 */

public class A_Storage_Records extends TitleActivity implements StorageRecordsView {

    private AStorageRecordsBinding mBinding;
    private StorageRecordsPresenter mPresenter;
    private OutboundRecordsVM mVM;// 与出库共用一个ViewModel
    private Adapter_Storage_Records mAdapter;
    private Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_storage_records);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mVM = new OutboundRecordsVM();
        mPresenter = new StorageRecordsPresenter(this, this, this);
        mAdapter = new Adapter_Storage_Records();
        mBinding.rvStorageRecords.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvStorageRecords.setAdapter(mAdapter);
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
        context.startActivity(new Intent(context, A_Storage_Records.class));
    }

    public void initTitle() {
        setTitleText("入库记录");//标题
//        setRightTextViewVisibity(true);
//        setRightTextViewText("确定");
    }

//    @Override
//    protected void rightTextViewOnClick() {
//        super.rightTextViewOnClick();
//    }

}
