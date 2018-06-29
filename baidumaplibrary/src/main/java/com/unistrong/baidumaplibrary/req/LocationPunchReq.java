package com.unistrong.baidumaplibrary.req;

/**
 * 定位打卡Req
 * 作者：Laughing on 2018/5/27 13:29
 * 邮箱：719240226@qq.com
 */

public class LocationPunchReq {

    private String itemId;//项目ID

    private String signinPointId;//考勤点ID

    private long userId;//用户ID

    private String location;//LOCATION打卡位置

    private String signinRange;//打卡距离

    private String signinNote;//备注

    private String signinType;//打卡类型0:上班打卡1：下班打卡2：外勤打卡

    private long creater;//创建人ID (即用户ID)

    private String signinPointName;//考勤点名称

    private String personSigninLongTitude;//个人考勤地址经度

    private String personSigninLaTitude;//个人考勤地址纬度


    public LocationPunchReq(String itemId, String signinPointId, long userId, String location,
                            String signinRange, String signinNote, String signinType,
                            long creater, String signinPointName, String personSigninLongTitude,
                            String personSigninLaTitude) {

        this.itemId = itemId;
        this.signinPointId = signinPointId;
        this.userId = userId;
        this.location = location;
        this.signinRange = signinRange;
        this.signinNote = signinNote;
        this.signinType = signinType;
        this.creater = creater;
        this.signinPointName = signinPointName;
        this.personSigninLongTitude = personSigninLongTitude;
        this.personSigninLaTitude = personSigninLaTitude;

    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSigninPointId() {
        return signinPointId;
    }

    public void setSigninPointId(String signinPointId) {
        this.signinPointId = signinPointId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSigninRange() {
        return signinRange;
    }

    public void setSigninRange(String signinRange) {
        this.signinRange = signinRange;
    }

    public String getSigninNote() {
        return signinNote;
    }

    public void setSigninNote(String signinNote) {
        this.signinNote = signinNote;
    }

    public String getSigninType() {
        return signinType;
    }

    public void setSigninType(String signinType) {
        this.signinType = signinType;
    }

    public long getCreater() {
        return creater;
    }

    public void setCreater(long creater) {
        this.creater = creater;
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

}
