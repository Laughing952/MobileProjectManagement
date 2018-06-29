package com.unistrong.working.ui.mvpview;

import com.unistrong.working.response.CreateNewTask_PerformerRep;

/**
 * 通用申请-我审批的
 * 作者：Laughing on 2018/5/10 09:36
 * 邮箱：719240226@qq.com
 */

public interface GeneralRequestIApprovedDetailsView<T,Y> {

    /**
     * 返回通用申请详情信息
     */
    void resultRequestInfo(Y data);

    /**
     * 处理通用申请成功
     */
    void disposeRequestSuccess();

    /**
     * 跳转查看内容：没有内容则不跳转
     */
    void jump();

    /**
     * 上传图片成功返回图片url
     */
    void resultImageUrl(T data);

}
