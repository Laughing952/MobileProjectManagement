package com.project.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AProjectMemberBinding;
import com.project.response.PersonRep;
import com.project.ui.adapter.Adapter_Project_Member;
import com.project.ui.mvpview.ProjectMemberView;
import com.project.ui.presenter.ProjectMemberPresenter;

import java.util.ArrayList;

/**
 * 项目或任务成员
 * 作者：Laughing on 2018/5/3 21:44
 * 邮箱：719240226@qq.com
 */

public class A_Project_Member extends TitleActivity implements ProjectMemberView {

    private AProjectMemberBinding mBinding;
    private ProjectMemberPresenter mProjectMemberPresenter;
    private Adapter_Project_Member mAdapter;
    private Intent mIntent;
    public ArrayList<PersonRep> mPersonReps;
    public static final int B_A = 0x211;//从中间页面B 返回到第一个页面A 的 resultCode
    public static final String B_A_DATA = "B_A_DATA";//从中间页面B 返回到第一个页面A 的 data
    public static final String B_C_DATA = "B_C_DATA";//从中间页面B 跳转到第三个页面C 的 data
    public static final int B_C = 0x212;//从中间页面B 跳转到第三个页面C 的 requestCode

    public static void startActivityForResult(Activity activity, ArrayList<PersonRep> repArrayList, int requestCode) {
        Intent intent = new Intent(activity, A_Project_Member.class);
        intent.putExtra("personRepList", repArrayList);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_project_member);
        initTitle();
        initData();
        initListener();
    }

    private void initData() {
        mIntent = getIntent();
        mPersonReps = (ArrayList<PersonRep>) mIntent.getSerializableExtra("personRepList");

        mProjectMemberPresenter = new ProjectMemberPresenter(this, this, this);
        mAdapter = new Adapter_Project_Member();
        mBinding.rvCreateProjectChooseMember.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvCreateProjectChooseMember.setAdapter(mAdapter);
        mAdapter.setData(mPersonReps);
    }

    private void initListener() {

        mBinding.setClick(v -> mProjectMemberPresenter.click(v));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Project_Member.class));
    }

    public void initTitle() {
        setTitleText("已选中成员");//标题
        setRightTextViewVisibity(true);
        setRightTwoImageVisibity(true);
        setRightTwoImagePic(R.mipmap.ic_add);
        setRightTextViewText("确定");
    }

    /**
     * 确定按钮
     */
    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        //传递来的数据
        mIntent = new Intent();
        //点击确定
        mIntent.putExtra(B_A_DATA, mPersonReps);// 把返回数据存入Intent
        A_Project_Member.this.setResult(B_A, mIntent);
        A_Project_Member.this.finish();
    }

    /**
     * 添加按钮
     */
    @Override
    protected void rightTwoImageOnClick() {
        super.rightTwoImageOnClick();
        //添加人员
        Intent intent = new Intent(this, A_Project_Member_Checked.class);
        intent.putExtra(B_C_DATA, mPersonReps);
        // 带返回数据启动Activity
        startActivityForResult(intent, B_C);

    }

    /**
     * 获取回传的数据
     * <p>
     * int requestCode 请求码 int resultCode 结果码 Intent data 意图(带着返回参数)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == B_C && resultCode == A_Project_Member_Checked.C_B) {
            // 把下一页的数据携带回来
            if (data != null) {
                ArrayList<PersonRep> personReps = (ArrayList<PersonRep>) data.getSerializableExtra(A_Project_Member_Checked.C_B_DATA);
                mAdapter.setData(personReps);
                if (mPersonReps != null) {
                    mPersonReps.clear();
                    mPersonReps.addAll(personReps);
                }

            }
        }
    }
}
