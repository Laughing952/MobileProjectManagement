package com.unistrong.working.ui.viewmodel;

import com.global.util.TransformUtil;
import com.unistrong.working.R;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.response.ImageRep;
import com.waterbase.utile.DateUtil;
import com.waterbase.utile.Utils;

/**
 * Created by edz on 2018/5/18.
 */

public class GeneralCCMeItemVM {
    private Long approveId;//项目审批ID
    private String approveStatus;//审批状态 0:待审批 1:驳回 2审批通过
    private String approveTitle;//标题
    private String approveNote;//内容
    private ImageRep imageRep;//头像
    private String approveTime;//创建时间
    private  String userName;//申请人姓名
    private int titleColor;//标题字体颜色
    private GeneralRequestItemDetailRep itemDetailRep;

    public GeneralCCMeItemVM(GeneralRequestItemDetailRep itemDetailRep) {
        this.itemDetailRep=itemDetailRep;
    }

    public Long getApproveId() {
        return itemDetailRep.getApproveId();
    }

    public void setApproveId(Long approveId) {
        this.approveId = approveId;
    }

    public String getApproveStatus() {
        return TransformUtil.ApprovalStatusToShowString(itemDetailRep.getApproveStatus());
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveTitle() {
        return itemDetailRep.getApproveTitle();
    }

    public void setApproveTitle(String approveTitle) {
        this.approveTitle = approveTitle;
    }

    public String getApproveNote() {
        return itemDetailRep.getApproveNote();
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public ImageRep getImageRep() {
        return itemDetailRep.getImageRep();
    }

    public void setImageRep(ImageRep imageRep) {
        this.imageRep = imageRep;
    }

    public String getApproveTime() {
        return DateUtil.getStringByFormat(itemDetailRep.getApproveTime(),"yyyy-MM-dd");
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    public String getUserName() {
        return itemDetailRep.getUserName()+"提交的";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTitleColor() {
        String isRead = itemDetailRep.getIsRead();//是否已阅 0: 未阅读 1:已阅读
        if ("0".equals(isRead)){
            return Utils.getContext().getResources().getColor(R.color.color_no_dispose);
        }else{
            return Utils.getContext().getResources().getColor(R.color.color_disposed);
        }
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public GeneralRequestItemDetailRep getItemDetailRep() {
        return itemDetailRep;
    }

    public void setItemDetailRep(GeneralRequestItemDetailRep itemDetailRep) {
        this.itemDetailRep = itemDetailRep;
    }

}
