package com.unistrong.working.response;

import com.google.gson.annotations.SerializedName;
import com.waterbase.widget.pickerview.model.IPickerViewData;

import java.io.Serializable;

/**
 * 项目名称-获取项目列表 Rep
 * 作者：Laughing on 2018/5/13 15:11
 * 邮箱：719240226@qq.com
 */

public class CreateNewTask_ProjectNameRep implements IPickerViewData, Serializable {
    @SerializedName("itemId")
    private String itemid;
    @SerializedName("itemName")
    private String itemname;

    public CreateNewTask_ProjectNameRep(String itemid, String itemname) {
        this.itemid = itemid;
        this.itemname = itemname;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    @Override
    public String getPickerViewText() {
        return itemname;
    }
}
