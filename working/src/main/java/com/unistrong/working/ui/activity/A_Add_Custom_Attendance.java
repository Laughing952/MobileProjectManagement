package com.unistrong.working.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.global.ui.activity.TitleActivity;
import com.global.util.DateUtils;
import com.global.util.PickerViewUtil;
import com.global.util.TransformUtil;
import com.global.util.UserIdUtil;
import com.unistrong.working.R;
import com.unistrong.working.bean.SigninTime;
import com.unistrong.working.databinding.AAddCustomAttendanceBinding;
import com.unistrong.working.request.AddCustomAttendanceReq;
import com.unistrong.working.ui.mvpview.AddCustomAttendanceView;
import com.unistrong.working.ui.presenter.AddCustomAttendancePresenter;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义考勤
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Add_Custom_Attendance extends TitleActivity implements AddCustomAttendanceView {

    private AddCustomAttendancePresenter mPresenter;
    private AAddCustomAttendanceBinding mBinding;
    private Context mContext = this;
    private Dialog dialog;
    private String mLatitude;
    private String mLongitude;
    private String mAddress;
    private String mEtName;
    private String mTvAddCustomAttendanceAddress;//考勤点地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_add_custom_attendance);
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initView() {


    }

    private void initData() {
        mPresenter = new AddCustomAttendancePresenter(this, this, this);
        mBinding.setClick(v -> mPresenter.click(v));

    }

    private void initListener() {


    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Add_Custom_Attendance.class));
    }

    public static void startActivity(Context context, int flag) {
        context.startActivity(new Intent(context, A_Add_Custom_Attendance.class));
    }

    public void initTitle() {
        setTitleText("打卡设置-新建考勤点");//标题
        setRightTextViewVisibity(true);
        setRightTextViewVisibity(true);
        setRightTextViewText("确定");

    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        //判断是否有些数据为空
        judgmentAttendanceNameIsEmpty();
    }

    /**
     * 提交前判断考勤点名称是否输入了（输入后方可提交）
     *
     * @return
     */
    private void judgmentAttendanceNameIsEmpty() {
        //考勤点名称
        mEtName = mBinding.etSearchProject.getText().toString().trim();
        //考勤点名称
        mTvAddCustomAttendanceAddress = mBinding.tvAddCustomAttendanceAddress.getText().toString().trim();
        if (StrUtil.isEmpty(mEtName) || StrUtil.isEmpty(mTvAddCustomAttendanceAddress)) {
            ToastUtil.showToast(mContext, "考勤点名称和考勤点都不能为空");
        } else {
            collectionData();

        }
    }

    /**
     * 收集数据（准备上传数据）
     */
    private void collectionData() {
        List<AddCustomAttendanceReq> list = new ArrayList<>();
        String tvAddCustomAttendanceRange = mBinding.tvAddCustomAttendanceRange.toString().trim();//考勤范围

        int range = TransformUtil.transform(tvAddCustomAttendanceRange);//考勤范围 300米，500米，1000米，2000米


        List<SigninTime> list2 = new ArrayList<>();
        //是否休息 1:休息 0:不休息
//        String i = (mBinding.cbPunchSetting1.isChecked() ? 1 : 0) + "";//星期是否选中
        list2.add(new SigninTime("", "0", mBinding.tvAddCustomAttendanceTime1.getText().toString(),
                mBinding.tvAddCustomAttendanceTime12.getText().toString(),
                (mBinding.cbPunchSetting1.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime("", "1", mBinding.tvAddCustomAttendanceTime2.getText().toString(),
                mBinding.tvAddCustomAttendanceTime22.getText().toString(),
                (mBinding.cbPunchSetting2.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime("", "2", mBinding.tvAddCustomAttendanceTime3.getText().toString(),
                mBinding.tvAddCustomAttendanceTime32.getText().toString(),
                (mBinding.cbPunchSetting3.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime("", "3", mBinding.tvAddCustomAttendanceTime4.getText().toString(),
                mBinding.tvAddCustomAttendanceTime42.getText().toString(),
                (mBinding.cbPunchSetting4.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime("", "4", mBinding.tvAddCustomAttendanceTime5.getText().toString(),
                mBinding.tvAddCustomAttendanceTime52.getText().toString(),
                (mBinding.cbPunchSetting5.isChecked() ? 0 : 1) + ""));

        list2.add(new SigninTime("", "5", mBinding.tvAddCustomAttendanceTime6.getText().toString(),
                mBinding.tvAddCustomAttendanceTime62.getText().toString(),
                (mBinding.cbPunchSetting6.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime("", "6", mBinding.tvAddCustomAttendanceTime7.getText().toString(),
                mBinding.tvAddCustomAttendanceTime72.getText().toString(),
                (mBinding.cbPunchSetting7.isChecked() ? 0 : 1) + ""));
        // TODO: 2018/5/25  userId需要传到服务器
        AddCustomAttendanceReq addCustomAttendanceReq = new AddCustomAttendanceReq("", "",
                mEtName, mTvAddCustomAttendanceAddress, mLongitude, mLatitude,
                range, UserIdUtil.getUserIdLong(), list2);
        mPresenter.uploadData(addCustomAttendanceReq);//上传数据
    }

    @Override
    public void chooseCommuteTime(int week) {
        showDialog1(week);
    }

    @Override
    public void setRange() {
        showDialog2();
    }

    /**
     * 从地图页面获取考勤地点的名字与经纬度
     */
    @Override
    public void jumpAndGetData() {
        Intent intent = null;
        try {
            intent = new Intent(mContext, Class.forName("com.unistrong.baidumaplibrary.ui.activity.A_Attendance_Location"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        startActivityForResult(intent, 0x456);

    }

    @Override
    public void uploadDataSuccess() {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x456 && resultCode == 0x457) {
            if (data != null) {
                mLatitude = data.getStringExtra("latitude");
                mLongitude = data.getStringExtra("longitude");
                mAddress = data.getStringExtra("address");
                mBinding.tvAddCustomAttendanceAddress.setText(mAddress);       //考勤点名称
//                LogUtil.e("TAG", "laughing--------------mLatitude-------->   " + mLatitude);
//                LogUtil.e("TAG", "laughing--------------mLongitude-------->   " + mLongitude);
//                LogUtil.e("TAG", "laughing--------------mAddress-------->   " + mAddress);
            }
        }

    }

    /**
     * 选择考勤时间设置是上班时间还是下班时间
     */
    public void showDialog1(int week) {
        View localView = LayoutInflater.from(this).inflate(
                R.layout.dialog_feedback_progress, null);
        TextView priority = (TextView) localView.findViewById(R.id.tv_set_priority);
        priority.setText("上班时间");
        TextView progress = (TextView) localView.findViewById(R.id.tv_feedback_progress);
        progress.setText("下班时间");
        TextView tv_cancel = (TextView) localView.findViewById(R.id.tv_cancel);
        dialog = new Dialog(mContext, com.global.R.style.custom_dialog);
        DateUtils.initDialog(dialog, localView, getWindowManager());
        dialog.show();
        tv_cancel.setOnClickListener(arg0 -> {
            //取消按钮
            dialog.dismiss();
        });

        priority.setOnClickListener(v -> {

            // 上班时间  参数二 0：代表上班时间设置
            chooseTimeOk(week, 0);
            dialog.dismiss();
        });

        progress.setOnClickListener(v -> {
            // 下班时间  参数二 1：代表下班时间设置
            chooseTimeOk(week, 1);
            dialog.dismiss();


        });
    }

    /**
     * 时间选择好后放置到控件中对应星期中（周一，到周日）与对应时间（上班时间或下班时间）
     *
     * @param week 星期几
     * @param i    上班时间或下班时间  0：代表上班时间  1：代表下班时间
     */
    public void chooseTimeOk(int week, int i) {
        PickerViewUtil.showSelectTime(this, new PickerViewUtil.DateSelectListener() {
            @Override
            public void result(String dateStr) {
                setDate2TextView(week, i, dateStr);
            }

        }, "请选择时间");

    }

    /**
     * @param week    星期几
     * @param i       上班时间或下班时间  0：代表上班时间  1：代表下班时间
     * @param dateStr 时间字符串
     */
    private void setDate2TextView(int week, int i, String dateStr) {

        switch (week) {
            case 1:
                viewSetData(i, dateStr, mBinding.cbPunchSetting1, mBinding.tvAddCustomAttendanceTime1, mBinding.tvAddCustomAttendanceTime12);

                break;
            case 2:
                viewSetData(i, dateStr, mBinding.cbPunchSetting2, mBinding.tvAddCustomAttendanceTime2, mBinding.tvAddCustomAttendanceTime22);

                break;
            case 3:
                viewSetData(i, dateStr, mBinding.cbPunchSetting3, mBinding.tvAddCustomAttendanceTime3, mBinding.tvAddCustomAttendanceTime32);

                break;
            case 4:
                viewSetData(i, dateStr, mBinding.cbPunchSetting4, mBinding.tvAddCustomAttendanceTime4, mBinding.tvAddCustomAttendanceTime42);

                break;
            case 5:
                viewSetData(i, dateStr, mBinding.cbPunchSetting5, mBinding.tvAddCustomAttendanceTime5, mBinding.tvAddCustomAttendanceTime52);

                break;
            case 6:
                viewSetData(i, dateStr, mBinding.cbPunchSetting6, mBinding.tvAddCustomAttendanceTime6, mBinding.tvAddCustomAttendanceTime62);

                break;
            case 7:
                viewSetData(i, dateStr, mBinding.cbPunchSetting7, mBinding.tvAddCustomAttendanceTime7, mBinding.tvAddCustomAttendanceTime72);

                break;

            default:

                break;
        }

    }

    /**
     * 控制选择打卡时间方法(只要用户去选择打卡时间，就会帮用户选中打卡星期)
     *
     * @param i       上班时间或下班时间  0：代表上班时间  1：代表下班时间
     * @param dateStr 时间字符串
     * @param box     要改变的CheckBox
     * @param tv1     上班的时间
     * @param tv2     下班的时间
     */
    private void viewSetData(int i, String dateStr, CheckBox box, TextView tv1, TextView tv2) {
        if (box.isChecked()) {
            if (i == 0) {
                tv1.setText(dateStr);
            } else if (i == 1) {
                tv2.setText(dateStr);
            }
        } else {
            //这里帮用户勾选星期
            box.setChecked(true);
            //同时把时间填到文本区
            if (i == 0) {
                tv1.setText(dateStr);
            } else if (i == 1) {
                tv2.setText(dateStr);
            }
        }
    }


    /**
     * 选择有效考勤范围
     */
    public void showDialog2() {
        View localView = LayoutInflater.from(this).inflate(
                R.layout.dialog_bottom_i_arranged, null);
        TextView tv_task_participant = (TextView) localView.findViewById(R.id.tv_task_participant);
        tv_task_participant.setText("300米");
        TextView tv_urge_task = (TextView) localView.findViewById(R.id.tv_urge_task);
        tv_urge_task.setText("500米");
        TextView tv_edit_task = (TextView) localView.findViewById(R.id.tv_edit_task);
        tv_edit_task.setText("1000米");
        TextView tv_cancel_task = (TextView) localView.findViewById(R.id.tv_cancel_task);
        tv_cancel_task.setText("2000米");
        TextView tv_cancel = (TextView) localView.findViewById(R.id.tv_cancel);
        dialog = new Dialog(mContext, com.global.R.style.custom_dialog);
        DateUtils.initDialog(dialog, localView, getWindowManager());
        dialog.show();
        tv_cancel.setOnClickListener(arg0 -> {
            //取消按钮
            dialog.dismiss();
        });

        tv_task_participant.setOnClickListener(v -> {
            // 300米
            mBinding.tvAddCustomAttendanceRange.setText("300米");
            dialog.dismiss();
        });
        tv_urge_task.setOnClickListener(v -> {
            //500米
            mBinding.tvAddCustomAttendanceRange.setText("500米");
            dialog.dismiss();

        });
        tv_edit_task.setOnClickListener(v -> {
            // 1000米
            mBinding.tvAddCustomAttendanceRange.setText("1000米");
            dialog.dismiss();

        });

        tv_cancel_task.setOnClickListener(v -> {
            dialog.dismiss();
            // 2000米
            mBinding.tvAddCustomAttendanceRange.setText("2000米");

        });
    }

}
