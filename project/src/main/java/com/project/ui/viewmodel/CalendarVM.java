package com.project.ui.viewmodel;

import com.waterbase.utile.DateUtil;
import com.waterbase.utile.LogUtil;

import java.io.Serializable;

/**
 * Created by Water on 2018/5/7.
 */

public class CalendarVM implements Serializable{
    private int year;
    private int month;
    private int day;
    private float statistics; // 记功统计
    private boolean isShowAdd;
    private boolean isShowNum;
    private boolean isClick;

    public CalendarVM() {
    }

    public CalendarVM(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDay() {
        return day == 0 ? null : String.valueOf(day);
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getStatistics() {
        return String.format("%.1f", statistics);
    }

    public void setStatistics(float statistics) {
        this.statistics = statistics;
    }

    public boolean isShowAdd() {
        if (day == 0)
            return false;
        if (statistics != 0)
            return false;
        StringBuilder builder = new StringBuilder();
        builder.append(getYear());
        if (month < 10) {
            builder.append(0);
            builder.append(month);
        } else
            builder.append(month);

        if (day < 10) {
            builder.append(0);
            builder.append(day);
        } else
            builder.append(day);
        LogUtil.d("-----", "isShowAdd1 :  " + DateUtil.getCurrentDate("yyyyMMdd"));
        LogUtil.d("-----", "isShowAdd2 :  " + builder.toString());
        LogUtil.d("-----", "isShowAdd :  " + DateUtil.TimeCompare(DateUtil.getCurrentDate("yyyyMMdd"), builder.toString()));
        if (DateUtil.TimeCompare(DateUtil.getCurrentDate("yyyyMMdd"), builder.toString()))
            return false;
        return true;
    }

    public boolean isShowNum() {
        if (day == 0)
            return false;
        if (statistics == 0)
            return false;
        StringBuilder builder = new StringBuilder();
        builder.append(getYear());
        if (month < 10) {
            builder.append(0);
            builder.append(month);
        } else
            builder.append(month);

        if (day < 10) {
            builder.append(0);
            builder.append(day);
        } else
            builder.append(day);
        if (DateUtil.TimeCompare(DateUtil.getCurrentDate("yyyyMMdd"), builder.toString()))
            return false;
        return true;
    }

    public boolean isClick() {
        if (day == 0)
            return false;
        StringBuilder builder = new StringBuilder();
        builder.append(getYear());
        if (month < 10) {
            builder.append(0);
            builder.append(month);
        } else
            builder.append(month);

        if (day < 10) {
            builder.append(0);
            builder.append(day);
        } else
            builder.append(day);
        if (DateUtil.TimeCompare(DateUtil.getCurrentDate("yyyyMMdd"), builder.toString()))
            return false;
        return true;
    }
}
