package com.mycentre.response;

import java.io.Serializable;

/**
 * 权限
 * Created by Water on 2018/5/9.
 */

public class JurisdictionRep implements Serializable {
    private String jurisdictionId;
    private String jurisdictionName;

    public JurisdictionRep(String jurisdictionId, String jurisdictionName) {
        this.jurisdictionId = jurisdictionId;
        this.jurisdictionName = jurisdictionName;
    }

    public String getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }
}
