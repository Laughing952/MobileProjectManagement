package com.mycentre.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;

import com.mycentre.R;

/**
 * 新建/修改职务  权限适配器
 * Created by Water on 2018/5/9.
 */

public class JurisdictionVM {

    private String jurisdictionId; // 权限ID
    private int imageRes; // 权限图片
    private String jurisdictionName;  // 权限名称
    private boolean isSel; // 是否选中

    private int checkBoxImgRes;

    public JurisdictionVM(String jurisdictionId, int imageRes, String jurisdictionName, boolean isSel) {
        this.jurisdictionId = jurisdictionId;
        this.imageRes = imageRes;
        this.jurisdictionName = jurisdictionName;
        this.isSel = isSel;
    }

    public String getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }

    public boolean isSel() {
        return isSel;
    }

    public void setSel(boolean sel) {
        isSel = sel;
    }

    public int getCheckBoxImgRes() {
        if (isSel())
            return R.mipmap.ic_turn_on;
        else
            return R.mipmap.ic_turn_off;

    }
}
