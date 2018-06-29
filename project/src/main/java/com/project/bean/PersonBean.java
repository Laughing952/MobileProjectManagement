package com.project.bean;

import java.io.Serializable;

/**
 * 项目成员
 * 作者：Laughing on 2018/5/4 13:26
 * 邮箱：719240226@qq.com
 */

public class PersonBean implements Serializable {
    private String name;// 人员姓名
    private String id;// 人员ID
//    private boolean isSel;//是否选中

//    public ProjectMemberCheckedVM(String name, String id, boolean isSel) {
//        this.name = name;
//        this.id = id;
//        this.isSel = isSel;
//    }

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

}
