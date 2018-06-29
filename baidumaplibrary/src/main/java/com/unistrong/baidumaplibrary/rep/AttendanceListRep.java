package com.unistrong.baidumaplibrary.rep;

import java.io.Serializable;

/**
 * 考勤设置(从列表选择一个考勤点进行打卡考勤)
 * 作者：Laughing on 2018/5/23 10:13
 * 邮箱：719240226@qq.com
 */

public class AttendanceListRep implements Serializable {

    private String signinPointId;//考勤点ID

    private String signinPointName;//考勤点名称

    private String signinDay;//考勤日 1:每周一  2：每周二  等

    private String signinDayName;//考勤日名称 每周一；每周二 等

    private String weekDay;  //item 右边的日期           周一、二、三、四、五

    private String isDel;//是否删除 0：未删除 1：删除

    public String getSigninPointId() {
        return signinPointId;
    }

    public void setSigninPointId(String signinPointId) {
        this.signinPointId = signinPointId;
    }

    public String getSigninPointName() {
        return signinPointName;
    }

    public void setSigninPointName(String signinPointName) {
        this.signinPointName = signinPointName;
    }

    public String getSigninDay() {
        return signinDay;
    }

    public void setSigninDay(String signinDay) {
        this.signinDay = signinDay;
    }

    public String getSigninDayName() {
        return signinDayName;
    }

    public void setSigninDayName(String signinDayName) {
        this.signinDayName = signinDayName;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
}


