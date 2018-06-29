package com.mycentre.ui.viewmodel;

import com.mycentre.response.PersonnelManagementRep;

/**
 * 人员管理
 * Created by Water on 2018/5/8.
 */

public class PersonnelManagementVM {

    private PersonnelManagementRep rep;

    private String headUrl; // 头像
    private String name;
    private String duty;// 职位

    public PersonnelManagementVM(PersonnelManagementRep rep) {
        this.rep = rep;
    }

    public String getHeadUrl() {
        return rep.getHeadUrl();
    }

    public String getName() {
        return rep.getName();
    }

    public String getDuty() {
        return rep.getDuty();
    }
}
