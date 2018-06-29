package com.project.ui.viewmodel;

import com.google.gson.annotations.SerializedName;
import com.project.response.ImageRep;
import com.waterbase.http.common.RxRetrofitApp;

/**
 * Created by Water on 2018/5/29.
 */

public class ImageVM {

    private ImageRep imageRep;

    private String thumUrl; // 缩略图
    private String url;  // 原图

    public ImageVM(ImageRep imageRep) {
        this.imageRep = imageRep;
    }

    public String getThumUrl() {
        return RxRetrofitApp.getApiServerUrl() + imageRep.getThumUrl();
    }

    public String getUrl() {
        return RxRetrofitApp.getApiServerUrl() + imageRep.getUrl();
    }
}
