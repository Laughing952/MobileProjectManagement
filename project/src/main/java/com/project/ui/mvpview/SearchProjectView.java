package com.project.ui.mvpview;

import com.project.response.ProjectDoingRep;

import java.util.List;

/**
 * 作者：Laughing on 2018/5/4 16:07
 * 邮箱：719240226@qq.com
 */

public interface SearchProjectView {

    /**
     * 初始化返回的数据
     *
     * @param involvedInfoRep
     */
    void initDataResult(List<ProjectDoingRep> involvedInfoRep);

    /**
     * 获取搜索框文本内容
     */
    void getBoxText();

    /**
     * 加载更多数据
     *
     * @param involvedInfoRep
     */
    void loadMoreResult(List<ProjectDoingRep> involvedInfoRep);

    /**
     * 返回刷新数据
     *
     */
    void refreshResult();
}
