package com.mycentre.response;

import java.util.List;

/**
 * 人员管理中人员信息修改返回的人员详情
 * Created by Water on 2018/5/9.
 */

public class PersonnelDetailsRep {

    private String userID;
    private String userName; // 姓名
    private String divisionalId;
    private String divisionalName;// 部门名称
    private String dutyID;
    private String dutyName; // 职务名称
    private List<JurisdictionRep> jurisdictionRepList; // 权限

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDivisionalId() {
        return divisionalId;
    }

    public void setDivisionalId(String divisionalId) {
        this.divisionalId = divisionalId;
    }

    public String getDivisionalName() {
        return divisionalName;
    }

    public void setDivisionalName(String divisionalName) {
        this.divisionalName = divisionalName;
    }

    public String getDutyID() {
        return dutyID;
    }

    public void setDutyID(String dutyID) {
        this.dutyID = dutyID;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public List<JurisdictionRep> getJurisdictionRepList() {
        return jurisdictionRepList;
    }

    public void setJurisdictionRepList(List<JurisdictionRep> jurisdictionRepList) {
        this.jurisdictionRepList = jurisdictionRepList;
    }
}
