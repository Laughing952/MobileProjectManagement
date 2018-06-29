package com.unistrong.working.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.util.UserIdUtil;
import com.unistrong.working.R;
import com.unistrong.working.bean.CcMeBean;
import com.unistrong.working.databinding.FCcMeBinding;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.activity.A_General_Request_Cc_me_Details_Details;
import com.unistrong.working.ui.activity.A_General_Request_Details;
import com.unistrong.working.ui.adapter.Adapter_Cc_Me;
import com.unistrong.working.ui.mvpview.CcMeView;
import com.unistrong.working.ui.presenter.CcMePresenter;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseFragment;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ToastUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 抄送我的
 * 作者：Laughing on 2018/5/8 16:09
 * 邮箱：719240226@qq.com
 */

public class F_Cc_Me extends BaseFragment implements CcMeView<List<GeneralRequestItemDetailRep>> {

    private static F_Cc_Me instance;
    private CcMePresenter mCcMePresenter;
    private FCcMeBinding mBinding;
    private Adapter_Cc_Me mAdapter_cc_me;

    public synchronized static F_Cc_Me newInstance() {
        if (instance == null) {
            synchronized (F_Cc_Me.class) {
                if (instance == null)
                    instance = new F_Cc_Me();
            }
        }
        return instance;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            onResume();
        }
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mBinding = getBind();
        mAdapter_cc_me = new Adapter_Cc_Me();
        mCcMePresenter = new CcMePresenter(this, (BaseActivity) getActivity(), this);
        mBinding.rvMyCc.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvMyCc.setAdapter(mAdapter_cc_me);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_cc_me;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCcMePresenter.getHttpRequestData(UserIdUtil.getUserIdLong());
        initListener();
    }

//    @Override
//    protected void initData() {
//        super.initData();
//        mCcMePresenter.getHttpRequestData(A_General_Approval.id);
//        initListener();
//    }


    private void initListener() {
        mBinding.setClick(v -> mCcMePresenter.click(v));
        mAdapter_cc_me.setItemClickListener(new ItemClickListener<GeneralRequestItemDetailRep>() {
            @Override
            public void itemClick(View v, GeneralRequestItemDetailRep ccMeBean, int index) {
                // TODO: 2018/5/7 模拟数据
                A_General_Request_Details.startActivity(F_Cc_Me.this,
                        A_General_Request_Details.FROMCCME,ccMeBean.getApproveId());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==getActivity().RESULT_OK&&requestCode==0){
            initData();
        }
    }

    @Override
    public void resultRequestApprovalData(List<GeneralRequestItemDetailRep> response) {
        mAdapter_cc_me.setData(response);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        instance = null;
    }

}
