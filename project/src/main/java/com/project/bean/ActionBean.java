package com.project.bean;

/**
 * Created by Water on 2018/5/7.
 */

public class ActionBean {

    private int id;
    private String name;
    private int imageRes;

    public ActionBean(int id, String name, int imageRes) {
        this.id = id;
        this.name = name;
        this.imageRes = imageRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
