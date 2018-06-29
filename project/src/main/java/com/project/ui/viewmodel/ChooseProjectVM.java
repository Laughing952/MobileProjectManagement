package com.project.ui.viewmodel;


import com.project.bean.ChooseProjectBean;

/**
 * 选择项目
 * 作者：Laughing on 2018/5/4 14:04
 * 邮箱：719240226@qq.com
 */

public class ChooseProjectVM {
    private String name;//
    private String id;//
    private ChooseProjectBean chooseProjectBean;//

    public ChooseProjectVM(ChooseProjectBean chooseProjectBean) {
        this.chooseProjectBean = chooseProjectBean;
    }

    public String getName() {
        return "IT软件项目";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
