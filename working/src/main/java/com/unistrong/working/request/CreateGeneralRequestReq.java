package com.unistrong.working.request;

import com.google.gson.annotations.SerializedName;
import com.unistrong.working.response.ImageRep;
import java.util.List;

/**
 * Created by edz on 2018/5/15.
 */

public class CreateGeneralRequestReq {
    private long approveCkUserId;//审批人id
    private long approveCcUserId;//抄送人id
    @SerializedName("itemId")
    private String itemid;//项目id
    private String approveTitle;//标题
    private String approveNote;//内容
    private List<ImageRep> imgUrl;//图片
    private long userId;//申请人id

    public long getApproveCkUserId() {
        return approveCkUserId;
    }

    public void setApproveCkUserId(long approveCkUserId) {
        this.approveCkUserId = approveCkUserId;
    }

    public long getApproveCcUserId() {
        return approveCcUserId;
    }

    public void setApproveCcUserId(long approveCcUserId) {
        this.approveCcUserId = approveCcUserId;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getApproveTitle() {
        return approveTitle;
    }

    public void setApproveTitle(String approveTitle) {
        this.approveTitle = approveTitle;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public List<ImageRep> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<ImageRep> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
