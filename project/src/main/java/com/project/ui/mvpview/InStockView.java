package com.project.ui.mvpview;

import com.project.response.MaterialsRep;
import com.project.ui.viewmodel.ClassifyeVM;

import java.util.List;

/**
 * 库存
 * 作者：Laughing on 2018/5/21 15:07
 * 邮箱：719240226@qq.com
 */

public interface InStockView {

    /**
     * 分类数据
     * @param materialsRepList
     */
    void initClassifyList(List<ClassifyeVM> materialsRepList);

    /**
     * 物资数据
     * @param materialsRepList
     */
    void initMaterialsList(List<MaterialsRep> materialsRepList);

    /**
     * 搜索框中的文字
     * @return
     */
    String getSearchContent();

    /**
     * 是否正在展示库存数据
     *
     * @param isShowInStock true 正在展示库存数据 false正在展示全部数据
     */
    void showInStock(boolean isShowInStock);

    /**
     * 取消搜索
     */
    void searchCancel();

    void initCarNum(String size);
}
