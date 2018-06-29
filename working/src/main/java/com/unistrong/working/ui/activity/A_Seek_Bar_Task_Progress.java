package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;

import com.global.ui.activity.TitleActivity;
import com.global.util.UserIdUtil;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ASeekBarTaskProgressBinding;
import com.unistrong.working.request.AlterTaskScheduleReq;
import com.unistrong.working.ui.mvpview.SeekBarTaskProgressView;
import com.unistrong.working.ui.presenter.SeekBarTaskProgressPresenter;
import com.waterbase.utile.ToastUtil;


/**
 * 任务反馈-进度
 * 作者：Laughing on 2018/5/11 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Seek_Bar_Task_Progress extends TitleActivity implements SeekBarTaskProgressView {

    private ASeekBarTaskProgressBinding mBinding;
    private SeekBarTaskProgressPresenter mSeekBarTaskProgressPresenter;
    private int mFirst;
    private Context mContext = this;
    private int progress;
    private String taskId;

    public static void startActivity(Context context,String schedule,String taskId) {
        Intent intent = new Intent(context, A_Seek_Bar_Task_Progress.class);
        intent.putExtra("schedule",schedule);
        intent.putExtra("taskId",taskId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_seek_bar_task_progress);
        initTitle();
        initData();
        initListener();
    }

    private void initListener() {
        mBinding.setClick(v -> mSeekBarTaskProgressPresenter.click(v));
        seekBarDrag();//处理进度条拖动事件
    }

    private void seekBarDrag() {
        mBinding.sbSeekBarTaskProgress.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
            /**
             * 拖动条停止拖动的时候调用
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                mBinding.tvSeekBarTaskProgressNum.setText("拖动停止");
            }

            /**
             * 拖动条开始拖动的时候调用
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                mBinding.tvSeekBarTaskProgressProgress.setText("开始拖动");
            }

            /**
             * 拖动条进度改变的时候调用
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                if (progress <= mFirst) {
                    //禁止滑动（保留之前的进度）
                    mBinding.tvSeekBarTaskProgressNum.setText(mFirst + "%");
                    seekBar.setProgress(mFirst);
                } else {
                    //可以滑动（增加任务进度）
                    A_Seek_Bar_Task_Progress.this.progress=progress;
                    mBinding.tvSeekBarTaskProgressNum.setText(progress + "%");

                }

            }
        });
    }

    private void initData() {
        mSeekBarTaskProgressPresenter = new SeekBarTaskProgressPresenter(this, this, this);
        mFirst = Integer.parseInt(getIntent().getStringExtra("schedule"));
        taskId = getIntent().getStringExtra("taskId");
        mBinding.sbSeekBarTaskProgress.setProgress(mFirst);
        mBinding.tvSeekBarTaskProgressNum.setText(""+mFirst + "%");

    }

    public void initTitle() {
        setTitleText("任务反馈-进度");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("确定");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        if (progress>mFirst) {
            AlterTaskScheduleReq alterTaskScheduleReq
                    = new AlterTaskScheduleReq("" + progress, UserIdUtil.getUserIdLong(),
                    "1", taskId);
            mSeekBarTaskProgressPresenter.alterTaskSchedule(alterTaskScheduleReq);
        }else {
            ToastUtil.showToast(this,"反馈进度必须大于当前进度");
        }

    }

    /**
     * 确认提交进度
     */
    @Override
    public void confirm() {
        ToastUtil.showToast(mContext, "保存成功");
        finish();
    }
}
