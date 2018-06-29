package com.unistrong.working.ui.mvpview;

/**
 * 我的审批
 * 作者：Laughing on 2018/5/8 16:16
 * 邮箱：719240226@qq.com
 */

public interface MyApprovalView<T> {

    /**
     * 请求我审批的数据成功后返回
     */
    void resultRequestApprovalData(T data);
}
