package com.login.response;

/**
 * Created by Water on 2018/5/7.
 */

public class UserLoginRep {

    private String token; // 权限
    private String mobile;// 电话号码
    private String userId; // 用户唯一标志13

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
