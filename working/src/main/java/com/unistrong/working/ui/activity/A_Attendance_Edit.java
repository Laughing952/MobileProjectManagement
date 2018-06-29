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
import com.global.util.UserIdUtil;
import com.global.util.PickerViewUtil;
import com.global.util.TransformUtil;
import com.unistrong.working.R;
import com.unistrong.working.bean.SigninTime;
import com.unistrong.working.databinding.AAttendanceEditBinding;
import com.unistrong.working.request.AddCustomAttendanceReq;
import com.unistrong.working.response.AttendanceSettingRep;
import com.unistrong.working.ui.mvpview.AttendanceEditView;
import com.unistrong.working.ui.presenter.AttendanceEditPresenter;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 编辑考勤点
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Attendance_Edit extends TitleActivity implements AttendanceEditView {

    private AttendanceEditPresenter mPresenter;
    private AAttendanceEditBinding mBinding;
    private Context mContext = this;
    private Dialog dialog;
    private String mLatitude;
    private String mLongitude;
    private String mAddress;
    private static String sAttendancePointId;//考勤点Id（根据id进行删除、修改）
    public static final String Item_Attendance_Setting = "Item_Attendance_Setting";
    private static AttendanceSettingRep sAttendanceSettingRep;
    private String mSigninPointName;// 考勤点名称
    private String mTvAddCustomAttendanceAddress;//考勤点地址
    private AttendanceSettingRep mAttendanceSettingRep;
    private Map<Integer, String> mIds;
    private String mItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_attendance_edit);
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initView() {
        mSigninPointName = sAttendanceSettingRep.getSigninPointName();
        mBinding.tvAttendanceEditAttendanceName.setText(mSigninPointName);//考勤点名称

    }

    private void initData() {

        mPresenter = new AttendanceEditPresenter(this, this, this);
        mBinding.setClick(v -> mPresenter.click(v));
        mPresenter.downLoadData(sAttendancePointId);//加载其他数据用来展示，以供修改

    }

    private void initListener() {


    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(new Intent(context, A_Attendance_Edit.class));
        sAttendanceSettingRep = (AttendanceSettingRep) intent.getSerializableExtra(Item_Attendance_Setting);
        sAttendancePointId = sAttendanceSettingRep.getSigninPointId();
    }


    public void initTitle() {
        setTitleText("编辑考勤点");//标题
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
        mTvAddCustomAttendanceAddress = mBinding.tvAttendanceEditAddress.getText().toString().trim();
        if (StrUtil.isEmpty(mSigninPointName) || StrUtil.isEmpty(mTvAddCustomAttendanceAddress)) {
            ToastUtil.showToast(mContext, "考勤点名称和考勤点都不能为空");
        } else {
            collectionData(); //保存编辑

        }
    }

    /**
     * 收集数据（准备上传数据）
     */
    private void collectionData() {
        String tvAddCustomAttendanceRange = mBinding.tvAttendanceEditRange.getText().toString().trim();//考勤范围

        int range = TransformUtil.transform(tvAddCustomAttendanceRange);//考勤范围 300米，500米，1000米，2000米


        List<SigninTime> list2 = new ArrayList<>();
        //是否休息 1:休息 0:不休息
//        String i = (mBinding.cbPunchSetting1.isChecked() ? 1 : 0) + "";//星期是否选中
        list2.add(new SigninTime(mIds.get(0), "0", mBinding.tvAttendanceEditTime1.getText().toString(),
                mBinding.tvAttendanceEditTime12.getText().toString(),
                (mBinding.cbPunchSetting1.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime(mIds.get(1), "1", mBinding.tvAttendanceEditTime2.getText().toString(),
                mBinding.tvAttendanceEditTime22.getText().toString(),
                (mBinding.cbPunchSetting2.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime(mIds.get(2), "2", mBinding.tvAttendanceEditTime3.getText().toString(),
                mBinding.tvAttendanceEditTime32.getText().toString(),
                (mBinding.cbPunchSetting3.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime(mIds.get(3), "3", mBinding.tvAttendanceEditTime4.getText().toString(),
                mBinding.tvAttendanceEditTime42.getText().toString(),
                (mBinding.cbPunchSetting4.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime(mIds.get(4), "4", mBinding.tvAttendanceEditTime5.getText().toString(),
                mBinding.tvAttendanceEditTime52.getText().toString(),
                (mBinding.cbPunchSetting5.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime(mIds.get(5), "5", mBinding.tvAttendanceEditTime6.getText().toString(),
                mBinding.tvAttendanceEditTime62.getText().toString(),
                (mBinding.cbPunchSetting6.isChecked() ? 0 : 1) + ""));
        list2.add(new SigninTime(mIds.get(6), "6", mBinding.tvAttendanceEditTime7.getText().toString(),
                mBinding.tvAttendanceEditTime72.getText().toString(),
                (mBinding.cbPunchSetting7.isChecked() ? 0 : 1) + ""));

        PreferencesManager preferencesManager = PreferencesManager.getInstance(BaseApplication.getAppContext());
        String userId = preferencesManager.get("userId", "");// 获取userId
//        preferencesManager.put("token", loginRep.getToken());
//        preferencesManager.put("mobile", loginRep.getMobile());
//        preferencesManager.put("userId", loginRep.getUserId());
        // TODO: 2018/5/25  userId需要传到服务器
        AddCustomAttendanceReq addCustomAttendanceReq = new AddCustomAttendanceReq(sAttendancePointId,
                mItemId, mSigninPointName, mTvAddCustomAttendanceAddress, mLongitude, mLatitude,
                range, UserIdUtil.getUserIdLong(), list2);
        mPresenter.saveAttendancePointDetails(addCustomAttendanceReq);//保存数据
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

    /**
     * 删除考勤点
     */
    @Override
    public void deleteAttendancePoint() {
        mAttendanceSettingRep = new AttendanceSettingRep();
        mAttendanceSettingRep.setIsDel("1");
        mAttendanceSettingRep.setSigninPointId(sAttendancePointId);
        mPresenter.deleteAttendancePointRequest(mAttendanceSettingRep);
    }

    /**
     * 删除考勤点成功
     */
    @Override
    public void deleteAttendancePointResult() {
        ToastUtil.showToast(mContext, "删除成功");
        finish();
    }

    /**
     * 进入编辑页面发送网络请求考勤点信息
     *
     * @param response
     */
    @Override
    public void backData(AddCustomAttendanceReq response) {
        mBinding.tvAttendanceEditAddress.setText(response.getSigninPointAddress());
        mBinding.tvAttendanceEditRange.setText(response.getSigninValidRange() + "米");
        mLatitude = response.getSigninPointLaTitude();
        mLongitude = response.getSigninPointLongTitude();
        mItemId = response.getItemId();
        List<SigninTime> signTime = response.getSignTime();
        mIds = new HashMap<>();
        for (SigninTime s : signTime) {
            String signinDay = s.getSigninDay();
            switch (signinDay) {
                case "0":
                    mIds.put(0, s.getSigninTimeId());

                    viewSetData2(s.getSigninIsBreak(), s.getSigninStartTime(), s.getSigninEndTime(),
                            mBinding.cbPunchSetting1, mBinding.tvAttendanceEditTime1, mBinding.tvAttendanceEditTime12);
                    break;
                case "1":
                    mIds.put(1, s.getSigninTimeId());

                    viewSetData2(s.getSigninIsBreak(), s.getSigninStartTime(), s.getSigninEndTime(),
                            mBinding.cbPunchSetting2, mBinding.tvAttendanceEditTime1, mBinding.tvAttendanceEditTime22);

                    break;
                case "2":
                    mIds.put(2, s.getSigninTimeId());
                    viewSetData2(s.getSigninIsBreak(), s.getSigninStartTime(), s.getSigninEndTime(),
                            mBinding.cbPunchSetting3, mBinding.tvAttendanceEditTime1, mBinding.tvAttendanceEditTime32);

                    break;
                case "3":
                    mIds.put(3, s.getSigninTimeId());
                    viewSetData2(s.getSigninIsBreak(), s.getSigninStartTime(), s.getSigninEndTime(),
                            mBinding.cbPunchSetting4, mBinding.tvAttendanceEditTime1, mBinding.tvAttendanceEditTime42);

                    break;
                case "4":
                    mIds.put(4, s.getSigninTimeId());
                    viewSetData2(s.getSigninIsBreak(), s.getSigninStartTime(), s.getSigninEndTime(),
                            mBinding.cbPunchSetting5, mBinding.tvAttendanceEditTime1, mBinding.tvAttendanceEditTime52);

                    break;
                case "5":
                    mIds.put(5, s.getSigninTimeId());
                    viewSetData2(s.getSigninIsBreak(), s.getSigninStartTime(), s.getSigninEndTime(),
                            mBinding.cbPunchSetting6, mBinding.tvAttendanceEditTime1, mBinding.tvAttendanceEditTime62);

                    break;
                case "6":
                    mIds.put(6, s.getSigninTimeId());
                    viewSetData2(s.getSigninIsBreak(), s.getSigninStartTime(), s.getSigninEndTime(),
                            mBinding.cbPunchSetting7, mBinding.tvAttendanceEditTime1, mBinding.tvAttendanceEditTime72);

                    break;

                default:

                    break;
            }

        }
    }

    @Override
    public void editAttendancePointResult() {
        finish();
    }

    /**
     * @param signinIsBreak   是否休息 1:休息 0:不休息
     * @param signinStartTime
     * @param signinEndTime
     * @param box
     * @param tv1
     * @param tv2
     */
    private void viewSetData2(String signinIsBreak, String signinStartTime, String signinEndTime, CheckBox box, TextView tv1, TextView tv2) {

        tv1.setText(signinStartTime);
        tv2.setText(signinEndTime);
        if (StrUtil.isEmpty(signinIsBreak))
            signinIsBreak = "1";//默认不休息
        switch (signinIsBreak) {
            case "0":
                box.setChecked(true);
                break;
            case "1":
                box.setChecked(false);
                break;

            default:

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x456 && resultCode == 0x457) {
            if (data != null) {
                mLatitude = data.getStringExtra("latitude");
                mLongitude = data.getStringExtra("longitude");
                mAddress = data.getStringExtra("address");
                mBinding.tvAttendanceEditAddress.setText(mAddress);       //考勤点名称
                LogUtil.e("TAG", "laughing--------------mLatitude-------->   " + mLatitude);
                LogUtil.e("TAG", "laughing--------------mLongitude-------->   " + mLongitude);
                LogUtil.e("TAG", "laughing--------------mAddress-------->   " + mAddress);
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
                viewSetData(i, dateStr, mBinding.cbPunchSetting1, mBinding.tvAttendanceEditTime1,
                        mBinding.tvAttendanceEditTime12);

                break;
            case 2:
                viewSetData(i, dateStr, mBinding.cbPunchSetting2, mBinding.tvAttendanceEditTime2,
                        mBinding.tvAttendanceEditTime22);

                break;
            case 3:
                viewSetData(i, dateStr, mBinding.cbPunchSetting3, mBinding.tvAttendanceEditTime3,
                        mBinding.tvAttendanceEditTime32);

                break;
            case 4:
                viewSetData(i, dateStr, mBinding.cbPunchSetting4, mBinding.tvAttendanceEditTime4,
                        mBinding.tvAttendanceEditTime42);

                break;
            case 5:
                viewSetData(i, dateStr, mBinding.cbPunchSetting5, mBinding.tvAttendanceEditTime5,
                        mBinding.tvAttendanceEditTime52);

                break;
            case 6:
                viewSetData(i, dateStr, mBinding.cbPunchSetting6, mBinding.tvAttendanceEditTime6,
                        mBinding.tvAttendanceEditTime62);

                break;
            case 7:
                viewSetData(i, dateStr, mBinding.cbPunchSetting7, mBinding.tvAttendanceEditTime7,
                        mBinding.tvAttendanceEditTime72);

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
            mBinding.tvAttendanceEditRange.setText("300米");
            dialog.dismiss();
        });
        tv_urge_task.setOnClickListener(v -> {
            //500米
            mBinding.tvAttendanceEditRange.setText("500米");
            dialog.dismiss();

        });
        tv_edit_task.setOnClickListener(v -> {
            // 1000米
            mBinding.tvAttendanceEditRange.setText("1000米");
            dialog.dismiss();

        });

        tv_cancel_task.setOnClickListener(v -> {
            dialog.dismiss();
            // 2000米
            mBinding.tvAttendanceEditRange.setText("2000米");

        });
    }

}
