package com.unistrong.working.ui.mvpview;

import com.unistrong.working.bean.IArrangedBean;

import java.util.List;

/**
 * 我安排的（任务）
 * 作者：Laughing on 2018/5/8 16:40
 * 邮箱：719240226@qq.com
 */

public interface IArrangedView {

    /**
     * 查看全部/查看未阅读
     *
     * @param read
     */
    void seeAllOrRead2View(String read);

    /**
     * 任务状态
     *
     * @param state
     */
    void setState2View(String state);

    /**
     * 返回我参与的任务列表
     *
     * @param arrangedList
     */
    void resultIArrangedListData(List<IArrangedBean> arrangedList);

    /**
     * 返回家在更多任务列表
     *
     * @param arrangedList
     */
    void loadMoreResult(List<IArrangedBean> arrangedList);
}
