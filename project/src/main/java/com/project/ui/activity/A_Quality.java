package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.global.even.RefreshEven;
import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AQualityBinding;
import com.project.databinding.RecyclerviewBinding;
import com.project.response.QualityListRep;
import com.project.ui.adapter.Adapter_Quality;
import com.project.ui.mvpview.QualityView;
import com.project.ui.presenter.QualityPresenter;
import com.waterbase.utile.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 质量检查
 * Created by Water on 2018/5/10.
 */

public class A_Quality extends TitleActivity implements QualityView {

    private String pId;
    private String pName;
    private AQualityBinding binding;

    private QualityPresenter presenter;
    private Adapter_Quality adapterQuality;

    public static void startActivity(Context context, String pId, String pName) {
        Intent intent = new Intent(context, A_Quality.class);
        intent.putExtra("pId", pId);
        intent.putExtra("pName", pName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        pId = getIntent().getStringExtra("pId");
        pName = getIntent().getStringExtra("pName");
        setTitleText("质量检查");
        binding = setView(R.layout.a_quality);
        initView();
        initListener();
        initData();
    }


    private void initView() {

        binding.rvQuality.setLayoutManager(new LinearLayoutManager(this));
        adapterQuality = new Adapter_Quality();
        adapterQuality.setItemClickListener(new ItemClickListener<QualityListRep>() {
            @Override
            public void itemClick(View v, QualityListRep rep, int index) {
                // 质量检查详情
                A_Quality_Details.startActivity(A_Quality.this, pId, rep.getE_id());
            }
        });
        adapterQuality.openAutoLoadMore(true);
        adapterQuality.setOnLoadMoreListener(() -> presenter.loadmore());
        binding.rvQuality.setAdapter(adapterQuality);
    }


    private void initListener() {
        binding.srQuality.setOnRefreshListener(() -> {
            presenter.Refresh();
        });
        binding.setClick(v -> {
            int i = v.getId();
            if (i == R.id.ll_date) {
                // 选择日期
                presenter.selDate(title, binding.llBg);
            } else if (i == R.id.ll_state) {
                // 选择状态
                presenter.selStater(title, binding.llBg);
            } else if (i == R.id.floatingActionButton) {
                // 添加
                A_New_Quality.startActivity(this, pId, A_New_Quality.NEW);
            }
        });
    }

    private void initData() {
        presenter = new QualityPresenter(this, this, this);
        presenter.request();
    }

    @Override
    public void responseList(List<QualityListRep> qualityListReps) {
        binding.srQuality.setRefreshing(false);
        adapterQuality.setData(qualityListReps);
    }

    @Override
    public void responseMoerList(List<QualityListRep> qualityListReps) {
        if (qualityListReps == null || qualityListReps.isEmpty())
            adapterQuality.loadCompleted();
        else
            adapterQuality.addData(qualityListReps);
    }

    @Override
    public void selStaterResult(String stater) {
        binding.tvState.setText(stater);
    }

    @Override
    public void selDateResult(String date) {
        binding.tvDate.setText(date);
    }

    @Override
    public String getPId() {
        return pId;
    }

    @Subscribe()
    public void onMessageEvent(RefreshEven event) {
        // 更新界面
        presenter.Refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

