package com.mycentre.ui.presenter;

import android.view.View;

import com.mycentre.R;
import com.mycentre.request.PersonnelManagementReq;
import com.mycentre.response.DivisionalManagementRep;
import com.mycentre.response.DutyRep;
import com.mycentre.ui.activity.A_How_To_Create_Project;
import com.mycentre.ui.activity.A_How_To_Create_Task;
import com.mycentre.ui.activity.A_How_To_Punch;
import com.mycentre.ui.mvpview.NoviceGuideView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

import java.util.List;

/**
 * 新手引导
 * Created by Water on 2018/5/8.
 */

public class NoviceGuidePresenter {

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private NoviceGuideView mView;


    private PersonnelManagementReq req; // 修改或者新增的数据
    private List<DivisionalManagementRep> divisionalManagementRepList; // 部门列表
    private List<DutyRep> dutyRepList; // 职务列表

    public NoviceGuidePresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, NoviceGuideView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        req = new PersonnelManagementReq();
    }


    public void click(View v) {
        if (v.getId() == R.id.rl_novice_guide_punch) {
            // 如何签到打卡
            A_How_To_Punch.startActivity(activity);

        } else if (v.getId() == R.id.rl_novice_guide_create_program) {
            // 如何创建项目
            A_How_To_Create_Project.startActivity(activity);

        } else if (v.getId() == R.id.rl_novice_guide_create_task) {
            // 如何创建任务
            A_How_To_Create_Task.startActivity(activity);

        }
    }
}
