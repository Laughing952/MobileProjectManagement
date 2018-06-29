package com.unistrong.mobileprojectmanagement.ui.mvpview;

import android.support.v4.app.Fragment;

/**
 * Created by Water on 2018/3/28.
 */

public interface HomeView {
    /**
     * 选中的fragment
     * @param fragment
     */
    void selectFragment(Fragment fragment);

    /**
     * 添加tab
     * @param res
     * @param text
     */
    void addTabView(int res, String text);

    /**
     * 改变tab
     * @param position
     * @param res
     * @param color
     */
    void alterTabState(int position, int res, int color);
}
