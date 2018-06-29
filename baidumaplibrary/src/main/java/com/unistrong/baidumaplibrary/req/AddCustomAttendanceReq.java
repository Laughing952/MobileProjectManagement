package com.unistrong.baidumaplibrary.req;

/**
 * 新建考勤点 提交的信息/编辑考勤点所展示的信息（参数：object）
 * 作者：Laughing on 2018/5/25 09:05
 * 邮箱：719240226@qq.com
 */

public class AddCustomAttendanceReq {
    private String signinPointId;//考勤点ID

    private String itemId;//项目ID

    private String signinPointName;//考勤点名称

    private String signinPointAddress;//考勤地址

    private String signinPointLongTitude;//考勤地址经度

    private String signinPointLaTitude;//考勤地址纬度

    private int signinValidRange;//有效考勤范围(单位米)

    private String creater;//创建人ID（userId）

    public AddCustomAttendanceReq(String signinPointId, String itemId, String signinPointName,
                                  String signinPointAddress, String signinPointLongTitude,
                                  String signinPointLaTitude, int signinValidRange, String creater) {
        this.signinPointId = signinPointId;
        this.itemId = itemId;
        this.signinPointName = signinPointName;
        this.signinPointAddress = signinPointAddress;
        this.signinPointLongTitude = signinPointLongTitude;
        this.signinPointLaTitude = signinPointLaTitude;
        this.signinValidRange = signinValidRange;
        this.creater = creater;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSigninPointName() {
        return signinPointName;
    }

    public void setSigninPointName(String signinPointName) {
        this.signinPointName = signinPointName;
    }

    public String getSigninPointAddress() {
        return signinPointAddress;
    }

    public void setSigninPointAddress(String signinPointAddress) {
        this.signinPointAddress = signinPointAddress;
    }

    public String getSigninPointLongTitude() {
        return signinPointLongTitude;
    }

    public void setSigninPointLongTitude(String signinPointLongTitude) {
        this.signinPointLongTitude = signinPointLongTitude;
    }

    public String getSigninPointLaTitude() {
        return signinPointLaTitude;
    }

    public void setSigninPointLaTitude(String signinPointLaTitude) {
        this.signinPointLaTitude = signinPointLaTitude;
    }

    public int getSigninValidRange() {
        return signinValidRange;
    }

    public void setSigninValidRange(int signinValidRange) {
        this.signinValidRange = signinValidRange;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getSigninPointId() {
        return signinPointId;
    }

    public void setSigninPointId(String signinPointId) {
        this.signinPointId = signinPointId;
    }
}
