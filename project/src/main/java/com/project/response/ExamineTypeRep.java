package com.project.response;

import com.global.listener.TextShow;
import com.google.gson.annotations.SerializedName;

/**
 * 检查类型
 * Created by Water on 2018/5/10.
 */

public class ExamineTypeRep implements TextShow {

    @SerializedName("dictCodeId")
    private String id;
    @SerializedName("dictCodeName")
    private String name;

    public ExamineTypeRep(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getContent() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
