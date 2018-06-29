package com.unistrong.working.ui.mvpview;

import com.unistrong.working.bean.IParticipatedBean;
import com.unistrong.working.bean.SentToMeBean;

import java.util.List;

/**
 * 我参与的（任务）
 * 作者：Laughing on 2018/5/8 16:42
 * 邮箱：719240226@qq.com
 */

public interface IParticipatedView {
    /**
     * 查看全部/查看未阅读
     * @param read
     */
    void seeAllOrRead2View(String read);

    /**
     * 任务状态
     * @param state
     */
    void setState2View(String state);

    /**
     * 返回我安排的任务列表
     * @param repList
     */
    void resultIParticpatedListData(List<IParticipatedBean> repList);

    /**
     * 返回加载更多数据列表
     *
     * @param moreList
     */
    void loadMoreResult(List<IParticipatedBean> moreList);
}
