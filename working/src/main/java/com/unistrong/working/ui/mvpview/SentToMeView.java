package com.unistrong.working.ui.mvpview;

import com.global.viewmodel.TaskPriorityViewModel;
import com.unistrong.working.bean.SentToMeBean;

import java.util.List;

/**
 * 我的审批
 * 作者：Laughing on 2018/5/8 16:16
 * 邮箱：719240226@qq.com
 */

public interface SentToMeView {
    /**
     * 任务优先级
     *
     * @param priorityViewModel
     */
    void setPriority2View(TaskPriorityViewModel priorityViewModel);

    /**
     * 任务状态
     *
     * @param priority
     */
    void setState2View(String priority);

    /**
     * 返回派给我的列表
     *
     * @param sentToMeList
     */
    void resultSendToMeListData(List<SentToMeBean> sentToMeList);

    /**
     * 返回加载更多数据列表
     *
     * @param moreList
     */
    void loadMoreResult(List<SentToMeBean> moreList);
}
