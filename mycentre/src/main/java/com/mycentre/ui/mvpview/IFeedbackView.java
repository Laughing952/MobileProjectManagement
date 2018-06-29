package com.mycentre.ui.mvpview;

/**
 * Created by edz on 2018/5/17.
 */

public interface IFeedbackView<T> {

    /**
     * 选择图片后显示
    * */
    void resultImageChoiced(T data);

    /**
     * 反馈意见上传成功
     * */
    void resultUploadFeedback();
}
