package com.unistrong.working.response;

import com.global.util.TransformUtil;
import com.google.gson.annotations.SerializedName;
import com.unistrong.working.ui.viewmodel.ApprovalHistoryVM;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by edz on 2018/5/15.
 */

public class GeneralRequestItemDetailRep{


    private Long approveId;//项目审批ID

    private String approveCkUser;//审批人

    private String approveCcUser;//抄送人

    private String approveStatus;//审批状态0:待审批1:驳回2审批通过

    private String approveComment;//审批意见

    private String itemId;//项目ID

    private String approveItemName;//项目名称

    private String approveTitle;//标题

    private String approveNote;//内容

    @SerializedName("imgUrl")
    private List<ImageRep> approveImgUrl;//图片

    private ImageRep imageRep;//头像

    private Date approveCkTime;//审批时间

    private Date approveTime;//创建时间

    private  String userName;//申请人姓名

    private String isRead;//是否已阅 0: 未阅读 1:已阅读、

    private List<ApprovalHistoryVM> deliverList;//审批历史

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<ApprovalHistoryVM> getDeliverList() {
        return deliverList;
    }

    public void setDeliverList(List<ApprovalHistoryVM> deliverList) {
        this.deliverList = deliverList;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Long getApproveId() {
        return approveId;
    }

    public void setApproveId(Long approveId) {
        this.approveId = approveId;
    }

    public String getApproveCkUser() {
        return approveCkUser;
    }

    public void setApproveCkUser(String approveCkUser) {
        this.approveCkUser = approveCkUser;
    }

    public String getApproveCcUser() {
        return approveCcUser;
    }

    public void setApproveCcUser(String approveCcUser) {
        this.approveCcUser = approveCcUser;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveComment() {
        return approveComment;
    }

    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment;
    }

    public String getApproveItemName() {
        return approveItemName;
    }

    public void setApproveItemName(String approveItemName) {
        this.approveItemName = approveItemName;
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

    public List<ImageRep> getApproveImgUrl() {
        return approveImgUrl;
    }

    public void setApproveImgUrl(List<ImageRep> approveImgUrl) {
        this.approveImgUrl = approveImgUrl;
    }

    public ImageRep getImageRep() {
        return imageRep;
    }

    public void setImageRep(ImageRep imageRep) {
        this.imageRep = imageRep;
    }

    public Date getApproveCkTime() {
        return approveCkTime;
    }

    public void setApproveCkTime(Date approveCkTime) {
        this.approveCkTime = approveCkTime;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
