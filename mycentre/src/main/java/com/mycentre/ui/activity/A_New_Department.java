package com.mycentre.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.ANewDepartmentBinding;
import com.mycentre.ui.mvpview.NewDepartmentView;
import com.mycentre.ui.presenter.NewDepartmentPresenter;
import com.mycentre.response.DivisionalManagementRep;
import com.mycentre.ui.viewmodel.DivisionalManagementVM;
import com.waterbase.ui.BaseActivity;

/**
 * 新建/编辑部门
 * Created by Water on 2018/5/8.
 */

public class A_New_Department extends TitleActivity implements NewDepartmentView {

    public static final int NEW = 67;
    public static final int UPDATA = 68;

    private int flag;

    private DivisionalManagementRep parendRep;
    private DivisionalManagementRep childRep;
    private String departmentName;
    private ANewDepartmentBinding binding;
    private NewDepartmentPresenter presenter;

    public static void startActivityForResult(BaseActivity baseActivity, int flag) {
        Intent intent = new Intent(baseActivity, A_New_Department.class);
        intent.putExtra("flag", flag);
        baseActivity.startActivityForResult(intent, flag);
    }


    public static void startActivityForResult(BaseActivity baseActivity, DivisionalManagementRep parendRep
            , DivisionalManagementRep childRep, int flag) {
        Intent intent = new Intent(baseActivity, A_New_Department.class);
        intent.putExtra("parendRep", parendRep);
        intent.putExtra("childRep", childRep);
        intent.putExtra("flag", flag);
        baseActivity.startActivityForResult(intent, flag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flag = getIntent().getIntExtra("flag", -1);
        if (flag == NEW) {
            setTitleText("新建部门");
        } else if (flag == UPDATA) {
            setTitleText("编辑部门");
        }
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_ok);
        binding = setView(R.layout.a_new_department);
        initView();
    }

    private void initView() {
        if (flag == NEW) {
            parendRep = new DivisionalManagementRep();
            childRep = new DivisionalManagementRep();
            binding.setViewModel(new DivisionalManagementVM(parendRep));
        } else if (flag == UPDATA) {
            parendRep = (DivisionalManagementRep) getIntent().getSerializableExtra("parendRep");
            childRep = (DivisionalManagementRep) getIntent().getSerializableExtra("childRep");
            binding.setViewModel(new DivisionalManagementVM(parendRep));
            binding.etDepartmentName.setText(childRep.getContent());
        }
        presenter = new NewDepartmentPresenter(this, this, this);
        binding.setClick(v -> {
            if (v.getId() == R.id.tl_superior_department)
                presenter.selSuperiorDepartmen();
        });
    }

    @Override
    protected void rightOneImageOnClick() {
        departmentName = binding.etDepartmentName.getText().toString();
        childRep.setContent(departmentName);
        presenter.request(parendRep, childRep, flag);
    }

    @Override
    public void selResult(DivisionalManagementRep rep) {
        this.parendRep = rep;
        binding.setViewModel(new DivisionalManagementVM(this.parendRep));
    }

    @Override
    public void response(Object o) {
        finish();
    }

}
