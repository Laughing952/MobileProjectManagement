package com.project.ui.fragmnt;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.project.R;
import com.project.databinding.FProjectBinding;
import com.project.ui.activity.A_Create_Project;
import com.project.ui.activity.A_Search_Project;
import com.project.ui.mvpview.ProjectView;
import com.project.ui.presenter.ProjectPresenter;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseTitleFragment;

/**
 * 项目
 * 作者：Laughing on 2018/4/28 07:23
 * 邮箱：719240226@qq.com
 */

public class F_Project extends BaseTitleFragment implements ProjectView {
    private static F_Project instance;
    private FProjectBinding mBinding;
    private ProjectPresenter mProjectPresenter;
//    public static final String userId="3";
//    public static final String userName="cuihao";

    @Override
    protected int getContentLayoutId() {
        return R.layout.f_project;
    }


    public synchronized static Fragment newInstance() {
        if (instance == null) {
            synchronized (F_Project.class) {
                if (instance == null)
                    instance = new F_Project();
            }
        }
        return instance;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        initTitle();
        mBinding = getBind();

    }

    private void initTitle() {
        setTitleText("项目");//标题
        setLeftOneImageVisibity(false);//隐藏左一按钮
        setLeftTwoImageVisibity(true);//左二按钮显示
        setRightOneImageVisibity(false);//隐藏右一按钮
        setRightOneImageVisibity(true);//隐藏右一按钮
        setRightTwoImageVisibity(true);//隐藏右二按钮
        setRightTextViewVisibity(false);//隐藏右侧TextView
        setRightOneImagePic(R.mipmap.ic_add);//右一按钮图片
        setRightTwoImagePic(R.mipmap.ic_search);//右二按钮图片
        setLeftTwoImagePic(R.mipmap.ic_map);//左二按钮图片
    }

    @Override
    protected void initData() {
        super.initData();
        mProjectPresenter = new ProjectPresenter(this, (BaseActivity) getActivity(), this);
        initListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        instance=null;
    }

    private void initListener() {
        mBinding.setClick(v -> mProjectPresenter.click(v));
    }

    @Override
    protected void leftTwoImageOnClick() {
        super.leftTwoImageOnClick();
        Intent intent = null;
        try {
            intent = new Intent(getContext(), Class.forName("com.unistrong.baidumaplibrary.ui.activity.A_Project_Distribution"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // TODO: 2018/5/7 跳转到地图页面
            intent.putExtra("data", "");
        }
        getActivity().startActivity(intent);

    }

    @Override
    protected void rightOneImageOnClick() {
        super.rightOneImageOnClick();
        A_Create_Project.startActivityAndFlag(getActivity().getApplicationContext());//创建项目
    }

    @Override
    protected void rightTwoImageOnClick() {
        super.rightTwoImageOnClick();
        //通过项目名称、项目负责人对项目进行查询
        A_Search_Project.startActivityAndFlag(getActivity().getApplicationContext());//搜索项目
    }
}

