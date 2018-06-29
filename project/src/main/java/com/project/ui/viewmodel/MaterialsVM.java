package com.project.ui.viewmodel;

import com.project.response.MaterialsRep;

/**
 * 物资数据
 * Created by Water on 2018/5/22.
 */

public class MaterialsVM {

    private MaterialsRep materialsRep;

    private String materialsName; // 物资名称
    private String standardName; // 物资规格
    private String companyName; // 单位名称

    private String inNum; // 入库量
    private String outNum;// 出库量
    private String saveNum;// 库存量


    private String encoding;// 编码
    private String classifyeName; // 分类名称
    private String remark;//备注

    public MaterialsVM(MaterialsRep materialsRep) {
        this.materialsRep = materialsRep;
    }

    public String getMaterialsName() {
        return materialsRep.getMaterialsName();
    }

    public String getStandardName() {
        return materialsRep.getStandardName();
    }

    public String getCompanyName() {
        return materialsRep.getCompanyName();
    }

    public String getInNum() {
        return materialsRep.getInNum();
    }

    public String getOutNum() {
        return materialsRep.getOutNum();
    }

    public String getSaveNum() {
        return materialsRep.getSaveNum();
    }

    public String getEncoding() {
        return materialsRep.getEncoding();
    }

    public String getClassifyeName() {
        return materialsRep.getClassifyeName();
    }

    public String getRemark() {
        return materialsRep.getRemark();
    }
}
