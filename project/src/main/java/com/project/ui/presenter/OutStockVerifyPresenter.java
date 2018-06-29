package com.project.ui.presenter;

import android.content.Intent;
import android.view.View;

import com.global.even.RefreshEven;
import com.global.listener.ItemClickListener;
import com.global.util.PickerViewUtil;
import com.google.gson.Gson;
import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.request.MaterialsReq;
import com.project.request.OutStockVerifyReq;
import com.project.response.MaterialsRep;
import com.project.response.WorkerRep;
import com.project.ui.activity.A_Editext;
import com.project.ui.adapter.Adapter_Dialog_Worker;
import com.project.ui.mvpview.OutStockVerifyView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.DateUtil;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;
import com.waterbase.widget.ListDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 出库:确认信息
 * Created by Water on 2018/5/24.
 */

public class OutStockVerifyPresenter {

    private static final String TAG = "OutStockVerifyPresenter";

    private OutStockVerifyView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    private OutStockVerifyReq verifyReq;
    private List<WorkerRep> workerRepList;

    public OutStockVerifyPresenter(OutStockVerifyView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        verifyReq = new OutStockVerifyReq();
        String dateStr = DateUtil.getCurrentDate("yyyy-MM-dd");
        verifyReq.setDate(dateStr);
        mView.initDate(dateStr);
    }

    public void click(View v) {
        if (v.getId() == R.id.rl_sel_date) {
            PickerViewUtil.showSelectDatePickerViewBefore(activity, "选择时间"
                    , dateStr -> {
                        verifyReq.setDate(dateStr);
                        mView.initDate(dateStr);
                    });
        } else if (v.getId() == R.id.tv_sel_receiver) {
            selReceiver();
        } else if (v.getId() == R.id.tv_marker) {
            A_Editext.startActivityForResult(activity, "编辑备注", verifyReq.getMarker(), null, 669);
        }
    }

    private void selReceiver() {
        // TODO: 2018/5/24 获取领料人数据
        if (workerRepList == null || workerRepList.isEmpty()) {
            HttpManager manager = new HttpManager(activity, lifecycleProvider);
            manager.doHttpDeal(RetrofitHelper.getApiService().workersList(mView.getpId())
                    , new DefaultObserver<List<WorkerRep>>() {
                        @Override
                        public void onSuccess(List<WorkerRep> repList) {
                            workerRepList = repList;
                            showDialog();
                        }
                    });
        }

    }

    private void showDialog() {
        Adapter_Dialog_Worker adapterDialogWorker = new Adapter_Dialog_Worker();

        adapterDialogWorker.setData(workerRepList);
        ListDialog listDialog = new ListDialog.Builder(activity)
                .setTitle("请选择领料人")
                .setAdapter(adapterDialogWorker)
                .create();
        listDialog.show();
        adapterDialogWorker.setItemClickListener(new ItemClickListener<WorkerRep>() {
            @Override
            public void itemClick(View v, WorkerRep rep, int index) {
                mView.initReceiver(rep.getName());
                listDialog.dismiss();
                verifyReq.setReceiver(rep.getWorkweId());
            }
        });
    }

    /**
     * TODO: 2018/5/24 提交
     *
     * @param pId              项目id
     * @param materialsRepList 物资列表
     */
    public void submit(String pId, List<MaterialsReq> materialsRepList) {
        if (StrUtil.isEmpty(verifyReq.getReceiver())) {
            ToastUtil.showToast(activity, "选择领料人");
            return;
        }
        verifyReq.setpId(pId);
        verifyReq.setMaterialsRepList(materialsRepList);
//        verifyReq.setReceiver(receiver);
        LogUtil.d(TAG, "REQ: " + new Gson().toJson(verifyReq));
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().outStock(verifyReq)
                , new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        activity.finish();
                        EventBus.getDefault().post(new RefreshEven());
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 669 && resultCode == activity.RESULT_OK && data != null) {
            String items = data.getStringExtra("content");
            verifyReq.setMarker(items);
            mView.initMarker(items);
        }
    }
}
