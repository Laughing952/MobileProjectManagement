package com.project.response;

import com.google.gson.annotations.SerializedName;
import com.project.widget.CN;

import java.io.Serializable;

import retrofit2.http.Query;

/**
 * 工人信息
 * Created by Water on 2018/5/14.
 */

public class WorkerRep implements Serializable, CN {

    @SerializedName("workerId")
    private String workweId; // 工人ID 唯一标识
    private float hours;// 工时
    @SerializedName("workerName")
    private String name;
    private String imgUrl;

    private boolean isCheck;

    @Override
    public String toString() {
        return "WorkerRep{" +
                "workweId='" + workweId + '\'' +
                ", hours=" + hours +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }

    public WorkerRep(String workweId, float hours, String name) {
        this.workweId = workweId;
        this.hours = hours;
        this.name = name;
    }

    public WorkerRep(String workweId, String name) {
        this.workweId = workweId;
        this.name = name;
    }

    public WorkerRep(String workweId, String name, String imgUrl) {
        this.workweId = workweId;
        this.name = name;
        this.imgUrl = imgUrl;
    }



    @Override
    public String chinese() {
        return name;
    }

    public String getWorkweId() {
        return workweId;
    }

    public void setWorkweId(String workweId) {
        this.workweId = workweId;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
