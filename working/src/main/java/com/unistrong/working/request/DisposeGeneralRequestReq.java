package com.unistrong.working.request;

/**
 * Created by edz on 2018/5/21.
 */

public class DisposeGeneralRequestReq {

    private Long approveId;//项目审批ID

    private String approveStatus;//审批状态0:待审批1:驳回2审批通过

    private String approveComment;//审批意见

    private String isDel;//是否删除 0 ：未删除  1：删除

    private String isRead;//是否已阅 0: 未阅读 1:已阅读、

    private long deliverCkUserId;//被转送人

    private long userId;//转送人Id

    private String deliverCkUserName;//被转送人姓名

    public String getDeliverCkUserName() {
        return deliverCkUserName;
    }

    public void setDeliverCkUserName(String deliverCkUserName) {
        this.deliverCkUserName = deliverCkUserName;
    }

    public long getDeliverCkUserId() {
        return deliverCkUserId;
    }

    public void setDeliverCkUserId(long deliverCkUserId) {
        this.deliverCkUserId = deliverCkUserId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
}
