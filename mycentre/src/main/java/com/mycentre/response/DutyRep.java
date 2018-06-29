package com.mycentre.response;

import com.waterbase.global.PsRow;

import java.io.Serializable;
import java.util.List;

/**
 * 职务
 * Created by Water on 2018/5/9.
 */

public class DutyRep implements Serializable {

    private String dutyID;
    private String name; // 职务名称
    private List<JurisdictionRep> jurisdictionRepList;

    public DutyRep(String dutyID, String name, List<JurisdictionRep> jurisdictionRepList) {
        this.dutyID = dutyID;
        this.name = name;
        this.jurisdictionRepList = jurisdictionRepList;
    }

    public String getDutyID() {
        return dutyID;
    }

    public void setDutyID(String dutyID) {
        this.dutyID = dutyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JurisdictionRep> getJurisdictionRepList() {
        return jurisdictionRepList;
    }

    public void setJurisdictionRepList(List<JurisdictionRep> jurisdictionRepList) {
        this.jurisdictionRepList = jurisdictionRepList;
    }
}
