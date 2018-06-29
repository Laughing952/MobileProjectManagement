package com.unistrong.working.ui.viewmodel;

import com.unistrong.working.response.ImageRep;

import java.util.Date;
import java.util.List;

/**
 * 新建任务/编辑任务
 * 作者：Laughing on 2018/5/8 20:27
 * 邮箱：719240226@qq.com
 */

public class CreateNewTaskVM {
    private String taskId;//任务ID

    private String itemId;//项目ID
    private String attachId;//附件ID
    private String taskName;//任务名称
    private String taskNote;//任务描述
    private long taskHeadId;//任务负责人ID
    private Date timeLimit;//任务时限（截止日期）
    public String endDate;//任务截止日期
    private String status;//任务状态（0 未开始 1 进行中 2已完成 3已超期  4待验收）
    private long creater;//创建人ID
    private long partakeId;//参与人ID
    private List<ImageRep> imgUrl;//图片

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public long getTaskHeadId() {
        return taskHeadId;
    }

    public void setTaskHeadId(long taskHeadId) {
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

    public long getCreater() {
        return creater;
    }

    public void setCreater(long creater) {
        this.creater = creater;
    }

    public long getPartakeId() {
        return partakeId;
    }

    public void setPartakeId(long partakeId) {
        this.partakeId = partakeId;
    }

    public List<ImageRep> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<ImageRep> imgUrl) {
        this.imgUrl = imgUrl;
    }
}
