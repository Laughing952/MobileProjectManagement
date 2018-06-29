package com.unistrong.baidumaplibrary.bean;

import java.io.Serializable;

/**
 * 考勤地点
 * 作者：Laughing on 2018/5/24 14:13
 * 邮箱：719240226@qq.com
 */

public class MyPoi implements Serializable {
    private String rank;
    private String id;
    private String name;

    public MyPoi(String rank, String id, String name) {
        this.rank = rank;
        this.id = id;
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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
