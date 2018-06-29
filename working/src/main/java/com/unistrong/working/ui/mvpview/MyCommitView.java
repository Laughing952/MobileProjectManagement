package com.unistrong.working.ui.mvpview;

/**
 * 我提交的
 * 作者：Laughing on 2018/5/8 16:40
 * 邮箱：719240226@qq.com
 */

public interface MyCommitView<T> {

    /**
     * 请求我提交的数据成功后返回
     */
    void resultRequestApprovalData(T data);
}
