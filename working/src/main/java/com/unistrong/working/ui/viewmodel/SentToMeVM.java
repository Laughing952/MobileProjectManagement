package com.unistrong.working.ui.viewmodel;

import com.unistrong.working.bean.SentToMeBean;
import com.waterbase.utile.DateUtil;

import java.util.List;

/**
 * 派给我的
 * 作者：Laughing on 2018/5/9 17:22
 * 邮箱：719240226@qq.com
 */

public class SentToMeVM {
    private String p_name;//项目名
    private String task_name;//任务
    private String task_id;//项目Id
    private String task_describe;//任务描述
    private List<String> photos;//图片
    private String end_date;//截止时间
    private String performer;//执行人
    private String schedule;//任务进度


    private SentToMeBean sentToMeBean;//

    public SentToMeVM(SentToMeBean sentToMeBean) {
        this.sentToMeBean = sentToMeBean;
    }

    public String getSchedule() {
        return sentToMeBean.getSchedule()+"%";
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getP_name() {
        return sentToMeBean.getItemName();
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getTask_name() {
        return sentToMeBean.getTaskName();
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_id() {
        return sentToMeBean.getTaskId();
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_describe() {
        return task_describe;
    }

    public void setTask_describe(String task_describe) {
        this.task_describe = task_describe;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getEnd_date() {
        return DateUtil.getStringByFormat(sentToMeBean.getTimeLimit(),"MM.dd")+"截止";
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getPerformer() {
        return "执行人："+sentToMeBean.getTaskHeadName();
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }
}
