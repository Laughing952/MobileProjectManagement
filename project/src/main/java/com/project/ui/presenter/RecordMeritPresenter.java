package com.project.ui.presenter;

import android.view.View;

import com.project.R;
import com.project.ui.activity.A_Record_Merit;
import com.project.ui.activity.A_Workers_List;
import com.project.ui.mvpview.QualityView;
import com.project.ui.mvpview.RecordMeritView;
import com.project.ui.viewmodel.CalendarVM;
import com.project.util.CalendarUtiles;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

import java.util.List;

/**
 * 记工
 * Created by Water on 2018/5/26.
 */

public class RecordMeritPresenter {

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private RecordMeritView mView;

    private int year;
    private int month;

    public RecordMeritPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, RecordMeritView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void initCalendar() {
        year = CalendarUtiles.getYear();
        month = CalendarUtiles.getMonth();
        initCalendarDate();
    }

    public void click(View v) {
        if (v.getId() == R.id.iv_left) {
            prevMonth();
        } else if (v.getId() == R.id.iv_right) {
            nextMonth();
        } else if (v.getId() == R.id.rl_record_merit_statistics) {
            // TODO: 2018/5/14 项目记工统计
        } else if (v.getId() == R.id.rl_workers_register) {
            // TODO: 2018/5/14 我的工人名册
            A_Workers_List.startActivity(activity, mView.getPId());
        }
    }

    private void initCalendarDate(){
        mView.initDate(year + "年" + month + "月");
        List<CalendarVM> calendarVMList = CalendarUtiles.getDayOfMonthFormat(year, month);
        mView.initCalendar(calendarVMList);
    }

    /**
     * 下一个月
     *
     * @return
     */
    private void nextMonth() {
        if (month == 12) {
            month = 1;
            year++;
        } else {
            month++;
        }

        initCalendarDate();
    }

    /**
     * 上一个月
     *
     * @return
     */
    private void prevMonth() {
        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
        initCalendarDate();
    }
}
