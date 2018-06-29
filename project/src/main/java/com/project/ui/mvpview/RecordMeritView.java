package com.project.ui.mvpview;

import com.project.ui.viewmodel.CalendarVM;

import java.util.List;

/**
 * 记工
 * Created by Water on 2018/5/26.
 */

public interface RecordMeritView {

    void initCalendar(List<CalendarVM> calendarVMList);
    void initDate(String dateStr);
    String getPId();
}
