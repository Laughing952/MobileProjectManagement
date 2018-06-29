package com.unistrong.working.ui.mvpview;

import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.ui.viewmodel.SentToMeDetailsVM;

/**
 * 派给我的-详情
 * 作者：Laughing on 2018/5/8 16:16
 * 邮箱：719240226@qq.com
 */

public interface SentToMeDetailsView {
    /**
     * 发送任务动态
     */
    void sendTaskDynamic();

    /**
     * 任务参与人
     *
     * @param rep
     */
    void setJoinPeopleResult(CreateNewTask_PerformerRep rep);

    /**
     * 任务详情信息
     *
     * @param sentToMeDetailsVM
     */
    void resultWorkTaskInfo(SentToMeDetailsVM sentToMeDetailsVM);

    /**
     * 取消任务成功
     * @param response
     */
    void doCancelTaskSuccess(Object response);

    /**
     * 发送完消息返回刷新页面
     *
     */
    void refreshData();
}
