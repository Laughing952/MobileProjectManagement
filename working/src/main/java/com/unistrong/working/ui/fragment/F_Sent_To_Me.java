package com.unistrong.working.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.viewmodel.TaskPriorityViewModel;
import com.unistrong.working.R;
import com.unistrong.working.bean.SentToMeBean;
import com.unistrong.working.databinding.FSentToMeBinding;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.activity.A_Sent_To_Me_Details;
import com.unistrong.working.ui.adapter.Adapter_Sent_To_Me;
import com.unistrong.working.ui.mvpview.SentToMeView;
import com.unistrong.working.ui.presenter.SentToMePresenter;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseApplication;
import com.waterbase.ui.BaseFragment;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.widget.recycleview.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 派给我的
 * 作者：Laughing on 2018/5/8 16:09
 * 邮箱：719240226@qq.com
 */

public class F_Sent_To_Me extends BaseFragment implements SentToMeView {

    private static F_Sent_To_Me instance;
    private SentToMePresenter mMyApprovalPresenter;
    private FSentToMeBinding mBinding;
    private Adapter_Sent_To_Me mAdapter_sent_to_me;

    public synchronized static Fragment newInstance() {
        if (instance == null) {
            synchronized (F_Sent_To_Me.class) {
                if (instance == null)
                    instance = new F_Sent_To_Me();
            }
        }
        return instance;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBinding = getBind();
        mAdapter_sent_to_me = new Adapter_Sent_To_Me();
        mMyApprovalPresenter = new SentToMePresenter(this, (BaseActivity) getActivity(), this);
        mBinding.rvSentToMe.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvSentToMe.setAdapter(mAdapter_sent_to_me);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_sent_to_me;
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
        mMyApprovalPresenter.getSentToMeListData();
        initListener();
    }

//    @Override
//    protected void initData() {
//        super.initData();
//        mMyApprovalPresenter.getSentToMeListData();
//        initListener();
//    }

    private void initListener() {
        mBinding.swipeRefreshLayoutSentToMe.setOnRefreshListener(() -> {
            mMyApprovalPresenter.getSentToMeListData();
        });

        mAdapter_sent_to_me.openAutoLoadMore(true);

        mAdapter_sent_to_me.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mMyApprovalPresenter.loadMoreData();
            }
        });
        mBinding.setClick(v -> mMyApprovalPresenter.click(v,getActivity()));
        mAdapter_sent_to_me.setItemClickListener(new ItemClickListener<SentToMeBean>() {
            @Override
            public void itemClick(View v, SentToMeBean sentToMeBean, int index) {
                // TODO: 2018/5/7 模拟数据
                A_Sent_To_Me_Details.startActivity(getActivity(), A_Sent_To_Me_Details.Constant_Sent_To_Me,sentToMeBean.getTaskId());
//                A_Project_Home.startActivity(A_Project_Doing.this, doingBean.getP_id(), doingBean.getP_name());
//                ToastUtil.showToast(mContext, "jump");

            }

        });
    }

    /**
     * 任务优先级
     *
     * @param priorityViewModel
     */
    @Override
    public void setPriority2View(TaskPriorityViewModel priorityViewModel) {
        mBinding.tvSentToMePriority.setText("任务优先级：" + priorityViewModel.getPickerViewText());
    }

    /**
     * 任务状态
     *
     * @param state
     */
    @Override
    public void setState2View(String state) {
        mBinding.tvSentToMeState.setText("任务状态：" + state);
        mMyApprovalPresenter.getSentToMeListData();
    }

    @Override
    public void resultSendToMeListData(List<SentToMeBean> sentToMeList) {
        mBinding.swipeRefreshLayoutSentToMe.setRefreshing(false);
        mAdapter_sent_to_me.setData(sentToMeList);
    }

    @Override
    public void loadMoreResult(List<SentToMeBean> moreList) {
        if (moreList == null || moreList.isEmpty()){
            mAdapter_sent_to_me.loadCompleted();
            return;
        }
        mAdapter_sent_to_me.addData(moreList);
    }
}
