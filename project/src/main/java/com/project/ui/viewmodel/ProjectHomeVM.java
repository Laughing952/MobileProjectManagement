package com.project.ui.viewmodel;

import android.annotation.SuppressLint;

import com.project.response.ProjectHomeRep;

/**
 * 项目详情页面的数据模型
 * Created by Water on 2018/5/7.
 */

public class ProjectHomeVM {

    private ProjectHomeRep projectHomeRep;

    private String designSchedule; // 计划进度
    private String manpower; // 人工
    private String safetyAbarbeitung; // 安全整改
    private String qualityAbarbeitung; // 质量整改

    public ProjectHomeVM(ProjectHomeRep projectHomeRep) {
        this.projectHomeRep = projectHomeRep;
    }

    public String getDesignSchedule() {
        return projectHomeRep.getDesignSchedule() + "%";
    }

    @SuppressLint("DefaultLocale")
    public String getManpower() {
        return String.format("%.1f", projectHomeRep.getManpower());
    }

    public String getSafetyAbarbeitung() {
        return projectHomeRep.getSafetyAbarbeitung() + "项";
    }

    public String getQualityAbarbeitung() {
        return projectHomeRep.getQualityAbarbeitung() + "项";
    }
}
