package com.unistrong.working.response;

import java.io.Serializable;

/**
 * 项目
 * Created by Laughing on 2018/5/24.
 */

public class ProjectRep implements Serializable {

    private String projectId;//项目Id
    private String projectName;//项目名

    public ProjectRep(String projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
