package com.project.ui.viewmodel;


import com.project.response.ProjectDoingRep;

/**
 * 正在进行的项目
 * 作者：Laughing on 2018/5/4 14:04
 * 邮箱：719240226@qq.com
 */

public class ProjectDoingVM {
    private String name;//项目名
    private String id;//项目id
    private String principal;//负责人
    private String principalId;//负责人Id
    private String progress;//项目进度
    private ProjectDoingRep mProjectDoingRep;//

    public ProjectDoingVM(ProjectDoingRep projectDoingRep) {
        this.mProjectDoingRep = projectDoingRep;
    }

    public String getName() {
        return mProjectDoingRep.getItemname();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return mProjectDoingRep.getItemid();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrincipal() {
        return mProjectDoingRep.getModifier();
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getProgress() {
        return mProjectDoingRep.getThumbnail();
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
