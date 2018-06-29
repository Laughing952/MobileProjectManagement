package com.project.ui.mvpview;

import com.project.ui.viewmodel.ProjectMemberCheckedVM;

import java.util.ArrayList;

/**
 * 选择项目成员（checkbox）
 * 作者：Laughing on 2018/5/7 17:00
 * 邮箱：719240226@qq.com
 */

public interface ProjectMemberCheckedView {
    /**
     * 显示数据
     *
     * @param vmList
     */
    void showData(ArrayList<ProjectMemberCheckedVM> vmList);
}
