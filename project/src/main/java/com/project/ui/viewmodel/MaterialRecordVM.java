package com.project.ui.viewmodel;

import com.project.R;
import com.project.response.MaterialRecordRep;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.DateUtil;
import com.waterbase.utile.StrUtil;

/**
 * 物资流水
 * Created by Water on 2018/5/23.
 */

public class MaterialRecordVM {

    private MaterialRecordRep rep;

    private String date; // 日期
    private String recorder; // 记录人
    private String projectName;// 项目名称
    private String num;// 数量

    private int flag; // 0入库1出库


    public MaterialRecordVM(MaterialRecordRep rep) {
        this.rep = rep;
    }

    public String getDate() {
//        String str = null;
//        try {
//            str = DateUtil.getStringByFormat(Long.parseLong(rep.getDate()), "yyyy-MM-dd HH:mm:ss");
//        }catch (NumberFormatException e){
//            e.printStackTrace();
//        }
//        return str;
        return rep.getDate();
    }

    public String getRecorder() {
        return rep.getRecorder();
    }

    public String getProjectName() {
        return rep.getProjectName();
    }

    public String getNum() {
        if (!StrUtil.isEmpty(rep.getInNum())) {
            return "+" + rep.getInNum();
        } else if (!StrUtil.isEmpty(rep.getOutNum())) {
            return "-" + rep.getOutNum();
        } else {
            return "未知";
        }
    }

    public int getFlag() {
        if (!StrUtil.isEmpty(rep.getInNum())) {
            return 0;
        } else if (!StrUtil.isEmpty(rep.getOutNum())) {
            return 1;
        } else {
            return -1;
        }
    }

//    public int getTextColorRes() {
//        if (rep.getFlag() == 0)
//            BaseApplication.getAppContext().getResources().getColor(R.color.red);
//        else if (rep.getFlag() == 1)
//            BaseApplication.getAppContext().getResources().getColor(R.color.main_color);
//        return BaseApplication.getAppContext().getResources().getColor(R.color.black);
//    }
}
