package com.unistrong.working.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.util.UserIdUtil;
import com.unistrong.working.R;
import com.unistrong.working.bean.MyCommitBean;
import com.unistrong.working.databinding.FMyCommitBinding;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.activity.A_General_Request_Details;
import com.unistrong.working.ui.activity.A_General_Request_I_Commit_Details;
import com.unistrong.working.ui.adapter.Adapter_My_Commit;
import com.unistrong.working.ui.mvpview.MyCommitView;
import com.unistrong.working.ui.presenter.MyCommitPresenter;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseFragment;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * 我提交的-通用申请（可取消）
 * 作者：Laughing on 2018/5/8 16:09
 * 邮箱：719240226@qq.com
 */

public class F_My_Commit extends BaseFragment implements MyCommitView<List<GeneralRequestItemDetailRep>> {

    private static F_My_Commit instance;
    private MyCommitPresenter mMyCommitPresenter;
    private FMyCommitBinding mBinding;
    private Adapter_My_Commit mAdapterMyCommit;


    public synchronized static F_My_Commit newInstance() {
        if (instance == null) {
            synchronized (F_My_Commit.class) {
                if (instance == null)
                    instance = new F_My_Commit();
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
    public void onResume() {
        super.onResume();
        mMyCommitPresenter.getHttpRequestData(UserIdUtil.getUserIdLong());
        initListener();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBinding = getBind();
        mAdapterMyCommit = new Adapter_My_Commit();
        mMyCommitPresenter = new MyCommitPresenter(this, (BaseActivity) getActivity(), this);
        mBinding.rvIArranged.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvIArranged.setAdapter(mAdapterMyCommit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_my_commit;
    }

//    @Override
//    protected void initData() {
//        super.initData();
//        mMyCommitPresenter.getHttpRequestData(A_General_Approval.id);
//        initListener();
//    }

    @Override
    public void resultRequestApprovalData(List<GeneralRequestItemDetailRep> response) {
        mAdapterMyCommit.setData(response);
    }

    private void initListener() {
        mBinding.setClick(v -> mMyCommitPresenter.click(v));
        mAdapterMyCommit.setItemClickListener(new ItemClickListener<GeneralRequestItemDetailRep>() {
            @Override
            public void itemClick(View v, GeneralRequestItemDetailRep myCommitBean, int index) {
                // TODO: 2018/5/7 模拟数据
                A_General_Request_Details.startActivity(F_My_Commit.this,
                        A_General_Request_Details.FROMICOMMIT,myCommitBean.getApproveId());
            }

        });

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
