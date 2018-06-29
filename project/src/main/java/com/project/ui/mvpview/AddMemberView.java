package com.project.ui.mvpview;

import com.project.ui.viewmodel.AddMemberVM;

/**
 * 项目-创建项目-添加成员
 * 作者：Laughing on 2018/5/3 22:40
 * 邮箱：719240226@qq.com
 */

public interface AddMemberView {
    /**
     * 获取AddMemberVM 对象
     *
     * @return
     */
    AddMemberVM getAddMemberVM();

    /**
     * 添加组成员成功
     */
    void addMemberSuccess();

}
