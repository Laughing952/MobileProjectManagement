package com.project.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 工种
 * Created by Water on 2018/5/14.
 */

public class WorkerTypeRep {
    @SerializedName("dictCodeId")
    private String id;
    @SerializedName("dictCodeName")
    private String content;
    @SerializedName("childList")
    private List<WorkerTypeChildRep> childRepList;

    public WorkerTypeRep(String id, String content, List<WorkerTypeChildRep> childRepList) {
        this.id = id;
        this.content = content;
        this.childRepList = childRepList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<WorkerTypeChildRep> getChildRepList() {
        return childRepList;
    }

    public void setChildRepList(List<WorkerTypeChildRep> childRepList) {
        this.childRepList = childRepList;
    }
}
