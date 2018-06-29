package com.unistrong.working.response;

import com.waterbase.utile.DateUtil;

import java.util.Date;

/**
 * Created by edz on 2018/5/29.
 */

public class MessageEntityRep {
    private  String  feedbackId;//任务反馈表ID
    private String taskId;//任务ID
    private Date feedbackTime;//反馈时间
    private String schedule;//任务进度
    private String feedbackNote;//反馈描述
    private String ifAttachment;//是否有附件
    private Date modifyTime;//修改时间
    private Date createTime;//创建时间
    private Long modifier;//修改人
    private Long creater;//创建人
    private String feedback;
    private String messageStatus;//0创建人物1修改任务2催办任务3任务交流
    private ImageRep imageRep;



    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public ImageRep getImageRep() {
        return imageRep;
    }

    public void setImageRep(ImageRep imageRep) {
        this.imageRep = imageRep;
    }

    public String getFeedback() {
        if (feedbackTime!=null){
            return DateUtil.getStringByFormat(feedbackTime,"yyyy-MM-dd HH:mm:ss");
        }
        return "";
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getFeedbackNote() {
        return feedbackNote;
    }

    public void setFeedbackNote(String feedbackNote) {
        this.feedbackNote = feedbackNote;
    }

    public String getIfAttachment() {
        return ifAttachment;
    }

    public void setIfAttachment(String ifAttachment) {
        this.ifAttachment = ifAttachment;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }
}
