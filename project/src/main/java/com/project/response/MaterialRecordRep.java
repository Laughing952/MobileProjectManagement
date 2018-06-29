package com.project.response;

import com.google.gson.annotations.SerializedName;

/**
 * 物资流水
 * Created by Water on 2018/5/23.
 */

public class MaterialRecordRep {

    @SerializedName("createTime")
    private String date; // 日期
    @SerializedName("userName")
    private String recorder; // 记录人
    @SerializedName("itemName")
    private String projectName;// 项目名称
    @SerializedName("outNumber")
    private String outNum;// 数量
    @SerializedName("inNumber")
    private String inNum;// 数量

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getInNum() {
        return inNum;
    }

    public void setInNum(String inNum) {
        this.inNum = inNum;
    }
}
