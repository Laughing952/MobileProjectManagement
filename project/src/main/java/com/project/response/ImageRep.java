package com.project.response;

import com.global.listener.ImageShow;
import com.google.gson.annotations.SerializedName;
import com.waterbase.http.common.RxRetrofitApp;

import java.io.Serializable;

/**
 * 图片
 * Created by Water on 2018/5/10.
 */

public class ImageRep implements ImageShow, Serializable {

    @SerializedName("thumbnail")
    private String thumUrl; // 缩略图
    @SerializedName("headImg")
    private String url;  // 原图

    public ImageRep(String thumUrl) {
        this.thumUrl = thumUrl;
    }

    public String getThumUrl() {
        return thumUrl;
    }

    public void setThumUrl(String thumUrl) {
        this.thumUrl = thumUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getShowImageUrl() {
        return RxRetrofitApp.getApiServerUrl() + thumUrl;
    }
}
