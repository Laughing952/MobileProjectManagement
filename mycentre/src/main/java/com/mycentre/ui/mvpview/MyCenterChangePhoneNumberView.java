package com.mycentre.ui.mvpview;

/**
 * 修改手机号
 * 作者：Laughing on 2018/5/23 17:23
 * 邮箱：719240226@qq.com
 */

public interface MyCenterChangePhoneNumberView {
    /**
     * 效验手机号
     */
    void doVerification();

    /**
     * 手机号修改成功，关闭页面
     */
    void finishPage();
}
