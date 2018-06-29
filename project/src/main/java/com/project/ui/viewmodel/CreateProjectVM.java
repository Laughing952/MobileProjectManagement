package com.project.ui.viewmodel;


import com.project.response.ImageRep;

import java.util.Date;
import java.util.List;

/**
 * 创建项目
 * 作者：Laughing on 2018/5/4 13:22
 * 邮箱：719240226@qq.com
 */

public class CreateProjectVM {

    private String itemId;// 项目id
    private String itemName;// 项目名称
    private String itemNote;// 项目概况
    private String itemHead;// 项目负责人
    private String itemHeadId;// 项目负责人
    private String remark;// 项目备注
    private String userId;// 用户ID
    private String buildCompany;// 项目建设单位
    private String supervisorCompany;// 项目监理单位
    private String designCompany;// 项目设计单位
    private String constructionCompany;// 项目施工单位
    private String tId;// 项目成员表主键
    private String shortName;// 项目简称
    private String schedule;// 计划进度
    private String quality;// 质量整改
    private String security;// 安全整改
    private List<ProjectMemberCheckedVM> list;// 项目成员集合
    private Date createTime;// 创建时间
    private Date modifyTime;// 修改时间
    private String creater;// 创建人
    private String modifier;// 修改人
    private String workDay;// 用工工日
    private String bigImage;// 项目图片
    private String smallImage;// 项目缩略图
    private ImageRep imageRep;
    private String personNum;//项目人员个数

    public String getPersonNum() {
        if (list!=null) {
            return "" + list.size() + "人";
        }
        return "";
    }

    public void setPersonNum(String personNum) {
        this.personNum = personNum;
    }

    public ImageRep getImageRep() {
        return imageRep;
    }

    public void setImageRep(ImageRep imageRep) {
        this.imageRep = imageRep;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNote() {
        return itemNote;
    }

    public void setItemNote(String itemNote) {
        this.itemNote = itemNote;
    }

    public String getItemHead() {
        return itemHead;
    }

    public void setItemHead(String itemHead) {
        this.itemHead = itemHead;
    }

    public String getItemHeadId() {
        return itemHeadId;
    }

    public void setItemHeadId(String itemHeadId) {
        this.itemHeadId = itemHeadId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBuildCompany() {
        return buildCompany;
    }

    public void setBuildCompany(String buildCompany) {
        this.buildCompany = buildCompany;
    }

    public String getSupervisorCompany() {
        return supervisorCompany;
    }

    public void setSupervisorCompany(String supervisorCompany) {
        this.supervisorCompany = supervisorCompany;
    }

    public String getDesignCompany() {
        return designCompany;
    }

    public void setDesignCompany(String designCompany) {
        this.designCompany = designCompany;
    }

    public String getConstructionCompany() {
        return constructionCompany;
    }

    public void setConstructionCompany(String constructionCompany) {
        this.constructionCompany = constructionCompany;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public List<ProjectMemberCheckedVM> getList() {
        return list;
    }

    public void setList(List<ProjectMemberCheckedVM> list) {
        this.list = list;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }
}
