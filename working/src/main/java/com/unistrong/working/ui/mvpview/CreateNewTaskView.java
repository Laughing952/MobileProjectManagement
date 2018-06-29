package com.unistrong.working.ui.mvpview;

import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.CreateNewTask_ProjectNameRep;
import com.unistrong.working.response.ImageRep;

/**
 * 创建/编辑 通用申请
 * 作者：Laughing on 2018/5/8 20:24
 * 邮箱：719240226@qq.com
 */

public interface CreateNewTaskView {

    /**
     * 新建任务成功
     */
    void createProjectSuccess();

    /**
     * 新建任务-选择项目名
     */
    void setProjectNameResult(CreateNewTask_ProjectNameRep rep);

    /**
     * 打开任务描述页面等待回传结果
     */
    void startTaskDescribe();

    /**
     * 选择执行人
     *
     * @param rep
     */
    void setPerformerResult(CreateNewTask_PerformerRep rep);

    /**
     * 请选截止日期
     */
    void selectEndDate();

    /**
     * 返回图片信息
     */
    void resultImageUrl(ImageRep imageRep);
}
