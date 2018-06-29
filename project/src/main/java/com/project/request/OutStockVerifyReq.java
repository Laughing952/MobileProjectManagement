package com.project.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.Body;

/**
 * 出库:确认信息
 * Created by Water on 2018/5/24.
 */

public class OutStockVerifyReq {
    @SerializedName("itemId")
    private String pId; // 项目id
    @SerializedName("creatTime")
    private String date;//领料日期
    @SerializedName("matterPersion")
    private String receiver;//领料人
    @SerializedName("note")
    private String marker; // 备注
    @SerializedName("list")
    private List<MaterialsReq> materialsRepList; // 物资列表


    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public List<MaterialsReq> getMaterialsRepList() {
        return materialsRepList;
    }

    public void setMaterialsRepList(List<MaterialsReq> materialsRepList) {
        this.materialsRepList = materialsRepList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
}
