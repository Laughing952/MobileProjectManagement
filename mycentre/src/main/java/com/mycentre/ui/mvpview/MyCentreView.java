package com.mycentre.ui.mvpview;

/**
 * 个人中心
 * Created by Water on 2018/4/27.
 */

public interface MyCentreView {

    /**
     * 退出登录的结果
     */
    void loginOutResult();

    /**
     * 打电话
     */
    void call();

    /**
     * 选择头像
     */
    void jump2PersonalInfoPage();
}
