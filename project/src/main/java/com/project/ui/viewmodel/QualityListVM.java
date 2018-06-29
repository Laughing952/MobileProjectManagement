package com.project.ui.viewmodel;

import android.graphics.Color;

import com.project.R;
import com.project.response.QualityListRep;
import com.waterbase.ui.BaseApplication;

/**
 * 质量检查列表
 * Created by Water on 2018/5/10.
 */

public class QualityListVM {

    private QualityListRep rep; // 质量检查列表服务器的返回

    private String e_date; //检查日期 YYYYMMDD
    private String e_time; //检查时间 YYYYMMDD HH:MM
    private String e_divisionalName; //检查部门名称
    private String e_items; //检查项目
    private String e_person; // 检查人
    private String e_resultContent; // 检查结果说明
    private String e_result; // 检查结果
    private int resultTVColor; // 检查结果的字体颜色

    private String p_Name;
    private boolean isShowAbarbeitung;


    public QualityListVM(QualityListRep rep) {
        this.rep = rep;
    }

    public String getE_date() {
        String date = rep.getE_date();
        return date;
    }

    public String getE_time() {
        return rep.getE_date();
    }

    public String getE_divisionalName() {
        return rep.getE_divisionalName();
    }

    public String getE_items() {
        return rep.getE_items();
    }

    public String getE_person() {
        return rep.getE_person();
    }

    public String getE_resultContent() {
        return rep.getE_resultContent();
    }

    public String getE_result() {
        if (rep.getE_result() == 1)
            return "通过";
        else if (rep.getE_result() == 2)
            return "未通过";
        return "未知的状态";
    }

    public int getResultTVColor() {
        if (rep.getE_result() == 1)
            return BaseApplication.getAppContext().getResources().getColor(R.color.main_color);
        else if (rep.getE_result() == 2)
            return BaseApplication.getAppContext().getResources().getColor(R.color.red);
        return Color.YELLOW;
    }

    public String getP_Name() {
        return rep.getP_name();
    }

    public boolean isShowAbarbeitung() {
        if (rep.getE_result() == 1)
            return false;
        else if (rep.getE_result() == 2)
            return true;
        return false;
    }
}
