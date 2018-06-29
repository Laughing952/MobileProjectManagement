package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.global.ui.activity.TitleActivity;
import com.unistrong.working.R;
import com.unistrong.working.databinding.AGeneralApprovalBinding;
import com.unistrong.working.ui.fragment.F_My_Approval;
import com.unistrong.working.ui.fragment.F_Cc_Me;
import com.unistrong.working.ui.fragment.F_My_Commit;
import com.unistrong.working.ui.mvpview.GeneralApprovalView;
import com.unistrong.working.ui.presenter.GeneralApprovalPresenter;
import com.waterbase.utile.ToastUtil;

/**
 * 通用申请
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_General_Approval extends TitleActivity implements GeneralApprovalView, RadioGroup.OnCheckedChangeListener {

    private AGeneralApprovalBinding mBinding;
    private GeneralApprovalPresenter mGeneralApprovalPresenter;
    private Context mContext = this;
//    private FragmentTransaction transaction;
    private F_My_Approval mFApproval;
    private F_My_Commit mFMyCommit;
    private F_Cc_Me mFMyCc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_general_approval);
        initTitle();
        initData();
        initListener();
    }

    private void initData() {
        mGeneralApprovalPresenter = new GeneralApprovalPresenter(this, this, this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mFApproval = F_My_Approval.newInstance();
        transaction.add(R.id.fl_general_approval_content, mFApproval);
        transaction.commit();
    }

    private void initListener() {
        mBinding.setClick(v -> mGeneralApprovalPresenter.click(v));
        mBinding.rgBookOrder.setOnCheckedChangeListener(this);
//        mBinding.rgBookOrder.check(R.id.rb1);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_General_Approval.class));
    }

    public void initTitle() {
        setTitleText("通用申请");//标题
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_add);
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        mGeneralApprovalPresenter.Add();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        if (checkedId == R.id.rb1) {
            //我审批的
            if (mFApproval == null) {
                mFApproval = F_My_Approval.newInstance();
                transaction.add(R.id.fl_general_approval_content, mFApproval);
            } else {
                transaction.show(mFApproval);
            }
        } else if (checkedId == R.id.rb2)

        {
            if (mFMyCommit == null) {
                //我提交的
                mFMyCommit = F_My_Commit.newInstance();
                transaction.add(R.id.fl_general_approval_content, mFMyCommit);
            } else {
                transaction.show(mFMyCommit);
            }
        } else if (checkedId == R.id.rb3)

        {
            if (mFMyCc == null) {
                //抄送我的
                mFMyCc = F_Cc_Me.newInstance();
                transaction.add(R.id.fl_general_approval_content, mFMyCc);
            } else {
                transaction.show(mFMyCc);
            }
        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (mFApproval != null) transaction.hide(mFApproval);
        if (mFMyCommit != null) transaction.hide(mFMyCommit);
        if (mFMyCc != null) transaction.hide(mFMyCc);

    }

    @Override
    protected void rightOneImageOnClick() {
        super.rightOneImageOnClick();
        A_Create_General_Request.startActivity(mContext);

    }

}
