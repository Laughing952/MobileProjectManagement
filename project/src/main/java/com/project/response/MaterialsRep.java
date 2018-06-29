package com.project.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 物资数据
 * Created by Water on 2018/5/22.
 */

public class MaterialsRep implements Serializable {

    @SerializedName("materialId")
    private String classifyeID; // 分类ID
    @SerializedName("materialType")
    private String classifyeName; // 分类名称
    @SerializedName("materialsId")
    private String materialsID; // 物资ID
    @SerializedName("mateName")
    private String materialsName; // 物资名称
    @SerializedName("mateModel")
    private String standardName; // 物资规格
    @SerializedName("unit")
    private String companyName; // 单位名称

    @SerializedName("mateCode")
    private String encoding;// 编码
    @SerializedName("note")
    private String remark;//备注

    @SerializedName("inCount")
    private String inNum; // 入库量
    @SerializedName("outCount")
    private String outNum;// 出库量
    @SerializedName("quantity")
    private String saveNum;// 库存量

    private String askOutNum; // 请求出库的数量
    @SerializedName("materialList")
    private List<MaterialRecordRep> recordRepList;// 物资流水列表

    public MaterialsRep(String classifyeName, String materialsName, String standardName
            , String companyName, String encoding, String remark, String inNum, String outNum
            , String saveNum, List<MaterialRecordRep> recordRepList) {
        this.classifyeName = classifyeName;
        this.materialsName = materialsName;
        this.standardName = standardName;
        this.companyName = companyName;
        this.encoding = encoding;
        this.remark = remark;
        this.inNum = inNum;
        this.outNum = outNum;
        this.saveNum = saveNum;
        this.recordRepList = recordRepList;
    }

    public MaterialsRep(String classifyeID, String classifyeName, String materialsID, String materialsName
            , String standardName, String companyName, String inNum, String outNum, String saveNum) {
        this.classifyeID = classifyeID;
        this.classifyeName = classifyeName;
        this.materialsID = materialsID;
        this.materialsName = materialsName;
        this.standardName = standardName;
        this.companyName = companyName;
        this.inNum = inNum;
        this.outNum = outNum;
        this.saveNum = saveNum;
    }

    public String getClassifyeID() {
        return classifyeID;
    }

    public void setClassifyeID(String classifyeID) {
        this.classifyeID = classifyeID;
    }

    public String getClassifyeName() {
        return classifyeName;
    }

    public void setClassifyeName(String classifyeName) {
        this.classifyeName = classifyeName;
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

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInNum() {
        return inNum;
    }

    public void setInNum(String inNum) {
        this.inNum = inNum;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getSaveNum() {
        return saveNum;
    }

    public void setSaveNum(String saveNum) {
        this.saveNum = saveNum;
    }

    public String getAskOutNum() {
        return askOutNum;
    }

    public void setAskOutNum(String askOutNum) {
        this.askOutNum = askOutNum;
    }

    public List<MaterialRecordRep> getRecordRepList() {
        return recordRepList;
    }

    public void setRecordRepList(List<MaterialRecordRep> recordRepList) {
        this.recordRepList = recordRepList;
    }
}
