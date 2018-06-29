package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.global.ui.activity.TitleActivity;
import com.unistrong.working.R;
import com.unistrong.working.databinding.AWorkingTaskBinding;
import com.unistrong.working.ui.fragment.F_I_Arranged;
import com.unistrong.working.ui.fragment.F_I_Participated;
import com.unistrong.working.ui.fragment.F_Sent_To_Me;
import com.unistrong.working.ui.mvpview.WorkingTaskView;
import com.unistrong.working.ui.presenter.WorkingTaskPresenter;

/**
 * 工作任务
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Working_Task extends TitleActivity implements WorkingTaskView, RadioGroup.OnCheckedChangeListener {

    private AWorkingTaskBinding mBinding;
    private WorkingTaskPresenter mWorkingTaskPresenter;
    private Context mContext = this;
    private F_Sent_To_Me mSentToMe;
    private F_I_Arranged mIArranged;
    private F_I_Participated mFIParticipated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_working_task);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mWorkingTaskPresenter = new WorkingTaskPresenter(this, this, this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mSentToMe = (F_Sent_To_Me) F_Sent_To_Me.newInstance();
        transaction.add(R.id.fl_general_approval_content, mSentToMe);
        transaction.commit();
    }

    private void initListener() {

        mBinding.setClick(v -> mWorkingTaskPresenter.click(v));
        mBinding.rgWorkingTask.setOnCheckedChangeListener(this);
//        mBinding.rgWorkingTask.check(R.id.rb1);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Working_Task.class));
    }

    public void initTitle() {
        setTitleText("工作任务");//标题
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_add);
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        mWorkingTaskPresenter.Add();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        if (checkedId == R.id.rb1) {
            //派给我的
//            ToastUtil.showToast(mContext, "派给我的");
            if (mSentToMe == null) {
                mSentToMe = (F_Sent_To_Me) F_Sent_To_Me.newInstance();
                transaction.add(R.id.fl_general_approval_content, mSentToMe);
            } else {
                transaction.show(mSentToMe);
            }

        } else if (checkedId == R.id.rb2) {
            //我安排的
//            ToastUtil.showToast(mContext, "我安排的");
            if (mIArranged == null) {
                mIArranged = (F_I_Arranged) F_I_Arranged.newInstance();
                transaction.add(R.id.fl_general_approval_content, mIArranged);
            } else {
                transaction.show(mIArranged);
            }

        } else if (checkedId == R.id.rb3) {
            //我参与的
//            ToastUtil.showToast(mContext, "我参与的");
            if (mFIParticipated == null) {
                mFIParticipated = (F_I_Participated) F_I_Participated.newInstance();
                transaction.add(R.id.fl_general_approval_content, mFIParticipated);
            } else {
                transaction.show(mFIParticipated);
            }
        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (mSentToMe != null) transaction.hide(mSentToMe);
        if (mIArranged != null) transaction.hide(mIArranged);
        if (mFIParticipated != null) transaction.hide(mFIParticipated);

    }

    @Override
    protected void rightOneImageOnClick() {
        super.rightOneImageOnClick();
        A_Create_New_Task.startActivity(this, A_Create_New_Task.Constant_Add, null);//跳转到创建/编辑任务页面

    }
}
