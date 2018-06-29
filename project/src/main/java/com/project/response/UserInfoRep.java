package com.project.response;

/**
 * 用户简单信息（创建项目使用）
 * 作者：Laughing on 2018/5/29 17:30
 * 邮箱：719240226@qq.com
 */

public class UserInfoRep {
    private Long userId;//用户Id
    private String username;//用户名字
    private String mobile;//用户手机号

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
