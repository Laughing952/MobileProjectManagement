package com.unistrong.working.ui.viewmodel;

import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.MessageEntityRep;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 派给我的-详情/我安排的-详情/我参与的-详情
 * 作者：Laughing on 2018/5/9 17:22
 * 邮箱：719240226@qq.com
 */

public class SentToMeDetailsVM implements Serializable {

    private String itemId;//项目ID
    private String attachId;//附件ID
    private String taskName;//任务名称
    private String taskNote;//任务描述
    private Long taskHeadId;//任务负责人（执行人）
    private Date timeLimit;//任务时限
    private String status;//任务状态
    private Date modifyTime;//修改时间
    private String taskImgUrl;//任务图片
    private List<CreateNewTask_PerformerRep> partakeList;//参与人
    private String partakeName;//参与人
    private String taskHeadName;//执行人姓名
    private String itemName;//项目ID
    private String feedbackId;//任务反馈表ID
    private Date feedbackTime;//反馈时间
    private String schedule;//任务进度
    private String feedbackNote;//反馈描述
    private String taskMessageId;//任务消息表ID
    private String content;//消息内容
    private Date createTime;//创建时间
    private Long creater;//创建人id
    private String createrName;//创建人

    private String taskId;//任务ID
    private Long modifier;//修改人Id（即userId）
    private long userId;//登陆人的ID
    private String isDel;//是否删除  0未删除 1删除


    private List<MessageEntityRep> feedbackList;//反馈消息列表
    private String messageStatus;//0创建人物1修改任务2催办任务3任务交流


    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public List<CreateNewTask_PerformerRep> getPartakeList() {
        return partakeList;
    }

    public void setPartakeList(List<CreateNewTask_PerformerRep> partakeList) {
        this.partakeList = partakeList;
    }

    public String getPartakeName() {
        return partakeName;
    }

    public void setPartakeName(String partakeName) {
        this.partakeName = partakeName;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    public Long getTaskHeadId() {
        return taskHeadId;
    }

    public void setTaskHeadId(Long taskHeadId) {
        this.taskHeadId = taskHeadId;
    }

    public Date getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Date timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getTaskImgUrl() {
        return taskImgUrl;
    }

    public void setTaskImgUrl(String taskImgUrl) {
        this.taskImgUrl = taskImgUrl;
    }

    public String getTaskHeadName() {
        return taskHeadName;
    }

    public void setTaskHeadName(String taskHeadName) {
        this.taskHeadName = taskHeadName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
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

    public String getTaskMessageId() {
        return taskMessageId;
    }

    public void setTaskMessageId(String taskMessageId) {
        this.taskMessageId = taskMessageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public List<MessageEntityRep> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<MessageEntityRep> feedbackList) {
        this.feedbackList = feedbackList;
    }

    ///////////////////////////////////是否删除///////////////////////////////////////////
    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }
}
