package com.unistrong.working.ui.viewmodel;

import com.unistrong.working.bean.GeneralApprovalCcMeBean;

import java.util.List;

/**
 * 抄送我的
 * 作者：Laughing on 2018/5/10 13:42
 * 邮箱：719240226@qq.com
 */

public class CreateGeneralCcMeVM {
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

    private GeneralApprovalCcMeBean generalApprovalCcMeBean;//

    public CreateGeneralCcMeVM(GeneralApprovalCcMeBean generalApprovalCcMeBean) {
        this.generalApprovalCcMeBean = generalApprovalCcMeBean;
    }

    public String getName() {
        return "laughing";
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
        return "laughing";
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
        return "翠华路小学监控施工";
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getTitle() {
        return "监控施工";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return "周六周日进行施工，学生放假施工方便。";
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
        return "张升";
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getOpinion() {
        return "暂无意见";
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

}
