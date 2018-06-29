package com.unistrong.working.ui.mvpview;

import com.unistrong.working.ui.viewmodel.JoinPeopleVM;

import java.util.List;

/**
 * 参与人列表
 * 作者：Laughing on 2018/5/14 16:41
 * 邮箱：719240226@qq.com
 */

public interface JoinPeopleView {

    void showData(List<JoinPeopleVM> vmList);

}
