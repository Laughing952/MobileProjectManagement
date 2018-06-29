package com.unistrong.working.ui.mvpview;

import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.CreateNewTask_ProjectNameRep;

/**
 * 创建通用申请
 * 作者：Laughing on 2018/5/8 20:24
 * 邮箱：719240226@qq.com
 */

public interface CreateGeneralRequestView<T> {

    /**
     * 上传图片成功返回图片信息
     */
    void resultImageUrl(T data);

    /**
     * 创建通用申请成功
     */
    void createProjectSuccess();

    /**
     * 请选择项目名
     *
     * @param rep
     */
    void setProjectNameResult(CreateNewTask_ProjectNameRep rep);

    /**
     * 选择审批人
     *
     * @param rep
     */
    void setApproverResult(CreateNewTask_PerformerRep rep);

    /**
     * 选择抄送人
     *
     * @param rep
     */
    void setCcPersonResult(CreateNewTask_PerformerRep rep);
}
