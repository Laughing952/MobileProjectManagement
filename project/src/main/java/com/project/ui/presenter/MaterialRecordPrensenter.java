package com.project.ui.presenter;

import com.project.api.RetrofitHelper;
import com.project.response.MaterialRecordRep;
import com.project.response.MaterialsRep;
import com.project.ui.mvpview.InStockView;
import com.project.ui.mvpview.MaterialRecordView;
import com.project.ui.viewmodel.ClassifyeVM;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 物资流水
 * Created by Water on 2018/5/23.
 */

public class MaterialRecordPrensenter {

    private static final String TAG = "MaterialRecordPrensenter";

    private MaterialRecordView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public MaterialRecordPrensenter(MaterialRecordView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    /**
     * 从服务器获取数据
     *
     * @param materialsID 物资ID
     */
    public void initData(String materialsID) {
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().materialRecord(materialsID)
                , new DefaultObserver<MaterialsRep>() {
                    @Override
                    public void onSuccess(MaterialsRep rep) {
                        mView.responseData(rep);
                    }
                });
//        List<MaterialRecordRep> recordRepList = new ArrayList<>();
//        recordRepList.add(new MaterialRecordRep("2018-9-12", "刘启", "项目1", "13", 0));
//        recordRepList.add(new MaterialRecordRep("2018-9-12", "袁超", "项目2", "3", 1));
//        recordRepList.add(new MaterialRecordRep("2018-9-12", "崔浩", "项目2", "23", 0));
//        recordRepList.add(new MaterialRecordRep("2018-9-12", "白素贞", "项目3", "16", 1));
//        MaterialsRep rep = new MaterialsRep("分类名称", "物资名称", "规格"
//                , "单位", "编码", "备注", "12", "8", "4"
//                , recordRepList);
//        mView.responseData(rep);
    }
}
