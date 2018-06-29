package com.project.response;

import com.google.gson.annotations.SerializedName;

/**
 * 项目详情页面的返回
 * Created by Water on 2018/5/7.
 */

public class ProjectHomeRep {
    @SerializedName("schedule")
    private int designSchedule; // 计划进度
    @SerializedName("workDay")
    private float manpower; // 人工
    @SerializedName("security")
    private int safetyAbarbeitung; // 安全整改
    @SerializedName("quality")
    private int qualityAbarbeitung; // 质量整改

    public int getDesignSchedule() {
        return designSchedule;
    }

    public void setDesignSchedule(int designSchedule) {
        this.designSchedule = designSchedule;
    }

    public float getManpower() {
        return manpower;
    }

    public void setManpower(float manpower) {
        this.manpower = manpower;
    }

    public int getSafetyAbarbeitung() {
        return safetyAbarbeitung;
    }

    public void setSafetyAbarbeitung(int safetyAbarbeitung) {
        this.safetyAbarbeitung = safetyAbarbeitung;
    }

    public int getQualityAbarbeitung() {
        return qualityAbarbeitung;
    }

    public void setQualityAbarbeitung(int qualityAbarbeitung) {
        this.qualityAbarbeitung = qualityAbarbeitung;
    }
}
