package com.project.ui.viewmodel;


import com.project.bean.PersonBean;

/**
 * 作者：Laughing on 2018/5/4 14:04
 * 邮箱：719240226@qq.com
 */

public class PersonVM {
    private String name;//
    private String id;//
    private PersonBean personBean;//
    private boolean isChecked;//

    public PersonVM(PersonBean personBean) {
        this.personBean = personBean;
    }

    public String getName() {
        return "laughing";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return "10000";
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
