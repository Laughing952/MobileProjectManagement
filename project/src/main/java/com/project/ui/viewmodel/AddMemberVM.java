package com.project.ui.viewmodel;


import com.project.bean.ProjectGroup;

/**
 * 添加成员
 * 作者：Laughing on 2018/5/4 16:42
 * 邮箱：719240226@qq.com
 */

public class AddMemberVM {
    private String g_name;//姓名
    private String g_phone_num;//手机号
    private ProjectGroup g_group;//分组
    private String g_other;//备注

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_phone_num() {
        return g_phone_num;
    }

    public void setG_phone_num(String g_phone_num) {
        this.g_phone_num = g_phone_num;
    }

    public ProjectGroup getG_group() {
        return g_group;
    }

    public void setG_group(ProjectGroup g_group) {
        this.g_group = g_group;
    }

    public String getG_other() {
        return g_other;
    }

    public void setG_other(String g_other) {
        this.g_other = g_other;
    }
}
