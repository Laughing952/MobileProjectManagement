package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AProjectMemberCheckedBinding;
import com.project.response.PersonRep;
import com.project.ui.adapter.Adapter_Project_Member_Checked;
import com.project.ui.mvpview.ProjectMemberCheckedView;
import com.project.ui.presenter.ProjectMemberCheckedPresenter;
import com.project.ui.viewmodel.ProjectMemberCheckedVM;

import java.util.ArrayList;

/**
 * 选择项目或任务成员（checkbox）
 * 作者：Laughing on 2018/5/3 21:44
 * 邮箱：719240226@qq.com
 */

public class A_Project_Member_Checked extends TitleActivity implements ProjectMemberCheckedView {

    private AProjectMemberCheckedBinding mBinding;
    private ProjectMemberCheckedPresenter mPresenter;
    private Adapter_Project_Member_Checked mAdapter;
    private Intent mIntent;
    public static final String C_B_DATA = "C_B_DATA";//从第三个页面C 返回到第二个页面B 的 data
    public static final int C_B = 0x311;//从第三个页面C 返回到第二个页面B 的 resultCode
    private ArrayList<PersonRep> mPersonReps;
    ArrayList<ProjectMemberCheckedVM> mProjectMemberCheckedVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_project_member_checked);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        //获取上一个页面上传递来的数据
        mIntent = getIntent();
        mPresenter = new ProjectMemberCheckedPresenter(this, this, this);
        mAdapter = new Adapter_Project_Member_Checked();
        mBinding.rvProjectMemberChecked.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvProjectMemberChecked.setAdapter(mAdapter);
        mPersonReps = (ArrayList<PersonRep>) mIntent.getSerializableExtra(A_Project_Member.B_C_DATA);
        mPresenter.queryPersonInfo(mPersonReps);
        //mAdapter.setData(ProjectMemberCheckedVM);

    }

    private void initListener() {

        mBinding.setClick(v -> mPresenter.click(v));
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Project_Member_Checked.class));
    }

    public void initTitle() {
        setTitleText("选择成员");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("确定");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        ArrayList<PersonRep> personReps = new ArrayList<>();
        for (ProjectMemberCheckedVM vm : mProjectMemberCheckedVM) {
            if (vm.isSel()) {
                PersonRep personRep = new PersonRep(vm.getRealname(), vm.getUserId(), vm.isSel());
                personReps.add(personRep);
            }
        }

        // 把新数据返回数据存入Intent
        mIntent.putExtra(C_B_DATA, personReps);
        A_Project_Member_Checked.this.setResult(C_B, mIntent);
        A_Project_Member_Checked.this.finish();
        finish();
    }

    @Override
    public void showData(ArrayList<ProjectMemberCheckedVM> projectMemberCheckedVMS) {
        mProjectMemberCheckedVM = projectMemberCheckedVMS;
        mAdapter.setData(projectMemberCheckedVMS);

    }
}
