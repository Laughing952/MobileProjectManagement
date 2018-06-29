package com.project.request;

import com.google.gson.annotations.SerializedName;
import com.project.response.ImageRep;
import com.project.response.QualityListRep;

import java.util.ArrayList;
import java.util.List;

/**
 * 新建/编辑质量检查
 * Created by Water on 2018/5/11.
 */

public class NewQualityReq {
    @SerializedName("itemId")
    private String p_ID; //项目id
    @SerializedName("qcId")
    private String qualityID; // 质量检查ID
    @SerializedName("qcType")
    private String examineTypeiD; //检查类型ID
    @SerializedName("qcDate")
    private String examineDate; //检查时间
    @SerializedName("qcList")
    private String examineItems; //检查项
    @SerializedName("qcResult")
    private String examineResultInfo; //检查结果
    @SerializedName("imgList")
    private List<ImageRep> imageList; //检查图片集合
    @SerializedName("qcIspass")
    private int examineResult; //检查类型ID
    @SerializedName("creater")
    private String creater; // 创建人ID
    @SerializedName("modifier")
    private String modifier; // 修改人


    private QualityListRep rep;

    public NewQualityReq(QualityListRep rep) {
        if (rep != null){
            p_ID = rep.getpId();
            qualityID = rep.getE_id();
            examineTypeiD = rep.getQcType();
            examineDate = rep.getE_date();
            examineItems = rep.getE_items();
            examineResultInfo = rep.getE_resultContent();
            imageList = rep.getImageRepList();
            examineResult = rep.getE_result();
        }
    }

    public String getP_ID() {
        return p_ID;
    }

    public void setP_ID(String p_ID) {
        this.p_ID = p_ID;
    }

    public String getQualityID() {
        return qualityID;
    }

    public void setQualityID(String qualityID) {
        this.qualityID = qualityID;
    }

    public String getExamineTypeiD() {
        return examineTypeiD;
    }

    public void setExamineTypeiD(String examineTypeiD) {
        this.examineTypeiD = examineTypeiD;
    }

    public String getExamineDate() {
        return examineDate;
    }

    public void setExamineDate(String examineDate) {
        this.examineDate = examineDate;
    }

    public String getExamineItems() {
        return examineItems;
    }

    public void setExamineItems(String examineItems) {
        this.examineItems = examineItems;
    }

    public String getExamineResultInfo() {
        return examineResultInfo;
    }

    public void setExamineResultInfo(String examineResultInfo) {
        this.examineResultInfo = examineResultInfo;
    }

    public List<ImageRep> getImageList() {
        if (imageList == null)
            imageList = new ArrayList<>();
        return imageList;
    }

    public void setImageList(List<ImageRep> imageList) {
        this.imageList = imageList;
    }

    public int getExamineResult() {
        return examineResult;
    }

    public void setExamineResult(int examineResult) {
        this.examineResult = examineResult;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
