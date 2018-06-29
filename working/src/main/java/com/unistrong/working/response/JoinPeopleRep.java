package com.unistrong.working.response;

import java.io.Serializable;

/**
 * 任务参与人
 * 作者：Laughing on 2018/5/4 13:26
 * 邮箱：719240226@qq.com
 */


public class JoinPeopleRep implements Serializable {

    private String name;//
    private String id;//
    private boolean isChecked;//是否选中

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
