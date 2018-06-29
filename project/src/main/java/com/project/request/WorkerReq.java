package com.project.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 工人信息提交
 * Created by Water on 2018/5/14.
 */

public class WorkerReq implements Serializable {

    @SerializedName("workerId")
    private String workerID; // 工人唯一标识
    @SerializedName("workerName")
    private String workerName; // 名字
    @SerializedName("workerIDcard")
    private String workerIDCardNum; // 证件号码
    @SerializedName("workerAddress")
    private String workerAddress; //地址
    @SerializedName("workerTel")
    private String workerMobile;// 手机号
    @SerializedName("workerType")
    private String workerType;// 工种
    @SerializedName("workerTypeName")
    private String workerTypeInfo; // 工种说明
    @SerializedName("workerSalary")
    private String salary; // 工资
    @SerializedName("note")
    private String remark; // 备注
    @SerializedName("workerIDcardPic")
    private String IDCardImg; // 证件照片

    public String getWorkerID() {
        return workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerIDCardNum() {
        return workerIDCardNum;
    }

    public void setWorkerIDCardNum(String workerIDCardNum) {
        this.workerIDCardNum = workerIDCardNum;
    }

    public String getWorkerAddress() {
        return workerAddress;
    }

    public void setWorkerAddress(String workerAddress) {
        this.workerAddress = workerAddress;
    }

    public String getWorkerMobile() {
        return workerMobile;
    }

    public void setWorkerMobile(String workerMobile) {
        this.workerMobile = workerMobile;
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public String getWorkerTypeInfo() {
        return workerTypeInfo;
    }

    public void setWorkerTypeInfo(String workerTypeInfo) {
        this.workerTypeInfo = workerTypeInfo;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIDCardImg() {
        return IDCardImg;
    }

    public void setIDCardImg(String IDCardImg) {
        this.IDCardImg = IDCardImg;
    }
}
