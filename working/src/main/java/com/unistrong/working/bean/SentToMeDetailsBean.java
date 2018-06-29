package com.unistrong.working.bean;

import java.util.List;

/**
 * 派给我的
 * 作者：Laughing on 2018/5/9 17:21
 * 邮箱：719240226@qq.com
 */

public class SentToMeDetailsBean {
    private String p_name;//项目名
    private String task_name;//任务
    private String p_id;//项目Id
    private String task_id;//项目Id
    private String content;//内容
    private List<String> photos;//图片
    private String date;//发布时间
    private String performer;//执行人
    private String des;//描述
    private String join_people;// 参与人

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getJoin_people() {
        return join_people;
    }

    public void setJoin_people(String join_people) {
        this.join_people = join_people;
    }
}
