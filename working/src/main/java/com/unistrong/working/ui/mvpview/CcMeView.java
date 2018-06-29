package com.unistrong.working.ui.mvpview;

/**
 * 抄送我的
 * 作者：Laughing on 2018/5/8 16:42
 * 邮箱：719240226@qq.com
 */

public interface CcMeView<T> {

    /**
     * 请求抄送我的数据成功后返回
     */
    void resultRequestApprovalData(T data);
}
