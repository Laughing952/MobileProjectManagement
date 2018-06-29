package com.unistrong.working.response;

import com.waterbase.widget.pickerview.model.IPickerViewData;

import java.io.Serializable;

/**
 * 创建任务-执行人 Rep
 * 作者：Laughing on 2018/5/13 15:11
 * 邮箱：719240226@qq.com
 */

public class CreateNewTask_PerformerRep implements IPickerViewData,Serializable {
    private long userId;//
    private String username;//姓名

    public CreateNewTask_PerformerRep(long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPickerViewText() {
        return username;
    }
}
