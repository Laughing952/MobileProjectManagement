package com.unistrong.working.request;

/**
 * Created by edz on 2018/5/31.
 */

public class AlterTaskScheduleReq {

    private String schedule;//任务进度
    private Long creater;//创建人
    private String messageStatus;//0创建人物1修改任务2催办任务3任务交流
    private String taskId;//任务id

    public AlterTaskScheduleReq(String schedule, Long creater, String messageStatus, String taskId) {
        this.schedule = schedule;
        this.creater = creater;
        this.messageStatus = messageStatus;
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }
}
