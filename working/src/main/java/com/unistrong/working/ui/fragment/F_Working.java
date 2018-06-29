package com.unistrong.working.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.unistrong.working.R;
import com.unistrong.working.databinding.FWorkingBinding;
import com.unistrong.working.ui.activity.A_Message;
import com.unistrong.working.ui.mvpview.WorkingView;
import com.unistrong.working.ui.presenter.WorkingPresenter;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseTitleFragment;


/**
 * 工作台
 * 作者：Laughing on 2018/5/8 10:55
 * 邮箱：719240226@qq.com
 */

public class F_Working extends BaseTitleFragment implements WorkingView {
    private static F_Working instance;
    private FWorkingBinding mBinding;
    private WorkingPresenter mProjectPresenter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.f_working;
    }


    public synchronized static Fragment newInstance() {
        if (instance == null) {
            synchronized (F_Working.class) {
                if (instance == null)
                    instance = new F_Working();
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
        setTitleText("工作台");//标题
        setLeftOneImageVisibity(false);//左一按钮
        setLeftTwoImageVisibity(false);//左二按钮显示
        setRightOneImageVisibity(false);//右一按钮
        setRightOneImageVisibity(true);//右一按钮
        setRightTwoImageVisibity(false);//右二按钮
        setRightTextViewVisibity(false);//右侧TextView
        setRightOneImagePic(R.mipmap.ic_no_msg);//右一按钮图片
        setRightTwoImagePic(R.mipmap.ic_search);//右二按钮图片
        setLeftTwoImagePic(R.mipmap.ic_search);//左二按钮图片

    }

    @Override
    protected void initData() {
        super.initData();
        mProjectPresenter = new WorkingPresenter(this, (BaseActivity) getActivity(), this);
        initListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        instance=null;
    }

    private void initListener() {
        mBinding.setClick(v -> {
            try {
                mProjectPresenter.click(v);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

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
        A_Message.startActivity(mContext);

    }

    @Override
    protected void rightTwoImageOnClick() {
        super.rightTwoImageOnClick();
    }
}
