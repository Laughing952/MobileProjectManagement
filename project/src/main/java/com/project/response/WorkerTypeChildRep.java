package com.project.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Water on 2018/5/14.
 */

public class WorkerTypeChildRep {
    @SerializedName("dictCodeId")
    private String childId;
    @SerializedName("dictCodeName")
    private String content;
    private boolean isSel;

    public WorkerTypeChildRep(String childId, String content) {
        this.childId = childId;
        this.content = content;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSel() {
        return isSel;
    }

    public void setSel(boolean sel) {
        isSel = sel;
    }
}
