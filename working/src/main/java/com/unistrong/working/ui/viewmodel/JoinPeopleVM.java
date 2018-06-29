package com.unistrong.working.ui.viewmodel;


import com.unistrong.working.R;

/**
 * 创建参与人
 * Created by Laughing on 2018/5/16 21:14.
 */

public class JoinPeopleVM {

    private String peopleId; //人员ID
    private int imageRes; // 权限图片
    private String peopleName;  // 人员姓名
    private boolean isSel; // 是否选中

    private int checkBoxImgRes;

    public JoinPeopleVM(String peopleId, int imageRes, String peopleName, boolean isSel) {
        this.peopleId = peopleId;
        this.imageRes = imageRes;
        this.peopleName = peopleName;
        this.isSel = isSel;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public boolean isSel() {
        return isSel;
    }

    public void setSel(boolean sel) {
        isSel = sel;
    }

    public int getCheckBoxImgRes() {
        if (isSel())
            return R.mipmap.ic_checked_box;
        else
            return R.mipmap.ic_unchecked_box;
    }
}
