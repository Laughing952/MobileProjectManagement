package com.mycentre.ui.viewmodel;


import com.mycentre.response.ImageRep;

/**
 * 个人资料
 * 作者：Laughing on 2018/5/4 14:04
 * 邮箱：719240226@qq.com
 */

public class PersonInfoVM {
    private Long userId;//用户ID
    private String username;//姓名
    private String cardNo;//身份证号码
    private String mobile;//手机号码
    private String note;//个人备注
    private String sex;//性别 0：男 1：女
    private String sexShow;//显示性别 0：男 1：女
    private ImageRep imageRep;//图像

    public String getSexShow() {
        return sexShow;
    }

    public void setSexShow(String sexShow) {
        this.sexShow = sexShow;
    }

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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ImageRep getImageRep() {
        return imageRep;
    }

    public void setImageRep(ImageRep imageRep) {
        this.imageRep = imageRep;
    }
}
