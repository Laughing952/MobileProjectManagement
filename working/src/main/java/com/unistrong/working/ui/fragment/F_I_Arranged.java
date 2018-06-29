package com.unistrong.working.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.unistrong.working.R;
import com.unistrong.working.bean.IArrangedBean;
import com.unistrong.working.databinding.FIArrangedBinding;
import com.unistrong.working.ui.activity.A_Sent_To_Me_Details;
import com.unistrong.working.ui.adapter.Adapter_I_Arranged;
import com.unistrong.working.ui.mvpview.IArrangedView;
import com.unistrong.working.ui.presenter.IArrangedPresenter;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseFragment;
import com.waterbase.widget.recycleview.OnLoadMoreListener;

import java.util.List;

/**
 * 我安排的
 * 作者：Laughing on 2018/5/8 16:09
 * 邮箱：719240226@qq.com
 */

public class F_I_Arranged extends BaseFragment implements IArrangedView {

    private static F_I_Arranged instance;
    private IArrangedPresenter mIArrangedPresenter;
    private FIArrangedBinding mBinding;
    private Adapter_I_Arranged mAdapterIArranged;


    public synchronized static Fragment newInstance() {
        if (instance == null) {
            synchronized (F_I_Arranged.class) {
                if (instance == null)
                    instance = new F_I_Arranged();
            }
        }
        return instance;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBinding = getBind();
        mAdapterIArranged = new Adapter_I_Arranged();
        mIArrangedPresenter = new IArrangedPresenter(this, (BaseActivity) getActivity(), this);
        mBinding.rvIArranged.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvIArranged.setAdapter(mAdapterIArranged);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_i_arranged;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        instance = null;
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
        mIArrangedPresenter.getIArrangedListData();
        initListener();
    }

//    @Override
//    protected void initData() {
//        super.initData();
//        mIArrangedPresenter.getIArrangedListData();
//        initListener();
//    }

    private void initListener() {
        mBinding.swipeRefreshLayoutArrange.setOnRefreshListener(() -> {
            mIArrangedPresenter.getIArrangedListData();
        });

        mAdapterIArranged.openAutoLoadMore(true);

        mAdapterIArranged.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mIArrangedPresenter.loadMoreData();
            }
        });

        mBinding.setClick(v -> mIArrangedPresenter.click(v));
        mAdapterIArranged.setItemClickListener(new ItemClickListener<IArrangedBean>() {
            @Override
            public void itemClick(View v, IArrangedBean arrangedBean, int index) {
                A_Sent_To_Me_Details.startActivity(getActivity(), A_Sent_To_Me_Details.Constant_I_Arranged, arrangedBean.getTaskId());

            }

        });

    }

    /**
     * 选择任务查看类型
     *
     * @param read
     */
    @Override
    public void seeAllOrRead2View(String read) {
        mBinding.tvIArrangedIsRead.setText(read);
    }

    /**
     * 任务状态
     *
     * @param state
     */
    @Override
    public void setState2View(String state) {
        mBinding.tvIArrangedState.setText("任务状态：" + state);

    }

    @Override
    public void resultIArrangedListData(List<IArrangedBean> arrangedList) {
        mBinding.swipeRefreshLayoutArrange.setRefreshing(false);
        mAdapterIArranged.setData(arrangedList);
    }

    @Override
    public void loadMoreResult(List<IArrangedBean> arrangedList) {
        if (arrangedList == null || arrangedList.isEmpty()) {
            mAdapterIArranged.loadCompleted();
            return;
        }
        mAdapterIArranged.addData(arrangedList);
    }
}
