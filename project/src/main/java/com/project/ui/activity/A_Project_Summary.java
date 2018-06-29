package com.project.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AProjectSummaryBinding;
import com.project.response.ImageRep;
import com.project.response.PersonRep;
import com.project.ui.mvpview.ProjectSummaryView;
import com.project.ui.presenter.ProjectSummaryPresenter;
import com.project.ui.viewmodel.CreateProjectVM;
import com.project.ui.viewmodel.ProjectMemberCheckedVM;
import com.project.util.LoadImageUtile;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.RxRetrofitApp;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目概况/编辑
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Project_Summary extends TitleActivity implements ProjectSummaryView {

    private Context mContext = this;
    private static Intent intent = null;
    private AProjectSummaryBinding mBinding;
    private ProjectSummaryPresenter mPresenter;
    private CreateProjectVM mCreateProjectVM;
    private ArrayList<PersonRep> mPersonReps;
    public static final int E_B = 0x411;//从第一个页面A 跳转到第二个页面B 的 requestCode
    public static final String Project_Summary = "Project_Summary";//数据传输key
    private String projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_project_summary);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        projectId = getIntent().getStringExtra("projectId");
        mPersonReps = new ArrayList<>();//创建集合用来存放数据
        mPresenter = new ProjectSummaryPresenter(this, this, this);
        mPresenter.getEditProjectInfo(projectId);
    }

    private void initListener() {

        mBinding.setClick(v ->
            mPresenter.click(v)
        );
    }

    public static void startActivity(Activity activity, String projectId) {
        intent = new Intent(activity, A_Project_Summary.class);
        intent.putExtra("projectId",projectId);
        activity.startActivityForResult(intent,0);
    }


    public void initTitle() {
        setTitleText("编辑项目");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("确定");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        mPresenter.Add(mCreateProjectVM);
    }

    @Override
    public void chooseImage() {
        intent = new Intent(this, MyPhotoActivity.class);
        startActivityForResult(intent, 0x04);
    }

    @Override
    public void showUserHeadPhoto(ImageRep imageRep) {
        LoadImageUtile.loadSquareImage(mBinding.ivProjectSummaryProjectImage, RxRetrofitApp.getApiServerUrl()+imageRep.getShowImageUrl());
        mCreateProjectVM.setImageRep(imageRep);
    }

    /**
     * 提交编辑
     */
    @Override
    public void editProject() {
        // TODO: 2018/5/16 提交编辑的逻辑
        List<ProjectMemberCheckedVM> memberList = new ArrayList<>();
        for (PersonRep personRep :mPersonReps){
            ProjectMemberCheckedVM memberCheckedVM = new ProjectMemberCheckedVM();
            memberCheckedVM.setUserId(personRep.getId());
            memberList.add(memberCheckedVM);
        }
        mCreateProjectVM.setList(memberList);
        mPresenter.commitEditProject(mCreateProjectVM);
    }

    /**
     * 提交编辑结果
     */
    @Override
    public void showResult() {
        ToastUtil.showToast(this, "编辑成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void chooseProjectMember() {
        A_Project_Member.startActivityForResult(this, mPersonReps, E_B);
    }

    @Override
    public void resultItemHead(ProjectMemberCheckedVM rep) {
        mBinding.edtProjectSummaryEditLeader.setText(rep.getRealname());
        mCreateProjectVM.setItemHeadId(rep.getUserId());
    }

    @Override
    public void resultProjectInfo(CreateProjectVM rep) {
        mCreateProjectVM = rep;
        mBinding.setViewmodel(mCreateProjectVM);
        //图片显示
        if (mCreateProjectVM.getImageRep()!=null) {
            LoadImageUtile.loadSquareImage(mBinding.ivProjectSummaryProjectImage,
                    RxRetrofitApp.getApiServerUrl() + mCreateProjectVM.getImageRep().getShowImageUrl());
        }

        mPersonReps = new ArrayList<>();//创建集合用来存放数据
        List<ProjectMemberCheckedVM> list = mCreateProjectVM.getList();
        if (list!=null && !list.isEmpty()){
            for (ProjectMemberCheckedVM memberCheckedVM :list){
                mPersonReps.add(new PersonRep(memberCheckedVM.getRealname(),memberCheckedVM.getUserId()));
            }
        }
    }

    /**
     * 拍完照片后返回的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case 0x04:
                    if (resultCode == MyPhotoActivity.FINSH_RESULT && data != null) {
                        String path = data.getStringExtra("image");
                        mPresenter.uploadImage(path);
//                        Uri uri = data.getData();
//                        LogUtil.e("TAG", "laughing---123------------------->   " + uri.toString());
//                        showUserHeadPhoto(path);
                    }
                    break;
                case E_B:
                    // 把下一页的数据携带回来
                    if (data != null) {
                        ArrayList<PersonRep> personReps = (ArrayList<PersonRep>) data.getSerializableExtra(A_Project_Member.B_A_DATA);
                        if (personReps != null) {
                            mBinding.edtProjectSummaryPersonNum.setText(personReps.size() + "人");
                            ToastUtil.showToast(mContext, personReps.size() + "");
                            mPersonReps.clear();
                            mPersonReps.addAll(personReps);
                        }

                    }
                    break;
            }
    }

    /**
     * 用来展示用户头像图片（从本地缓存中加载）
     *
     * @param path 图片上一次在本地存的位置
     */
    private void showUserHeadPhoto(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if (bitmap != null) {
            LogUtil.e("laughing", "path---------->  " + path);
//            Bitmap bitmap1 = new RoundBitmap().toRoundBitmap(bitmap);//图片处理成圆形
            mBinding.ivProjectSummaryProjectImage.setImageBitmap(bitmap);
        }

    }
}
