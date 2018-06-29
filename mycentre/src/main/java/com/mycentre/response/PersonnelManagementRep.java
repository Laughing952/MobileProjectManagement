package com.mycentre.response;

import java.io.Serializable;

/**
 * Created by Water on 2018/5/8.
 */

public class PersonnelManagementRep implements Serializable {

    private String userId;
    private String name;
    private String headUrl;
    private String duty;

    public PersonnelManagementRep(String userId, String name, String headUrl, String duty) {
        this.userId = userId;
        this.name = name;
        this.headUrl = headUrl;
        this.duty = duty;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}
