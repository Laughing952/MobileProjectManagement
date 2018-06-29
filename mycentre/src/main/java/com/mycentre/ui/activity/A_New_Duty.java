package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.ANewDutyBinding;
import com.mycentre.response.DutyRep;
import com.mycentre.ui.adapter.Adapter_New_Duty;
import com.mycentre.ui.mvpview.NewDutyView;
import com.mycentre.ui.presenter.NewDutyPresenter;
import com.mycentre.ui.viewmodel.JurisdictionVM;
import com.waterbase.utile.ToastUtil;

import java.util.List;

/**
 * 新建/修改职务
 * Created by Water on 2018/5/9.
 */

public class A_New_Duty extends TitleActivity implements NewDutyView {

    public static final int NEW = 67;
    public static final int UPDATA = 68;

    private int flag;
    private ANewDutyBinding binding;
    private Adapter_New_Duty adapterNewDuty;
    private NewDutyPresenter presenter;

    public static void startActivity(Context context, int flag) {
        startActivity(context, null, flag);
    }

    public static void startActivity(Context context, DutyRep dutyRep, int flag) {
        Intent intent = new Intent(context, A_New_Duty.class);
        intent.putExtra("flag", flag);
        intent.putExtra("dutyRep", dutyRep);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_ok);
        flag = getIntent().getIntExtra("flag", -1);
        if (flag == NEW) {
            setTitleText("新建职务");
        } else if (flag == UPDATA) {
            setTitleText("修改职务");
        }
        binding = setView(R.layout.a_new_duty);
        initView();
        initData();
    }

    private void initView() {
        binding.rvJurisdiction.setLayoutManager(new LinearLayoutManager(this));
        adapterNewDuty = new Adapter_New_Duty();
        binding.rvJurisdiction.setAdapter(adapterNewDuty);
    }

    private void initData() {
        presenter = new NewDutyPresenter(this, this, this);
        if (flag == NEW) {
            presenter.initData();
        } else if (flag == UPDATA) {
            presenter.initData((DutyRep) getIntent().getSerializableExtra("dutyRep"));
        }
    }

    @Override
    public void showData(List<JurisdictionVM> vmList) {
        adapterNewDuty.setData(vmList);
    }

    @Override
    public void addResponse() {
        ToastUtil.showToast(this, "新增成功");
        finish();
    }

    @Override
    public void updataResponse() {
        ToastUtil.showToast(this, "修改成功");
        finish();
    }

    @Override
    public void showName(String name) {
        binding.rtDutyName.setText(name);
    }

    @Override
    protected void rightOneImageOnClick() {
        String dutyName = binding.rtDutyName.getText().toString();
        presenter.request(dutyName, flag);
    }
}
