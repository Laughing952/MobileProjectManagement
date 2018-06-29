package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.global.util.UserIdUtil;
import com.project.R;
import com.project.databinding.ACreateProjectBinding;
import com.project.request.CreateProjectReq;
import com.project.response.PersonRep;
import com.project.response.UserInfoRep;
import com.project.ui.mvpview.CreateProjectView;
import com.project.ui.presenter.CreateProjectPresenter;
import com.project.ui.viewmodel.ProjectMemberCheckedVM;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;

/**
 * 项目-创建项目
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Create_Project extends TitleActivity implements CreateProjectView {

    private ACreateProjectBinding mBinding;
    private CreateProjectPresenter mPresenter;
    private CreateProjectReq mCreateProjectReq;
    public static final String Create_Project_Data = "projectPersonBeans";//请求码
    public static final int A_B = 0x111;//从第一个页面A 跳转到第二个页面B 的 requestCode
    private ArrayList<PersonRep> mPersonReps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_create_project);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mCreateProjectReq = new CreateProjectReq();
        mPresenter = new CreateProjectPresenter(this, this, this);
        mCreateProjectReq.setItemHead(UserIdUtil.getUserIdLong().toString());
        mBinding.setViewmodel(mCreateProjectReq);//绑定数据
        mPersonReps = new ArrayList<>();//创建集合用来存放数据
        mPresenter.downloadUserInfo(UserIdUtil.getUserIdLong());//加载用户姓名

    }

    private void initListener() {
        mBinding.setClick(v -> mPresenter.click(v));
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, A_Create_Project.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startActivityAndFlag(Context context) {
        Intent intent = new Intent(context, A_Create_Project.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void initTitle() {
        setTitleText("创建项目");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("创建");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        ArrayList<ProjectMemberCheckedVM> memberCheckedVMArrayList = new ArrayList<>();
        for (PersonRep personRep : mPersonReps) {
            ProjectMemberCheckedVM projectMemberCheckedVM = new ProjectMemberCheckedVM();
            projectMemberCheckedVM.setUserId(personRep.getId());
            memberCheckedVMArrayList.add(projectMemberCheckedVM);
        }
        mCreateProjectReq.setList(memberCheckedVMArrayList);
        mCreateProjectReq.setUserId(UserIdUtil.getUserIdLong());
        mCreateProjectReq.setCreater(UserIdUtil.getUserIdLong());
        LogUtil.e("TAG", "laughing---------------------->   " + UserIdUtil.getUserIdLong());
        mPresenter.create(mCreateProjectReq);
    }

    public static void startActivity(Context context, ArrayList<ProjectMemberCheckedVM> projectMemberCheckedVMS) {
        Intent intent = new Intent(context, A_Project_Member.class);
        intent.putExtra("projectPersonBeans", projectMemberCheckedVMS);
        context.startActivity(intent);
    }

    /**
     * 传递项目成员列表,并且接受选择的人员列表
     */
    @Override
    public void jumpAndGetData() {

        A_Project_Member.startActivityForResult(this, mPersonReps, A_B);

//        Intent intent = new Intent(this, A_Project_Member.class);
//        intent.putExtra(Create_Project_Data, mPersonReps);
//        // 带返回数据启动Activity
//        startActivityForResult(intent, A_B);//请求码 A_B
    }

    /**
     * 创建项目结果
     */
    @Override
    public void showResult() {
        ToastUtil.showToast(this, "创建成功");
        finish();
    }

    /**
     * 创建项目时自动添加用户名字到负责人
     */
    @Override
    public void showName(UserInfoRep userInfoRep) {
        if (!StrUtil.isEmpty(userInfoRep.getUsername())) {
            //有名字，显示名字
            mBinding.edtPersonalInfoEditLeader.setText(userInfoRep.getUsername());
        } else {
            //没有名字，显示手机号
            mBinding.edtPersonalInfoEditLeader.setText(userInfoRep.getMobile());
        }
    }

    /**
     * 获取回传的数据
     * <p>
     * int requestCode 请求码 int resultCode 结果码 Intent data 意图(带着返回参数)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == A_B && resultCode == A_Project_Member.B_A) {
            // 把下一页的数据携带回来
            if (data != null) {
                ArrayList<PersonRep> personReps = (ArrayList<PersonRep>) data.getSerializableExtra(A_Project_Member.B_A_DATA);
                mBinding.edtPersonalInfoPersonNum.setText(personReps.size() + "人");
                mPersonReps.clear();
                mPersonReps.addAll(personReps);

            }
        }
    }
}
