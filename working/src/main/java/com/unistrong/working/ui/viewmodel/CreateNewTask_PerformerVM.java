package com.unistrong.working.ui.viewmodel;

import com.unistrong.working.response.CreateNewTask_PerformerRep;

/**
 * 创建任务-执行人
 * 作者：Laughing on 2018/5/13 19:22
 * 邮箱：719240226@qq.com
 */

public class CreateNewTask_PerformerVM {
    private String id;//
    private String name;//姓名

    private CreateNewTask_PerformerRep mCreateNewTask_performerRep;//

    public CreateNewTask_PerformerVM(CreateNewTask_PerformerRep mCreateNewTask_performerRep) {
        this.mCreateNewTask_performerRep = mCreateNewTask_performerRep;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return mCreateNewTask_performerRep.getUsername();
    }

    public void setName(String name) {
        this.name = name;
    }
}
