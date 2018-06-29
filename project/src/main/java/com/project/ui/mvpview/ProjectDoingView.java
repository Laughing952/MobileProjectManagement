package com.project.ui.mvpview;

/**
 * 正在执行的项目 列表
 * 作者：Laughing on 2018/5/6 11:00
 * 邮箱：719240226@qq.com
 */

public interface ProjectDoingView<T> {

    void initDataResult(T t);

    void refreshResult();

    void loadMoreResult(T t);

}
