package com.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.ASelWorkertypeBinding;
import com.project.response.WorkerTypeChildRep;
import com.project.response.WorkerTypeRep;
import com.project.ui.adapter.Adapter_WorkerType;
import com.project.ui.mvpview.SelWorkerTypeView;
import com.project.ui.presenter.SelWorkerTypePresenter;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;

import java.util.List;

/**
 * 选择工种
 * Created by Water on 2018/5/14.
 */

public class A_Sel_WorkerType extends TitleActivity implements SelWorkerTypeView {

    private ASelWorkertypeBinding binding;
    private Adapter_WorkerType adapterWorkerType;
    private SelWorkerTypePresenter presenter;

    public static void startActivityForResult(BaseActivity activity, String childID, int requestCode) {
        Intent intent = new Intent(activity, A_Sel_WorkerType.class);
        intent.putExtra("childID", childID);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText("工种");
        binding = setView(R.layout.a_sel_workertype);
        initView();
        presenter.initData(getIntent().getStringExtra("childID"));
    }

    private void initView() {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapterWorkerType = new Adapter_WorkerType();
        binding.recyclerview.setAdapter(adapterWorkerType);
        adapterWorkerType.setItemClickListener(new ItemClickListener<WorkerTypeChildRep>() {
            @Override
            public void itemClick(View v, WorkerTypeChildRep childRep, int index) {
                ToastUtil.showToast(A_Sel_WorkerType.this, "childRep:  " + index);
                Intent intent = new Intent();
                intent.putExtra("childId", childRep.getChildId());
                intent.putExtra("childConetnt", childRep.getContent());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        presenter = new SelWorkerTypePresenter(this, this, this);
    }

    @Override
    public void response(List<WorkerTypeRep> typeRepList) {
        adapterWorkerType.setData(typeRepList);
    }
}
