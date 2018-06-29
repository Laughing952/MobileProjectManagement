package com.project.ui.mvpview;

/**
 * 已完成的项目
 * 作者：Laughing on 2018/5/6 11:04
 * 邮箱：719240226@qq.com
 */

public interface ProjectOverView<T> {

    void initDataResult(T t);

    void refreshResult();

    void loadMoreResult(T t);
}

