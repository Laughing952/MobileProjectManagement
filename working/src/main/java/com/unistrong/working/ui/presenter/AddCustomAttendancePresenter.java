package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.request.AddCustomAttendanceReq;
import com.unistrong.working.ui.mvpview.AddCustomAttendanceView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;

/**
 * 自定义考勤
 * 作者：Laughing on 2018/5/22 15:42
 * 邮箱：719240226@qq.com
 */

public class AddCustomAttendancePresenter {
    private AddCustomAttendanceView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public AddCustomAttendancePresenter(AddCustomAttendanceView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
        if (view.getId() == R.id.iv_add_custom_attendance1) {
            //星期一的时间
            choosePunchTime(1);

        } else if (view.getId() == R.id.iv_add_custom_attendance2) {
            //星期二的时间
            choosePunchTime(2);


        } else if (view.getId() == R.id.iv_add_custom_attendance3) {
            //星期三的时间
            choosePunchTime(3);


        } else if (view.getId() == R.id.iv_add_custom_attendance4) {
            //星期四的时间
            choosePunchTime(4);


        } else if (view.getId() == R.id.iv_add_custom_attendance5) {
            //星期五的时间
            choosePunchTime(5);


        } else if (view.getId() == R.id.iv_add_custom_attendance6) {
            //星期六的时间
            choosePunchTime(6);


        } else if (view.getId() == R.id.iv_add_custom_attendance7) {
            //星期日的时间
            choosePunchTime(7);


        } else if (view.getId() == R.id.rl_add_custom_attendance_address) {
            //考勤地点

            mView.jumpAndGetData();
        } else if (view.getId() == R.id.rl_add_custom_attendance_range) {
            //有效考勤范围
            mView.setRange();

        }
    }

    /**
     * @param week 星期几 传入 1到7 分别对应周一到周天
     */
    private void choosePunchTime(int week) {

        mView.chooseCommuteTime(week);//选择上下班时间

    }

    /**
     * 新考勤点数据
     *
     * @param addCustomAttendanceReq
     */
    public void uploadData(AddCustomAttendanceReq addCustomAttendanceReq) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadCustomAttendanceData(addCustomAttendanceReq),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        ToastUtil.showToast(activity, "新考勤点成功");
                       mView.uploadDataSuccess();
                    }
                });
    }



}
