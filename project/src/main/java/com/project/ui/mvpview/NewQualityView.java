package com.project.ui.mvpview;

import com.project.response.ImageRep;

/**
 * 新建/编辑质量检查
 * Created by Water on 2018/5/10.
 */

public interface NewQualityView {

    /**
     * 上传图片成功的返回
     *
     * @param imageRep
     */
    void responseImage(ImageRep imageRep);

    /**
     * 检查类型
     *
     * @param content
     */
    void showType(String content);

    /**
     * 检查日期
     *
     * @param content
     */
    void showDate(String content);

    /**
     * 检查项
     *
     * @param content
     */
    void showItems(String content);

    /**
     * 检查结果
     *
     * @param content
     */
    void showResultInfo(String content);

    /**
     * 检查结果是否通过
     *
     * @param is
     */
    void showResult(boolean is);

}
