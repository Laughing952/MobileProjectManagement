package com.unistrong.working.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.unistrong.working.R;
import com.unistrong.working.bean.IParticipatedBean;
import com.unistrong.working.databinding.FIParticipatedBinding;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.activity.A_Sent_To_Me_Details;
import com.unistrong.working.ui.adapter.Adapter_I_Participated;
import com.unistrong.working.ui.mvpview.IParticipatedView;
import com.unistrong.working.ui.presenter.IParticipatedPresenter;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseFragment;
import com.waterbase.widget.recycleview.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 我参与的（任务）
 * 作者：Laughing on 2018/5/8 16:09
 * 邮箱：719240226@qq.com
 */

public class F_I_Participated extends BaseFragment implements IParticipatedView {

    private static F_I_Participated instance;
    private IParticipatedPresenter mIParticipatedPresenter;
    private FIParticipatedBinding mBinding;
    private Adapter_I_Participated mAdapterIParticipated;

    public synchronized static Fragment newInstance() {
        if (instance == null) {
            synchronized (F_I_Participated.class) {
                if (instance == null)
                    instance = new F_I_Participated();
            }
        }
        return instance;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBinding = getBind();
        mAdapterIParticipated = new Adapter_I_Participated();
        mIParticipatedPresenter = new IParticipatedPresenter(this, (BaseActivity) getActivity(), this);
        mBinding.rvIParticipated.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvIParticipated.setAdapter(mAdapterIParticipated);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_i_participated;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        instance=null;
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
        mIParticipatedPresenter.getIParticpatedListData();
        initListener();
    }

//    @Override
//    protected void initData() {
//        super.initData();
//        mIParticipatedPresenter.getIParticpatedListData();
//        initListener();
//    }

    private void initListener() {
        mBinding.swipeRefreshLayoutParticipated.setOnRefreshListener(() -> {
            mIParticipatedPresenter.getIParticpatedListData();
        });

        mAdapterIParticipated.openAutoLoadMore(true);

        mAdapterIParticipated.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mIParticipatedPresenter.loadMoreData();
            }
        });
        mBinding.setClick(v -> mIParticipatedPresenter.click(v));
        mAdapterIParticipated.setItemClickListener(new ItemClickListener<IParticipatedBean>() {
            @Override
            public void itemClick(View v, IParticipatedBean bean, int index) {
                // TODO: 2018/5/7 模拟数据
//                A_I_Participated_DetailsDetails.startActivity(getActivity());
                A_Sent_To_Me_Details.startActivity(getActivity(), A_Sent_To_Me_Details.Constant_I_Participated,bean.getTaskId());

//                A_Project_Home.startActivity(A_Project_Doing.this, doingBean.getP_id(), doingBean.getP_name());
//                ToastUtil.showToast(mContext, "jump");

            }

        });
    }

    @Override
    public void seeAllOrRead2View(String read) {
        mBinding.tvIParticipatedIsRead.setText(read);
    }

    @Override
    public void setState2View(String state) {
        mBinding.tvIParticipatedState.setText("任务状态：" + state);
    }

    @Override
    public void resultIParticpatedListData(List<IParticipatedBean> repList) {
        mBinding.swipeRefreshLayoutParticipated.setRefreshing(false);
        mAdapterIParticipated.setData(repList);
    }

    @Override
    public void loadMoreResult(List<IParticipatedBean> moreList) {
        if (moreList == null || moreList.isEmpty()){
            mAdapterIParticipated.loadCompleted();
            return;
        }
        mAdapterIParticipated.addData(moreList);
    }
}
