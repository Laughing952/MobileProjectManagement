package com.mycentre.ui.presenter;


import com.global.util.ImageUtils;
import com.mycentre.api.RetrofitHelper;
import com.mycentre.request.FeedbackReq;
import com.mycentre.response.ImageRep;
import com.mycentre.ui.mvpview.IFeedbackView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;

import okhttp3.MultipartBody;

/**
 * Created by edz on 2018/5/17.
 */

public class FeedbackPresenter {

    private IFeedbackView view;
    private BaseActivity activity;
    private LifecycleProvider provider;
    private final HttpManager httpManager;

    public FeedbackPresenter(IFeedbackView view, BaseActivity activity, LifecycleProvider provider){
        this.view=view;
        this.activity=activity;
        this.provider=provider;
        httpManager = new HttpManager(activity, provider);
    }

    /**
     * 上传图片
     * */
    public void uploadImage(String imgPath) {
        MultipartBody.Part part = ImageUtils.fileToMultipartBodyPart(imgPath);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadImage(part),
                new DefaultObserver<ImageRep>() {
                    @Override
                    public void onSuccess(ImageRep response) {
                        view.resultImageChoiced(response);
                    }
                });
    }

    /**
     * 上传反馈信息
     * */
    public void uploadFeedbackInfo(FeedbackReq feedbackReq){
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadFeedbackInfo(feedbackReq),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        view.resultUploadFeedback();
                    }
                });
    }
}
