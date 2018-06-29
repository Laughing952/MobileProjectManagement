package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.global.even.RefreshEven;
import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AQualityDetailsBinding;
import com.project.response.QualityListRep;
import com.project.ui.adapter.Adapter_Image;
import com.project.ui.mvpview.QualityDetailsView;
import com.project.ui.presenter.QualityDetailsPresenter;
import com.project.ui.viewmodel.QualityListVM;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ViewUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 质量检查详情
 * Created by Water on 2018/5/11.
 */

public class A_Quality_Details extends TitleActivity implements QualityDetailsView {

    private AQualityDetailsBinding binding;
    private Adapter_Image adapterImage;
    private String p_id; // 项目id
    private String e_id; // 质量检查id
    private QualityDetailsPresenter presenter;

    public static void startActivity(Context context, String p_id, String e_id) {
        Intent intent = new Intent(context, A_Quality_Details.class);
        intent.putExtra("p_id", p_id);
        intent.putExtra("e_id", e_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setTitleText("质量检查详情");
        binding = setView(R.layout.a_quality_details);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        binding.rvImage.setLayoutManager(new GridLayoutManager(this, 4));
        adapterImage = new Adapter_Image();
        binding.rvImage.setAdapter(adapterImage);
    }

    private void initListener() {
        binding.setClick(v -> {
            presenter.click(v);
        });
    }

    private void initData() {
        p_id = getIntent().getStringExtra("p_id");
        e_id = getIntent().getStringExtra("e_id");
        presenter = new QualityDetailsPresenter(this, this, this);
        presenter.request(p_id, e_id);

    }

    @Override
    public void responseQualityDetails(QualityListRep rep) {
        binding.setViewModel(new QualityListVM(rep));
        if (rep.getImageRepList() == null || rep.getImageRepList().isEmpty()) {
            binding.rvImage.setVisibility(View.GONE);
        } else {
            binding.rvImage.setVisibility(View.VISIBLE);
            adapterImage.setData(rep.getImageRepList());
            int line = (int) Math.ceil(adapterImage.getItemCount() / 4f);
            LogUtil.d(TAG, "line  " + line);
            int height = (int) ViewUtil.dp2px(this, 80) * line;
            LogUtil.d(TAG, "height  " + height);
            ViewUtil.setViewHeight(binding.rvImage, height);
        }

    }

    @Subscribe()
    public void onMessageEvent(RefreshEven event) {
        // 更新界面
        presenter.request(p_id, e_id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
