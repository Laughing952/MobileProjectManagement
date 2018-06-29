package com.project.ui.mvpview;

import com.project.response.ImageRep;
import com.project.ui.viewmodel.CreateProjectVM;
import com.project.ui.viewmodel.ProjectMemberCheckedVM;

/**
 * 项目概况-可编辑
 * 作者：Laughing on 2018/5/7 18:32
 * 邮箱：719240226@qq.com
 */

public interface ProjectSummaryView {
    /**
     * 选择图像
     */
    void chooseImage();

    /**
     * 显示图像
     */
    void showUserHeadPhoto(ImageRep response);

    /**
     * 提交编辑
     */
    void editProject();

    /**
     * 编辑提交结果
     */
    void showResult();

    /**
     * 编辑成员
     */
    void chooseProjectMember();

    /**
     * 返回项目负责人
     */
    void resultItemHead(ProjectMemberCheckedVM rep);

    /**
     * 返回编辑项目信息
     */
    void resultProjectInfo(CreateProjectVM rep);
}
