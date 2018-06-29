package com.unistrong.working.ui.viewmodel;

import com.global.util.TransformUtil;
import com.unistrong.working.response.ImageRep;
import com.waterbase.utile.DateUtil;

import java.util.Date;

/**
 * Created by cuihao on 2018/5/22.
 */

public class ApprovalHistoryVM {

    private String deliverCkUser;//被转送人
    private String username;//转送人
    private Date deliverTime;//转送时间
    private String deliverContent;//转送意见
    private ImageRep imageRep;//头像
    private String date;//日期
    private String time;//时间

    public String getDeliverCkUser() {
        return deliverCkUser;
    }

    public void setDeliverCkUser(String deliverCkUser) {
        this.deliverCkUser = deliverCkUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getDeliverContent() {
        return "意见："+deliverContent;
    }

    public void setDeliverContent(String deliverContent) {
        this.deliverContent = deliverContent;
    }

    public ImageRep getImageRep() {
        return imageRep;
    }

    public void setImageRep(ImageRep imageRep) {
        this.imageRep = imageRep;
    }

    public String getDate() {
        return DateUtil.getStringByFormat(deliverTime,"MM月dd");
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return DateUtil.getStringByFormat(deliverTime,"HH:mm");
    }

    public void setTime(String time) {
        this.time = time;
    }
}
