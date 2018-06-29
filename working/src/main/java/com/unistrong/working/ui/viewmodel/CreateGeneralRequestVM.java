package com.unistrong.working.ui.viewmodel;

import com.unistrong.working.response.ImageRep;
import java.util.List;

/**
 * 创建通用申请
 * 作者：Laughing on 2018/5/8 20:27
 * 邮箱：719240226@qq.com
 */

public class CreateGeneralRequestVM {

    private String approveCkUser;//审批人
    private String approveCcUser;//抄送人
    private String approveItemName;//项目名称
    private String approveTitle;//标题
    private String approveNote;//内容
    private List<ImageRep> imgUrl;//图片
    private  String userName;//申请人姓名

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

    public List<ImageRep> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<ImageRep> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
