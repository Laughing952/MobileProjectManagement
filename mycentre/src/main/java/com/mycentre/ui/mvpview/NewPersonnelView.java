package com.mycentre.ui.mvpview;

import com.mycentre.response.DivisionalManagementRep;
import com.mycentre.response.JurisdictionRep;

import java.util.List;

/**
 * 新建/编辑人员
 * Created by Water on 2018/5/9.
 */

public interface NewPersonnelView {

    /**
     * 选择部门的结果
     *
     * @param divisionalName 部门名称
     */
    void selDivisionalResult(String divisionalName);

    /**
     * 选择职务的结果
     *
     * @param dutyName 职务名称
     */
    void selDutyResult(String dutyName);

    /**
     * 权限结果
     *
     * @param jurisdictionRepList
     */
    void selDutyJurisdictionResult(List<JurisdictionRep> jurisdictionRepList);

    /**
     * 查询后返回的名字
     *
     * @param name
     */
    void callbackName(String name);

    /**
     * 修改的成功返回
     */
    void responseUpdata();

    /**
     * 添加成功返回
     */
    void responseAdd();
}
