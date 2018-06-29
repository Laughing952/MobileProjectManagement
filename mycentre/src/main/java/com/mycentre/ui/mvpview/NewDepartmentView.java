package com.mycentre.ui.mvpview;

import com.mycentre.response.DivisionalManagementRep;

/**
 * 新建/编辑部门
 * Created by Water on 2018/5/8.
 */

public interface NewDepartmentView<T> {

    void selResult(DivisionalManagementRep rep);
    void response(T t);
}
