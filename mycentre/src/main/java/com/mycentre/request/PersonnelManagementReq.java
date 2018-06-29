package com.mycentre.request;

/**
 * Created by Water on 2018/5/9.
 */

public class PersonnelManagementReq {

    private String name; // 姓名
    private String departmentId; // 部门id
    private String dutyId; // 职务ID

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }
}
