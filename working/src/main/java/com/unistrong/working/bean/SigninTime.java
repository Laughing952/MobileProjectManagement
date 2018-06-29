package com.unistrong.working.bean;

/**
 * 考勤规则添加-星期的考勤数据
 * 作者：Laughing on 2018/5/25 10:28
 * 邮箱：719240226@qq.com
 */

public class SigninTime {

    private String signinTimeId;//考勤时间设置ID

    private String signinDay;//考勤日 0:每周一  1：每周二  ....6:周日

    private String signinStartTime;//考勤开始时间

    private String signinEndTime;//考勤结束时间


    private String signinIsBreak;//是否休息 1:休息 0:不休息

    public SigninTime(String signinTimeId, String signinDay, String signinStartTime, String signinEndTime, String signinIsBreak) {
        this.signinTimeId = signinTimeId;
        this.signinDay = signinDay;
        this.signinStartTime = signinStartTime;
        this.signinEndTime = signinEndTime;
        this.signinIsBreak = signinIsBreak;
    }

    public String getSigninDay() {
        return signinDay;
    }

    public void setSigninDay(String signinDay) {
        this.signinDay = signinDay;
    }

    public String getSigninStartTime() {
        return signinStartTime;
    }

    public void setSigninStartTime(String signinStartTime) {
        this.signinStartTime = signinStartTime;
    }

    public String getSigninEndTime() {
        return signinEndTime;
    }

    public void setSigninEndTime(String signinEndTime) {
        this.signinEndTime = signinEndTime;
    }

    public String getSigninIsBreak() {
        return signinIsBreak;
    }

    public void setSigninIsBreak(String signinIsBreak) {
        this.signinIsBreak = signinIsBreak;
    }

    public String getSigninTimeId() {
        return signinTimeId;
    }

    public void setSigninTimeId(String signinTimeId) {
        this.signinTimeId = signinTimeId;
    }
}
