package com.unistrong.working.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.util.UserIdUtil;
import com.unistrong.working.R;
import com.unistrong.working.bean.MyApprovalBean;
import com.unistrong.working.databinding.FMyApprovalBinding;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.activity.A_General_Request_Details;
import com.unistrong.working.ui.adapter.Adapter_My_Approval;
import com.unistrong.working.ui.mvpview.MyApprovalView;
import com.unistrong.working.ui.presenter.MyApprovalPresenter;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseFragment;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的审批
 * 作者：Laughing on 2018/5/8 16:09
 * 邮箱：719240226@qq.com
 */

public class F_My_Approval extends BaseFragment implements MyApprovalView<List<GeneralRequestItemDetailRep>> {

    private static F_My_Approval instance;
    private MyApprovalPresenter mMyApprovalPresenter;
    private FMyApprovalBinding mBinding;
    private Adapter_My_Approval mAdapter_my_approval;

    public synchronized static F_My_Approval newInstance() {
        if (instance == null) {
            synchronized (F_My_Approval.class) {
                if (instance == null)
                    instance = new F_My_Approval();
            }
        }
        return instance;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMyApprovalPresenter.getHttpRequestData(UserIdUtil.getUserIdLong());
        initListener();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBinding = getBind();
        mAdapter_my_approval = new Adapter_My_Approval();
        mMyApprovalPresenter = new MyApprovalPresenter(this, (BaseActivity) getActivity(), this);
        mBinding.rvSentToMe.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvSentToMe.setAdapter(mAdapter_my_approval);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_my_approval;
    }

//    @Override
//    protected void initData() {
//        super.initData();
//        mMyApprovalPresenter.getHttpRequestData(A_General_Approval.id);
//        initListener();
//    }

    private void initListener() {
        mBinding.setClick(v -> mMyApprovalPresenter.click(v));
        mAdapter_my_approval.setItemClickListener(new ItemClickListener<GeneralRequestItemDetailRep>() {
            @Override
            public void itemClick(View v, GeneralRequestItemDetailRep myApprovalBean, int position) {
                A_General_Request_Details.startActivity(F_My_Approval.this,
                        A_General_Request_Details.FROMIAPPROVED, myApprovalBean.getApproveId());
            }

        });
    }

    @Override
    public void resultRequestApprovalData(List<GeneralRequestItemDetailRep> response) {
        mAdapter_my_approval.setData(response);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        instance = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==getActivity().RESULT_OK&&requestCode==0){
            initData();
        }

    }
}
