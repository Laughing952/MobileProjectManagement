package com.login.request;

import java.io.Serializable;

/**
 * 注册
 * Created by Water on 2018/5/7.
 */

public class UserRegisterReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mobile;
    private String password;
    private String verifyCode;
    private String oldPassword;//旧密码（如果为空，即为注册，否则为）

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;

    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
