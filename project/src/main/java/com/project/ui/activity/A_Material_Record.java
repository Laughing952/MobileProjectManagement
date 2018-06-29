package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AMaterialRecordBinding;
import com.project.response.MaterialsRep;
import com.project.ui.adapter.Adapter_Material_Record;
import com.project.ui.mvpview.MaterialRecordView;
import com.project.ui.presenter.MaterialRecordPrensenter;
import com.project.ui.viewmodel.MaterialsVM;

/**
 * 物资流水
 * Created by Water on 2018/5/23.
 */

public class A_Material_Record extends TitleActivity implements MaterialRecordView {

    private AMaterialRecordBinding binding;
    private Adapter_Material_Record adapterMaterialRecord;
    private MaterialRecordPrensenter prensenter;

    /**
     * @param context
     * @param materialsID 物资ID
     */
    public static void startActivity(Context context, String materialsID) {
        Intent intent = new Intent(context, A_Material_Record.class);
        intent.putExtra("materialsID", materialsID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText("物资流水");
        binding = setView(R.layout.a_material_record);
        initView();
        initData();
    }

    private void initView() {
        binding.rvRecord.setLayoutManager(new LinearLayoutManager(this));
        adapterMaterialRecord = new Adapter_Material_Record();
        binding.rvRecord.setAdapter(adapterMaterialRecord);
    }

    private void initData() {
        prensenter = new MaterialRecordPrensenter(this, this, this);
        prensenter.initData(getIntent().getStringExtra("materialsID"));
    }

    @Override
    public void responseData(MaterialsRep materialsRep) {
        binding.setViewModel(new MaterialsVM(materialsRep));
        adapterMaterialRecord.setData(materialsRep.getRecordRepList());
    }
}
