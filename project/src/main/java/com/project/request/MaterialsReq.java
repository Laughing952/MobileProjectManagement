package com.project.request;

import com.google.gson.annotations.SerializedName;
import com.project.response.MaterialsRep;

import java.io.Serializable;

/**
 * 出库物资
 * Created by Water on 2018/5/24.
 */

public class MaterialsReq implements Serializable {
    @SerializedName("materialsId")
    private String materialsID; // 物资ID
    private String materialsName; // 物资名称
    private String standardName; // 物资规格
    private String companyName; // 单位名称
    @SerializedName("outNumber")
    private String askOutNum; // 请求出库的数量

    @SerializedName("mateCode")
    private String encoding;// 编码

    @Override
    public String toString() {
        return "MaterialsReq{" +
                "materialsID='" + materialsID + '\'' +
                ", materialsName='" + materialsName + '\'' +
                ", standardName='" + standardName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", askOutNum='" + askOutNum + '\'' +
                '}';
    }

    public MaterialsReq(MaterialsRep materialsRep) {
        this.materialsID = materialsRep.getMaterialsID();
        this.materialsName = materialsRep.getMaterialsName();
        this.standardName = materialsRep.getStandardName();
        this.companyName = materialsRep.getCompanyName();
        this.askOutNum = materialsRep.getAskOutNum();
        this.encoding = materialsRep.getEncoding();
    }

    public String getMaterialsID() {
        return materialsID;
    }

    public void setMaterialsID(String materialsID) {
        this.materialsID = materialsID;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAskOutNum() {
        return askOutNum;
    }

    public void setAskOutNum(String askOutNum) {
        this.askOutNum = askOutNum;
    }
}
