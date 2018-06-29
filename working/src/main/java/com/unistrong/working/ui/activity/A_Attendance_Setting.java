package com.unistrong.working.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.global.util.DateUtils;
import com.global.util.UserIdUtil;
import com.unistrong.working.R;
import com.unistrong.working.databinding.AAttendanceSettingBinding;
import com.unistrong.working.response.AttendanceSettingRep;
import com.unistrong.working.ui.adapter.Adapter_Attendance_Setting;
import com.unistrong.working.ui.mvpview.AttendanceSettingView;
import com.unistrong.working.ui.presenter.AttendanceSettingPresenter;
import com.waterbase.widget.recycleview.OnLoadMoreListener;

import java.util.List;

/**
 * 考勤设置
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Attendance_Setting extends TitleActivity implements AttendanceSettingView<List<AttendanceSettingRep>> {

    private AAttendanceSettingBinding mBinding;
    private AttendanceSettingPresenter mPresenter;
    private Adapter_Attendance_Setting mAdapter;
    private Context mContext = this;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_attendance_setting);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mPresenter = new AttendanceSettingPresenter(this, this, this);
        mAdapter = new Adapter_Attendance_Setting();
        mBinding.srlAttendanceSetting.setRefreshing(false);
        mBinding.rvAttendanceSetting.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvAttendanceSetting.setAdapter(mAdapter);

    }

    private void initListener() {
        mBinding.setClick(v -> mPresenter.click(v));
        mBinding.srlAttendanceSetting.setOnRefreshListener(() -> {
            mPresenter.refData(UserIdUtil.getUserIdLong());
        });
        mAdapter.openAutoLoadMore(true);
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMoreData(UserIdUtil.getUserIdLong());

            }
        });

        mAdapter.setItemClickListener(new ItemClickListener<AttendanceSettingRep>() {

            @Override
            public void itemClick(View v, AttendanceSettingRep attendanceSettingRep, int index) {
                Intent intent = new Intent();
                intent.putExtra(A_Attendance_Edit.Item_Attendance_Setting, attendanceSettingRep);
                A_Attendance_Edit.startActivity(mContext, intent);
            }
        });

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Attendance_Setting.class));
    }

    public void initTitle() {
        setTitleText("考勤设置");//标题
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_add);
    }

    //从别的页面回到此页面时加载数据
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.refData(UserIdUtil.getUserIdLong());//获取已添加的考勤列表
    }

    @Override
    protected void rightOneImageOnClick() {
        super.rightOneImageOnClick();
        showDialog1();
    }

    /**
     * 选择图片对话框1-派给我的-详情
     */
    public void showDialog1() {
        View localView = LayoutInflater.from(this).inflate(
                R.layout.dialog_feedback_progress, null);
        TextView priority = (TextView) localView.findViewById(R.id.tv_set_priority);
        priority.setText("自定义考勤点");
        TextView progress = (TextView) localView.findViewById(R.id.tv_feedback_progress);
        progress.setText("项目考勤点");
        TextView tv_cancel = (TextView) localView.findViewById(R.id.tv_cancel);
        dialog = new Dialog(mContext, com.global.R.style.custom_dialog);
        DateUtils.initDialog(dialog, localView, getWindowManager());
        dialog.show();
        tv_cancel.setOnClickListener(arg0 -> {
            //取消按钮
            dialog.dismiss();
        });

        priority.setOnClickListener(v -> {
            dialog.dismiss();
            // 自定义考勤点
            A_Add_Custom_Attendance.startActivity(mContext);
        });

        progress.setOnClickListener(v -> {
            dialog.dismiss();
            // 项目考勤点
            A_Add_Project_Attendance.startActivity(mContext);

        });
    }

    /**
     * 首次进入页面加载数据
     *
     * @param response
     */
    @Override
    public void loadData(List<AttendanceSettingRep> response) {
        mBinding.srlAttendanceSetting.setRefreshing(false);//停止刷新
        mAdapter.setData(response);


    }

    /**
     * 上拉加载更多
     *
     * @param response
     */
    @Override
    public void loadMoreData(List<AttendanceSettingRep> response) {
        if (response == null || response.isEmpty()) {
            mAdapter.loadCompleted();
            return;
        }
        mAdapter.addData(response);
    }
}
