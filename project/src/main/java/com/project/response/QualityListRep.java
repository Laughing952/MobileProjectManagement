package com.project.response;

import com.google.gson.annotations.SerializedName;
import com.waterbase.widget.recycleview.BaseAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * 质量检查列表
 * Created by Water on 2018/5/10.
 */

public class QualityListRep implements Serializable {

    @SerializedName("itemId")
    private String pId;
    @SerializedName("itemName")
    private String p_name;
    @SerializedName("qcId")
    private String e_id; // 质量检查ID
    @SerializedName("qcDate")
    private String e_date; // 质量检查时间 YYYYMMDD HH:MM
    private String qcType;
    @SerializedName("qcTypeName")
    private String e_divisionalName; //检查部门名称
    @SerializedName("qcList")
    private String e_items; //检查项目
    @SerializedName("userName")
    private String e_person; // 检查人
    @SerializedName("qcResult")
    private String e_resultContent; // 检查结果说明
    @SerializedName("qcIspass")
    private int e_result; // 检查结果
    @SerializedName("imgList")
    private List<ImageRep> imageRepList;

    public QualityListRep(String e_date, String e_divisionalName, String e_items, String e_person
            , String e_resultContent, int e_result, List<ImageRep> imageRepList) {
        this.e_date = e_date;
        this.e_divisionalName = e_divisionalName;
        this.e_items = e_items;
        this.e_person = e_person;
        this.e_resultContent = e_resultContent;
        this.e_result = e_result;
        this.imageRepList = imageRepList;
    }

    public QualityListRep(String p_name, String e_id, String e_date, String e_divisionalName
            , String e_items, String e_person, String e_resultContent
            , int e_result, List<ImageRep> imageRepList) {
        this.p_name = p_name;
        this.e_id = e_id;
        this.e_date = e_date;
        this.e_divisionalName = e_divisionalName;
        this.e_items = e_items;
        this.e_person = e_person;
        this.e_resultContent = e_resultContent;
        this.e_result = e_result;
        this.imageRepList = imageRepList;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }


    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }

    public String getE_divisionalName() {
        return e_divisionalName;
    }

    public void setE_divisionalName(String e_divisionalName) {
        this.e_divisionalName = e_divisionalName;
    }

    public String getE_items() {
        return e_items;
    }

    public void setE_items(String e_items) {
        this.e_items = e_items;
    }

    public String getE_person() {
        return e_person;
    }

    public void setE_person(String e_person) {
        this.e_person = e_person;
    }

    public String getE_resultContent() {
        return e_resultContent;
    }

    public void setE_resultContent(String e_resultContent) {
        this.e_resultContent = e_resultContent;
    }

    public int getE_result() {
        return e_result;
    }

    public void setE_result(int e_result) {
        this.e_result = e_result;
    }

    public List<ImageRep> getImageRepList() {
        return imageRepList;
    }

    public void setImageRepList(List<ImageRep> imageRepList) {
        this.imageRepList = imageRepList;
    }

    public String getQcType() {
        return qcType;
    }

    public void setQcType(String qcType) {
        this.qcType = qcType;
    }
}
