package com.project.response;

import com.google.gson.annotations.SerializedName;
import com.waterbase.http.common.RxRetrofitApp;
import com.waterbase.ui.BaseApplication;

import java.io.Serializable;

/**
 * 未完成项目/已归档-项目列表
 * 作者：Laughing on 2018/5/15 16:27
 * 邮箱：719240226@qq.com
 */

public class ProjectDoingRep implements Serializable {

    /**
     * "buildCompany": "广州公司",
     * "constructionCompany": "北京公司",
     * "creater": "张三",
     * "designCompany": "西安基地",
     * "image": "fafsfa",
     * "itemLocation": "司法所地方",
     * "itemhead": "zhangsheng",
     * "itemid": "1",
     * "itemname": "普通项目",
     * "itemnote": "发多少",
     * "itemtype": "普通",
     * "modifier": "张三",
     * "modifyTime": 1525707426000,
     * "remark": "无",
     * "status": "1",
     * "supervisorCompany": "深圳集团",
     * "thumbnail": "wew"
     */
    private String buildCompany;//项目建设单位
    private String constructionCompany;//项目施工单位
    private String creater;//创建人
    private String designCompany;//项目设计单位
    @SerializedName("bigImage")
    private String image;//项目图片
    private String itemLocation;//项目地址
    @SerializedName("itemHead")
    private String itemhead;//项目负责人
    @SerializedName("itemId")
    private String itemid;//项目ID
    @SerializedName("itemName")
    private String itemname;//项目名称
    private String itemnote;//项目概况
    private String itemtype;//项目类别
    private String modifier;// 修改人
    private String modifyTime;//// 修改时间
    private String remark;// 项目备注
    private String status;//项目状态
    private String supervisorCompany;//项目监理单位
    @SerializedName("smallImage")
    private String thumbnail;//项目缩略图
    private String creatTime;//创建时间
    private String schedule;//计划进度
    private String workDay;//用工工日

    public String getWorkDay() {
        return "人工："+workDay+"工日";
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public String getSchedule() {
        return "进度："+schedule+"%";
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getBuildCompany() {
        return buildCompany;
    }

    public void setBuildCompany(String buildCompany) {
        this.buildCompany = buildCompany;
    }

    public String getConstructionCompany() {
        return constructionCompany;
    }

    public void setConstructionCompany(String constructionCompany) {
        this.constructionCompany = constructionCompany;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getDesignCompany() {
        return designCompany;
    }

    public void setDesignCompany(String designCompany) {
        this.designCompany = designCompany;
    }

    public String getImage() {
        return RxRetrofitApp.getApiServerUrl() +image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public String getItemhead() {
        return "负责人："+itemhead;
    }

    public void setItemhead(String itemhead) {
        this.itemhead = itemhead;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemnote() {
        return itemnote;
    }

    public void setItemnote(String itemnote) {
        this.itemnote = itemnote;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupervisorCompany() {
        return supervisorCompany;
    }

    public void setSupervisorCompany(String supervisorCompany) {
        this.supervisorCompany = supervisorCompany;
    }

    public String getThumbnail() {
        return RxRetrofitApp.getApiServerUrl() +thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
