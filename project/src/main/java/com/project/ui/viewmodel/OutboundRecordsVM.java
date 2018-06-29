package com.project.ui.viewmodel;

/**
 * 作者：Laughing on 2018/5/18 15:25
 * 邮箱：719240226@qq.com
 */

public class OutboundRecordsVM {
    private String R_date;//    出库日期
    private String R_person;//  领料人
    private String R_item;//    有几项
    private String R_id;//      出库记录Id
    private String R_personId;//领料人Id



    public String getR_date() {
        return R_date;
    }

    public void setR_date(String r_date) {
        R_date = r_date;
    }

    public String getR_person() {
        return R_person;
    }

    public void setR_person(String r_person) {
        R_person = r_person;
    }

    public String getR_item() {
        return R_item;
    }

    public void setR_item(String r_item) {
        R_item = r_item;
    }

    public String getR_id() {
        return R_id;
    }

    public void setR_id(String r_id) {
        R_id = r_id;
    }

    public String getR_personId() {
        return R_personId;
    }

    public void setR_personId(String r_personId) {
        R_personId = r_personId;
    }
}
