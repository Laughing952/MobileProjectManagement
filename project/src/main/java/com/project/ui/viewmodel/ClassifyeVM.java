package com.project.ui.viewmodel;

import com.project.response.MaterialsRep;

import java.util.ArrayList;
import java.util.List;

/**
 * 物资分类
 * Created by Water on 2018/5/22.
 */

public class ClassifyeVM {

    private String classifyeID; // 分类ID
    private String classifyeName; // 分类名称
    private List<MaterialsRep> materialsRepList;

    private boolean isCheck; // 是否选中

    public ClassifyeVM(String classifyeID, String classifyeName) {
        this.classifyeID = classifyeID;
        this.classifyeName = classifyeName;
    }

    public ClassifyeVM(String classifyeID, String classifyeName, List<MaterialsRep> materialsRepList, boolean isCheck) {
        this.classifyeID = classifyeID;
        this.classifyeName = classifyeName;
        this.materialsRepList = materialsRepList;
        this.isCheck = isCheck;
    }

    @Override
    public boolean equals(Object obj) {
        String id = (String) obj;
        return id.equals(classifyeID);
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

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public List<MaterialsRep> getMaterialsRepList() {
        if (materialsRepList == null)
            materialsRepList = new ArrayList<>();
        return materialsRepList;
    }

    public void setMaterialsRepList(List<MaterialsRep> materialsRepList) {
        this.materialsRepList = materialsRepList;
    }
}
