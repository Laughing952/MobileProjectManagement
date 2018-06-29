package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AOutStockVerifyBinding;
import com.project.request.MaterialsReq;
import com.project.request.OutStockVerifyReq;
import com.project.ui.adapter.Adapter_Out_Stock_Verify;
import com.project.ui.mvpview.OutStockVerifyView;
import com.project.ui.presenter.OutStockVerifyPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 出库:确认信息
 * Created by Water on 2018/5/24.
 */

public class A_Out_Stock_Verify extends TitleActivity implements OutStockVerifyView {

    private AOutStockVerifyBinding binding;
    private Adapter_Out_Stock_Verify adapterOutStockVerify;
    private OutStockVerifyPresenter presenter;

    private String pId; // 项目id
    private List<MaterialsReq> materialsRepList; // 物资列表


    /**
     * @param context
     * @param pId              项目ID
     * @param materialsRepList 物资列表
     */
    public static void startActivity(Context context, String pId, List<MaterialsReq> materialsRepList) {
        Intent intent = new Intent(context, A_Out_Stock_Verify.class);
        intent.putExtra("pId", pId);
        intent.putExtra("materialsRepList", (ArrayList) materialsRepList);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText("出库:确认信息");
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_ok);
        binding = setView(R.layout.a_out_stock_verify);
        initView();
        initData();
    }

    private void initView() {
        binding.rvOutstockMaterials.setLayoutManager(new LinearLayoutManager(this));
        adapterOutStockVerify = new Adapter_Out_Stock_Verify();
        binding.rvOutstockMaterials.setAdapter(adapterOutStockVerify);
        binding.setClick(v -> presenter.click(v));
    }

    private void initData() {
        presenter = new OutStockVerifyPresenter(this, this, this);
        pId = getIntent().getStringExtra("pId");
        materialsRepList = (ArrayList<MaterialsReq>) getIntent().getSerializableExtra("materialsRepList");
        adapterOutStockVerify.setData(materialsRepList);
        binding.tvNum.setText(String.valueOf(materialsRepList.size()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void rightOneImageOnClick() {
        // 保存
        presenter.submit(pId, materialsRepList);
    }

    @Override
    public String getpId() {
        return pId;
    }

    @Override
    public void initReceiver(String name) {
        binding.etReceiver.setText(name);
    }

    @Override
    public void initDate(String dateStr) {
        binding.tvDate.setText(dateStr);
    }

    @Override
    public void initMarker(String items) {
        binding.tvMarker.setText(items);
    }
}
