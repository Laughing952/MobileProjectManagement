package com.project.ui.mvpview;

import com.project.ui.viewmodel.AddMemberVM;

/**
 * 项目-创建项目-成员详情（编辑成员）
 * 作者：Laughing on 2018/5/4 17:46
 * 邮箱：719240226@qq.com
 */

public interface EditMemberView {
    /**
     * 获取AddMemberVM 对象
     *
     * @return
     */
    AddMemberVM getEditMemberVM();

    /**
     * 添加组成员成功
     */
    void editMemberSuccess();
}
