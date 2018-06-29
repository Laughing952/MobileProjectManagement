package com.project.request;

import com.project.ui.viewmodel.ProjectMemberCheckedVM;

import java.util.List;

/**
 * 作者：Laughing on 2018/5/16 09:46
 * 邮箱：719240226@qq.com
 */

public class CreateProjectReq {
    private String itemNote;//项目概况
    private String itemName;//项目名称
    private long userId;// 用户ID
    private String itemHead;//项目负责人
    private String remark;//备注
    private long creater;//项目创建人id

    private List<ProjectMemberCheckedVM> list;//项目成员

    public String getItemNote() {
        return itemNote;
    }

    public void setItemNote(String itemNote) {
        this.itemNote = itemNote;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public String getItemHead() {
        return itemHead;
    }

    public void setItemHead(String itemHead) {
        this.itemHead = itemHead;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ProjectMemberCheckedVM> getList() {
        return list;
    }

    public void setList(List<ProjectMemberCheckedVM> list) {
        this.list = list;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCreater() {
        return creater;
    }

    public void setCreater(long creater) {
        this.creater = creater;
    }
}
