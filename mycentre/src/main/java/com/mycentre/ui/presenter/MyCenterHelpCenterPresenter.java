package com.mycentre.ui.presenter;

import android.view.View;

import com.mycentre.R;
import com.mycentre.request.PersonnelManagementReq;
import com.mycentre.response.DivisionalManagementRep;
import com.mycentre.response.DutyRep;
import com.mycentre.ui.activity.A_Center_Help_Common_Problem;
import com.mycentre.ui.activity.A_Center_Help_Function_Description;
import com.mycentre.ui.activity.A_Novice_Guide;
import com.mycentre.ui.mvpview.MyCenterHelpCenterView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

import java.util.List;

/**
 * 帮助中心
 * Created by Water on 2018/5/8.
 */

public class MyCenterHelpCenterPresenter {

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private MyCenterHelpCenterView mView;


    private PersonnelManagementReq req; // 修改或者新增的数据
    private List<DivisionalManagementRep> divisionalManagementRepList; // 部门列表
    private List<DutyRep> dutyRepList; // 职务列表

    public MyCenterHelpCenterPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, MyCenterHelpCenterView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        req = new PersonnelManagementReq();
    }


    public void click(View v) {
        if (v.getId() == R.id.rl_personal_center_help_center_guide) {
            // 新手引导
            A_Novice_Guide.startActivity(activity);
        } else if (v.getId() == R.id.rl_personal_center_help_center_common_problem) {
            // 常见问题
            A_Center_Help_Common_Problem.startActivity(activity);
        } else if (v.getId() == R.id.rl_personal_center_help_center_function_description) {
            // 功能说明
            A_Center_Help_Function_Description.startActivity(activity);
        }
    }
}
