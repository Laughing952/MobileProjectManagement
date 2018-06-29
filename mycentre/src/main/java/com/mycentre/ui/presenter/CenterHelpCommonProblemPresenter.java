package com.mycentre.ui.presenter;

import android.view.View;

import com.mycentre.R;
import com.mycentre.ui.activity.A_How_To_Update_Program_Progress;
import com.mycentre.ui.activity.A_Punch_Location_Fail_How_To_Do;
import com.mycentre.ui.activity.A_Upload_image_Fail;
import com.mycentre.ui.mvpview.CenterHelpCommonProblemView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

/**
 * 常见问题
 * Created by Laughing on 2018/5/31.
 */

public class CenterHelpCommonProblemPresenter {

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private CenterHelpCommonProblemView mView;

//
//    private PersonnelManagementReq req; // 修改或者新增的数据
//    private List<DivisionalManagementRep> divisionalManagementRepList; // 部门列表
//    private List<DutyRep> dutyRepList; // 职务列表

    public CenterHelpCommonProblemPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, CenterHelpCommonProblemView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
//        req = new PersonnelManagementReq();
    }


    public void click(View v) {
        if (v.getId() == R.id.rl_common_problem_location_fail) {
            // 考勤定位失败怎么办
            A_Punch_Location_Fail_How_To_Do.startActivity(activity);

        } else if (v.getId() == R.id.rl_common_problem_update_program_progress) {
            // 如何更新项目进度
            A_How_To_Update_Program_Progress.startActivity(activity);

        } else if (v.getId() == R.id.rl_common_problem_photo_upload_fail) {
            // 为什么会上传图片失败
            A_Upload_image_Fail.startActivity(activity);

        }
    }
}
