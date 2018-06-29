package com.mycentre.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.ANewPersonnelBinding;
import com.mycentre.response.JurisdictionRep;
import com.mycentre.ui.adapter.Adapter_Jurisdiction;
import com.mycentre.ui.presenter.NewPersonnelPresenter;
import com.mycentre.ui.mvpview.NewPersonnelView;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;

import java.util.List;

/**
 * 新建/编辑人员
 * Created by Water on 2018/5/8.
 */

public class A_New_Personnel extends TitleActivity implements NewPersonnelView {

    public static final int NEW = 67;
    public static final int UPDATA = 68;

    private int flag;
    private ANewPersonnelBinding binding;
    private Adapter_Jurisdiction adapterJurisdiction;
    private NewPersonnelPresenter presenter;

    public static void startActivityForResult(BaseActivity baseActivity, int flag) {
        Intent intent = new Intent(baseActivity, A_New_Personnel.class);
        intent.putExtra("flag", flag);
        baseActivity.startActivityForResult(intent, flag);
    }


    public static void startActivityForResult(BaseActivity baseActivity, String userId, int flag) {
        Intent intent = new Intent(baseActivity, A_New_Personnel.class);
        intent.putExtra("userId", userId);
        intent.putExtra("flag", flag);
        baseActivity.startActivityForResult(intent, flag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = getIntent().getIntExtra("flag", -1);
        if (flag == NEW) {
            setTitleText("新建人员");
        } else if (flag == UPDATA) {
            setTitleText("编辑人员");
        }
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_ok);
        binding = setView(R.layout.a_new_personnel);
        initView();
        initData();
        initLisetener();
    }

    private void initView() {
        binding.rvJurisdiction.setLayoutManager(new GridLayoutManager(this, 4));
        adapterJurisdiction = new Adapter_Jurisdiction();
        binding.rvJurisdiction.setAdapter(adapterJurisdiction);
    }

    private void initData() {
        presenter = new NewPersonnelPresenter(this, this, this);
        if (flag == NEW) {
        } else if (flag == UPDATA) {
            String userId = getIntent().getStringExtra("userId");
            presenter.getPersonnel(userId);
        }
    }

    private void initLisetener() {
        binding.setClick(v -> presenter.click(v));
    }

    @Override
    protected void rightOneImageOnClick() {
        String name = binding.etName.getText().toString();
        presenter.request(name, flag);
    }

    @Override
    public void selDivisionalResult(String divisionalName) {
        binding.tvDepartment.setText(divisionalName);
    }

    @Override
    public void selDutyResult(String dutyName) {
        binding.tvDuty.setText(dutyName);
    }

    @Override
    public void selDutyJurisdictionResult(List<JurisdictionRep> jurisdictionRepList) {
        adapterJurisdiction.setData(jurisdictionRepList);
    }

    @Override
    public void callbackName(String name) {
        binding.etName.setText(name);
    }

    @Override
    public void responseUpdata() {
        ToastUtil.showToast(this, "修改成功");
        finish();
    }

    @Override
    public void responseAdd() {
        ToastUtil.showToast(this, "添加成功");
        finish();
    }
}
