package com.project.ui.presenter;

import android.view.View;

import com.project.R;
import com.project.ui.activity.A_Classification_Management;
import com.project.ui.activity.A_In_Stock;
import com.project.ui.activity.A_Outbound_Records;
import com.project.ui.activity.A_Storage_Records;
import com.project.ui.mvpview.MaterialManagementView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

/**
 * 项目-物资（管理）MaterialManagement
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class MaterialManagementrPresenter {
    private MaterialManagementView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public MaterialManagementrPresenter(MaterialManagementView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
        if (view.getId() == R.id.rl_a_material_management_in_stock) {
            //库存
            A_In_Stock.startActivity(activity, mView.getPId(), A_In_Stock.SAVE_STOCK);

        } else if (view.getId() == R.id.rl_a_material_management_storage_records) {
            //入库记录
            A_Storage_Records.startActivity(activity);

        } else if (view.getId() == R.id.rl_a_material_management_outbound_records) {
            //出库记录
            A_Outbound_Records.startActivity(activity);

        } else if (view.getId() == R.id.rl_a_material_management_classification_management) {
            //分类管理
            A_Classification_Management.startActivity(activity);

        } else if (view.getId() == R.id.ll_outstock) {
            //出库
            A_In_Stock.startActivity(activity, mView.getPId(), A_In_Stock.OUT_STOCK);
        }
    }


}
