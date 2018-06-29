package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.ANewWorkerBinding;
import com.project.request.WorkerReq;
import com.project.response.QualityListRep;
import com.project.response.WorkerRep;
import com.project.ui.mvpview.NewWorkerView;
import com.project.ui.presenter.NewWorkerPresenter;
import com.project.util.LoadImageUtile;
import com.waterbase.http.common.RxRetrofitApp;
import com.waterbase.utile.GlideUtile;

/**
 * 新建/修改工人信息
 * Created by Water on 2018/5/14.
 */

public class A_New_Worker extends TitleActivity implements NewWorkerView {

    public static final int NEW = 67; // 新建
    public static final int UPDATA = 68; // 修改

    private int flag;
    private ANewWorkerBinding binding;

    private NewWorkerPresenter presenter;

    public static void startActivity(Context context, String workerID, int flag) {
        Intent intent = new Intent(context, A_New_Worker.class);
        intent.putExtra("flag", flag);
        intent.putExtra("workerID", workerID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_ok);
        flag = getIntent().getIntExtra("flag", -1);
        if (flag == NEW) {
            setTitleText("新建工人信息");
        } else if (flag == UPDATA) {
            setTitleText("修改工人信息");
        }
        binding = setView(R.layout.a_new_worker);
        initView();
        initListener();
        presenter.initData(flag, getIntent().getStringExtra("workerID"));
    }

    private void initView() {
        if (flag == NEW) {
            binding.tvDel.setVisibility(View.GONE);
        } else if (flag == UPDATA) {
            binding.tvDel.setVisibility(View.VISIBLE);
        }
        presenter = new NewWorkerPresenter(this, this, this);
    }

    private void initListener() {
        binding.setClick(v -> presenter.click(v));
    }

    @Override
    public void initData(WorkerReq workerReq) {
        binding.setWorkerReq(workerReq);
        if (workerReq.getIDCardImg() != null)
            GlideUtile.loadImage(binding.ivSelImage, RxRetrofitApp.getApiServerUrl() + workerReq.getIDCardImg()
                    , R.mipmap.ic_camera, R.mipmap.ic_camera);
    }

    @Override
    public void responseImage(String url) {
        GlideUtile.loadImage(binding.ivSelImage, url, R.mipmap.ic_camera, R.mipmap.ic_camera);
    }

    @Override
    public void showWorkerType(String workerType) {
        binding.tvWorkerTypeInfo.setText(workerType);
    }

    @Override
    protected void rightOneImageOnClick() {
        super.rightOneImageOnClick();
        presenter.request(flag);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
