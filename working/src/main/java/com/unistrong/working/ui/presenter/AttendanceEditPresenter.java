package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.request.AddCustomAttendanceReq;
import com.unistrong.working.response.AttendanceSettingRep;
import com.unistrong.working.ui.mvpview.AttendanceEditView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;

/**
 * 编辑考勤点
 * 作者：Laughing on 2018/5/22 15:42
 * 邮箱：719240226@qq.com
 */

public class AttendanceEditPresenter {
    private AttendanceEditView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private HttpManager mHttpManager;

    public AttendanceEditPresenter(AttendanceEditView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        mHttpManager = new HttpManager(activity, lifecycleProvider);
    }

    public void click(View view) {
        if (view.getId() == R.id.iv_attendance_edit1) {
            //星期一的时间
            choosePunchTime(1);
        } else if (view.getId() == R.id.iv_attendance_edit2) {
            //星期二的时间
            choosePunchTime(2);


        } else if (view.getId() == R.id.iv_attendance_edit3) {
            //星期三的时间
            choosePunchTime(3);


        } else if (view.getId() == R.id.iv_attendance_edit4) {
            //星期四的时间
            choosePunchTime(4);


        } else if (view.getId() == R.id.iv_attendance_edit5) {
            //星期五的时间
            choosePunchTime(5);


        } else if (view.getId() == R.id.iv_attendance_edit6) {
            //星期六的时间
            choosePunchTime(6);


        } else if (view.getId() == R.id.iv_attendance_edit7) {
            //星期日的时间
            choosePunchTime(7);


        } else if (view.getId() == R.id.rl_attendance_edit_address) {
            //考勤地点
            mView.jumpAndGetData();

        } else if (view.getId() == R.id.rl_attendance_edit_range) {
            //有效考勤范围
            mView.setRange();

        } else if (view.getId() == R.id.iv_attendance_edit_delete) {
            //删除考勤点
            mView.deleteAttendancePoint();

        }
    }

    /**
     * @param week 星期几 传入 1到7 分别对应周一到周天
     */
    private void choosePunchTime(int week) {

        mView.chooseCommuteTime(week);//选择上下班时间

    }

    /**
     * 加载点击的考勤item的详情信息
     *
     * @param mId
     */
    public void downLoadData(String mId) {

        mHttpManager.doHttpDeal(RetrofitHelper.getApiService().downloadAttendanceData(mId),
                new DefaultObserver<AddCustomAttendanceReq>() {
                    @Override
                    public void onSuccess(AddCustomAttendanceReq response) {
                        //进入编辑页面发送网络请求考勤点信息
                        mView.backData(response);
                    }
                });
    }

    /**
     * 删除考勤点
     *
     * @param attendanceSettingRep
     */
    public void deleteAttendancePointRequest(AttendanceSettingRep attendanceSettingRep) {
        mHttpManager.doHttpDeal(RetrofitHelper.getApiService().deleteAttendancePoint(attendanceSettingRep),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.deleteAttendancePointResult();
                    }
                });


    }

    /**
     * 保存编辑
     */
    public void saveAttendancePointDetails(AddCustomAttendanceReq addCustomAttendanceReq) {
        mHttpManager.doHttpDeal(RetrofitHelper.getApiService().uploadEditAttendanceDetails(addCustomAttendanceReq),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        ToastUtil.showToast(activity, "编辑成功");
                        mView.editAttendancePointResult();
                    }
                });
    }
}
