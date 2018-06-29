package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter2;
import com.mycentre.R;
import com.mycentre.databinding.AMyCenterFeedbackBinding;
import com.mycentre.request.FeedbackReq;
import com.mycentre.response.ImageRep;
import com.mycentre.ui.mvpview.IFeedbackView;
import com.mycentre.ui.presenter.FeedbackPresenter;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ToastUtil;
import com.waterbase.utile.ViewUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心-意见反馈
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_My_Center_Feedback extends TitleActivity implements IFeedbackView<ImageRep> {

    private List<Map<String, Object>> mDatas;
    private GridViewAddImageAdapter2 mAdapter;
    private AMyCenterFeedbackBinding mBinding;
    private Context mContext = this;
    private FeedbackPresenter presenter;
    private FeedbackReq feedbackReq;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_My_Center_Feedback.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_my_center_feedback);
        initTitle();
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mAdapter = new GridViewAddImageAdapter2(this);
        mBinding.gvFeedback.setAdapter(mAdapter);
    }

    private void initData() {
        presenter = new FeedbackPresenter(this, this, this);
        feedbackReq = new FeedbackReq();
    }

    private void initListener() {
        mBinding.gvFeedback.setOnItemClickListener((parent, view, position, id) -> {
            startActivityForResult(new Intent(this, MyPhotoActivity.class), 0x06);
        });

        mBinding.setClick(v -> {
            feedbackReq.setImgList(mAdapter.getDatas());
            feedbackReq.setOpinionContent(mBinding.etActivityFeedbackContent.getText().toString().trim());
            feedbackReq.setUserId(20L);
            presenter.uploadFeedbackInfo(feedbackReq);
        });
    }

    public void initTitle() {
        setTitleText("意见反馈");//标题
    }

    /**
     * 拍完照片后返回的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0x06:
                if (resultCode == MyPhotoActivity.FINSH_RESULT && data != null) {
                    String path = data.getStringExtra("image");
                    presenter.uploadImage(path);
                }
                break;
        }

    }

    @Override
    public void resultImageChoiced(ImageRep data) {
        mAdapter.addData(data);
    }

    @Override
    public void resultUploadFeedback() {
        ToastUtil.showToast(this, "完成反馈");
    }
}
