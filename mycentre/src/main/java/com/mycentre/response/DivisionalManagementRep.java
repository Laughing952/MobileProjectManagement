package com.mycentre.response;

import java.io.Serializable;

/**
 * 部门管理
 * Created by Water on 2018/5/8.
 */

public class DivisionalManagementRep implements Serializable{

    private String id;
    private String content;
    private String parendId;
    private int level;

    public DivisionalManagementRep() {
    }

    public DivisionalManagementRep(String id, String content, String parendId, int level) {
        this.id = id;
        this.content = content;
        this.parendId = parendId;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getParendId() {
        return parendId;
    }

    public void setParendId(String parendId) {
        this.parendId = parendId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
