package com.project.ui.mvpview;

import com.project.response.UserInfoRep;

/**
 * 创建项目
 * 作者：Laughing on 2018/5/3 21:06
 * 邮箱：719240226@qq.com
 */

public interface CreateProjectView {

    /**
     * 传递项目成员列表,并且接受选择的人员列表
     */
    void jumpAndGetData();

    /**
     * 登记成功后展示结果
     *
     * @param
     */
    void showResult();

    /**
     * 创建项目时自动添加用户名字到负责人
     */
    void showName(UserInfoRep userInfoRep);

}
