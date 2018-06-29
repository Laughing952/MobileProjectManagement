package com.mycentre.ui.mvpview;

import com.mycentre.response.DivisionalManagementRep;

/**
 * 只有一个返回的处理
 * Created by Water on 2018/5/8.
 */

public interface DivisionalManagementView<T> {

    void callBack(T t);

    void delSuccss(DivisionalManagementRep rep);

}
