package com.mycentre.ui.presenter;

import android.view.View;

import com.global.listener.ItemClickListener;
import com.mycentre.ui.activity.A_New_Department;
import com.mycentre.ui.adapter.Adapter_Divisional_Management;
import com.mycentre.ui.mvpview.NewDepartmentView;
import com.mycentre.response.DivisionalManagementRep;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;
import com.waterbase.widget.ListDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Water on 2018/5/8.
 */

public class NewDepartmentPresenter {

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private NewDepartmentView mView;

    public NewDepartmentPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, NewDepartmentView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    /**
     * 选择上级部门
     */
    public void selSuperiorDepartmen() {
        List<DivisionalManagementRep> repList = new ArrayList<>();
        repList.add(new DivisionalManagementRep("10001", "办公室1级1", null, 0));
        repList.add(new DivisionalManagementRep("11001", "办公室2级1", "10001", 1));
        repList.add(new DivisionalManagementRep("11101", "办公室3级1", "11001", 2));
        repList.add(new DivisionalManagementRep("11111", "办公室4级1", "11101", 3));
        repList.add(new DivisionalManagementRep("11102", "办公室3级2", "11001", 2));
        repList.add(new DivisionalManagementRep("11002", "办公室2级2", "10001", 1));
        repList.add(new DivisionalManagementRep("10002", "办公室1级2", null, 0));
        repList.add(new DivisionalManagementRep("10001", "办公室1级1", null, 0));
        repList.add(new DivisionalManagementRep("11001", "办公室2级1", "10001", 1));
        repList.add(new DivisionalManagementRep("11101", "办公室3级1", "11001", 2));
        repList.add(new DivisionalManagementRep("11111", "办公室4级1", "11101", 3));
        repList.add(new DivisionalManagementRep("11102", "办公室3级2", "11001", 2));
        repList.add(new DivisionalManagementRep("11002", "办公室2级2", "10001", 1));
        repList.add(new DivisionalManagementRep("10002", "办公室1级2", null, 0));
        Adapter_Divisional_Management adapterDivisionalManagement = new Adapter_Divisional_Management(true);

        adapterDivisionalManagement.setData(repList);
        ListDialog listDialog = new ListDialog.Builder(activity)
                .setTitle("请选择")
                .setAdapter(adapterDivisionalManagement)
                .create();
        listDialog.show();
        adapterDivisionalManagement.setItemClickListener(new ItemClickListener<DivisionalManagementRep>() {
            @Override
            public void itemClick(View v, DivisionalManagementRep rep, int index) {
                mView.selResult(rep);
                listDialog.dismiss();
            }

        });
    }

    public void request(DivisionalManagementRep parendRep, DivisionalManagementRep childRep, int flag) {
        if (flag == A_New_Department.NEW) {
            // TODO: 2018/5/8 新建
            ToastUtil.showToast(activity, "新建成功");
        } else if (flag == A_New_Department.UPDATA) {
            // TODO: 2018/5/8 修改
            ToastUtil.showToast(activity, "修改成功");
        }
        mView.response(null);
    }
}
