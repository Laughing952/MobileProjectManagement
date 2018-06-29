package com.unistrong.working.bean;

import java.util.Date;
import java.util.List;

/**
 * 派给我的
 * 作者：Laughing on 2018/5/9 17:21
 * 邮箱：719240226@qq.com
 */

public class SentToMeBean {

    private String taskId;//任务ID
    private String taskName;//任务名称
    private Long taskHeadId;//任务负责人（执行人）
    private Date timeLimit;//任务时限
    private String schedule;//任务进度
    private String itemName;//项目名称
    private String taskHeadName;//执行人姓名

    public String getTaskHeadName() {
        return taskHeadName;
    }

    public void setTaskHeadName(String taskHeadName) {
        this.taskHeadName = taskHeadName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
