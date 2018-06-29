package com.unistrong.baidumaplibrary.rep;

import com.global.util.DateUtils;
import com.global.util.TransformUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 获取个人上下班，外出考勤信息
 * 作者：Laughing on 2018/5/27 17:46
 * 邮箱：719240226@qq.com
 */


public class PunchRecordRep implements Serializable {

    private String signinId;//人员考勤记录ID

    private Date signinTime;//打卡时间

    private String location;//LOCATION打卡位置

    private String signinNote;//备注

    private String signinType;//打卡类型0:上班打卡 1：下班打卡 2：外勤打卡

    private String signinPointName;//考勤点名称

    private String personSigninLongTitude;//个人考勤地址经度（新增加的）

    private String personSigninLaTitude;//个人考勤地址纬度（新增加的）

    private String signinRange;//打卡距离

    private String signinPointId;//打卡点id

    private String itemId;//项目Id

    private long userId;//用户Id

    private long creater;//创建人（用户Id）


    public String getSigninId() {
        return signinId;
    }

    public void setSigninId(String signinId) {
        this.signinId = signinId;
    }

    /**
     * 此处对get方法修改，使其返回String类型的时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public String getSigninTime() {
        return DateUtils.transformDate2Str(signinTime);
    }

    public void setSigninTime(Date signinTime) {
        this.signinTime = signinTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        this.location = location;
    }

    public String getSigninNote() {
        return signinNote;
    }

    public void setSigninNote(String signinNote) {
        this.signinNote = signinNote;
    }

    public String getSigninType() {
        return TransformUtil.transformPunchType2String(signinType);
    }

    public void setSigninType(String signinType) {
        this.signinType = signinType;
    }

    public String getSigninPointName() {
        return signinPointName;
    }

    public void setSigninPointName(String signinPointName) {
        this.signinPointName = signinPointName;
    }

    public String getPersonSigninLongTitude() {
        return personSigninLongTitude;
    }

    public void setPersonSigninLongTitude(String personSigninLongTitude) {
        this.personSigninLongTitude = personSigninLongTitude;
    }

    public String getPersonSigninLaTitude() {
        return personSigninLaTitude;
    }

    public void setPersonSigninLaTitude(String personSigninLaTitude) {
        this.personSigninLaTitude = personSigninLaTitude;
    }

    public String getSigninRange() {
        return signinRange ;
    }

    public void setSigninRange(String signinRange) {
        this.signinRange = signinRange;
    }
}
