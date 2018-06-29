package com.mycentre.bean;

/**
 * 功能说明
 * Created by Water on 2018/5/7.
 */

public class CenterHelpFunctionDescriptionBean {

    private int id;//
    private String name;//
    private int imageRes;//图片资源

    public CenterHelpFunctionDescriptionBean(int id, String name, int imageRes) {
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
