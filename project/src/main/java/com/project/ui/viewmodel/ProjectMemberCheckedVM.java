package com.project.ui.viewmodel;

import com.project.R;
import com.waterbase.widget.pickerview.model.IPickerViewData;

import java.io.Serializable;

/**
 * 项目成员
 * 作者：Laughing on 2018/5/4 13:26
 * 邮箱：719240226@qq.com
 */

public class ProjectMemberCheckedVM implements Serializable,IPickerViewData{
    private String realname;// 人员姓名
    private String userId;// 人员ID
    private int imageRes; // check图片
    private boolean isSel; // 是否选中

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getImageRes() {

        if (isSel)
            return R.mipmap.ic_checked_box;
        else
            return R.mipmap.ic_unchecked_box;
    }


    public boolean isSel() {
        return isSel;
    }

    public void setSel(boolean sel) {
        isSel = sel;
    }

    @Override
    public String getPickerViewText() {
        return realname;
    }
}
