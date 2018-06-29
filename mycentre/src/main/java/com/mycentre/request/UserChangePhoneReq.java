package com.mycentre.request;

/**
 * 作者：Laughing on 2018/5/23 17:20
 * 邮箱：719240226@qq.com
 */

public class UserChangePhoneReq {
    private String userId;
    private String oldMobile;
    private String mobile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldMobile() {
        return oldMobile;
    }

    public void setOldMobile(String oldMobile) {
        this.oldMobile = oldMobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
