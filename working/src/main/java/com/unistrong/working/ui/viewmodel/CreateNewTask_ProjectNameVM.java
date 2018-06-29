package com.unistrong.working.ui.viewmodel;

import com.unistrong.working.response.CreateNewTask_ProjectNameRep;

/**
 * 创建任务-项目名称
 * 作者：Laughing on 2018/5/9 17:22
 * 邮箱：719240226@qq.com
 */

public class CreateNewTask_ProjectNameVM {
    private String id;//项目Id
    private String p_name;//项目名

    private CreateNewTask_ProjectNameRep mCreateNewTask_projectNameRep;//

    public CreateNewTask_ProjectNameVM(CreateNewTask_ProjectNameRep mCreateNewTask_projectNameRep) {
        this.mCreateNewTask_projectNameRep = mCreateNewTask_projectNameRep;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getP_name() {
        return mCreateNewTask_projectNameRep.getItemname();
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }
}
