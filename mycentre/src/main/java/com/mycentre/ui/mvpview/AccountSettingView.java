package com.mycentre.ui.mvpview;

/**
 * 账号设置
 * 作者：Laughing on 2018/5/2 13:54
 * 邮箱：719240226@qq.com
 */

public interface AccountSettingView {

    /**
     * 去下载新版app
     */
    void updateApp();

    /**
     * 显示下载提示对话框
     *
     * @param strUrl
     */
    void showDownLoadAPKDialog(String strUrl);

    /**
     * 提示不用更新
     */
    void doNotUpdate();
}
