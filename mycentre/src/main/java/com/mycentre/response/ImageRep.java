package com.mycentre.response;

import com.global.listener.ImageShow;
import com.mycentre.api.RetrofitHelper;
import com.waterbase.http.common.RetrofitService;
import com.waterbase.http.common.RxRetrofitApp;
import com.waterbase.ui.BaseApplication;

import java.io.Serializable;

/**
 * 图片
 * Created by Water on 2018/5/10.
 */

public class ImageRep implements ImageShow,Serializable {

    private String thumbnail; // 缩略图
    private String headImg;  // 原图

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "ImageRep{" +
                "thumbnail='" + thumbnail + '\'' +
                ", headImg='" + headImg + '\'' +
                '}';
    }

    @Override
    public String getShowImageUrl() {
        return RxRetrofitApp.getApiServerUrl() +thumbnail;
    }
}
