package com.mycentre.ui.viewmodel;

import com.mycentre.response.DutyRep;
import com.mycentre.response.JurisdictionRep;

import java.util.List;

/**
 * Created by Water on 2018/5/9.
 */

public class DutyJurisdictionVM {

    private DutyRep dutyRep;

    private int index;
    private String dutyName; // 职务名称
    private boolean isShowLv; // 是否展示最高级
    private String sumJurisdiction; // 权限总个数
    private String jurisdictionInfo; // 权限列举

    public DutyJurisdictionVM(DutyRep dutyRep, int index) {
        this.dutyRep = dutyRep;
        this.index = index;
    }

    public String getIndex() {
        return String.valueOf(index);
    }

    public String getDutyName() {
        return dutyRep.getName();
    }

    public boolean isShowLv() {
        return index == 0 ? true : false;
    }

    public String getSumJurisdiction() {
        int size = dutyRep.getJurisdictionRepList().isEmpty() ? 0 : dutyRep.getJurisdictionRepList().size();
        return "共" + size + "项权限";
    }

    public String getJurisdictionInfo() {
        StringBuilder sb = new StringBuilder();
        if (!dutyRep.getJurisdictionRepList().isEmpty()) {
            for (JurisdictionRep jurisdictionRep : dutyRep.getJurisdictionRepList()) {
                sb.append(jurisdictionRep.getJurisdictionName());
            }
        }
        return sb.toString();
    }
}
