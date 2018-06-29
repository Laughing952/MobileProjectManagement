package com.mycentre.request;


import com.mycentre.response.ImageRep;

import java.util.List;

/**
 * Created by cuihao on 2018/5/17.
 */

public class FeedbackReq {
    private Long  userId;//用户ID
    private List<ImageRep> imgList;//照片
    private String opinionContent;//意见

    public List<ImageRep> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImageRep> imgList) {
        this.imgList = imgList;
    }

    public String getOpinionContent() {
        return opinionContent;
    }

    public void setOpinionContent(String opinionContent) {
        this.opinionContent = opinionContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
