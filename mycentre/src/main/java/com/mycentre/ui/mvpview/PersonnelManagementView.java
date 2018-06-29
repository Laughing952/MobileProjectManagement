package com.mycentre.ui.mvpview;

import com.mycentre.response.PersonnelManagementRep;

/**
 * 人员管理
 * Created by Water on 2018/5/8.
 */

public interface PersonnelManagementView<T> {

    void response(T t);

    void delSucceed(PersonnelManagementRep rep);
}
