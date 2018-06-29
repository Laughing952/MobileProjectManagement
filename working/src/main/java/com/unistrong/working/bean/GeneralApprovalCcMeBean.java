package com.unistrong.working.bean;

import java.util.List;

/**
 *我提交的-通用申请（取消）
 * 作者：Laughing on 2018/5/10 09:53
 * 邮箱：719240226@qq.com
 */

public class GeneralApprovalCcMeBean {
    private String name;//申请人
    private String id;//申请人Id
    private String approval;//审批人
    private String date;//时间
    private String p_name;//项目名
    private String title;//项目标题
    private String content;//项目内容
    private List<String> photos;//图片
    private String cc;//抄送人
    private String opinion;//意见

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getDate() {
        return "2018-5-9";
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}

