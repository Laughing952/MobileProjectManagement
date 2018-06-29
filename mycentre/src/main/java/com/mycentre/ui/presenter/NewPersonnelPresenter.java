package com.mycentre.ui.presenter;

import android.view.View;

import com.global.listener.ItemClickListener;
import com.mycentre.R;
import com.mycentre.request.PersonnelManagementReq;
import com.mycentre.response.DivisionalManagementRep;
import com.mycentre.response.DutyRep;
import com.mycentre.response.JurisdictionRep;
import com.mycentre.response.PersonnelDetailsRep;
import com.mycentre.ui.activity.A_New_Personnel;
import com.mycentre.ui.adapter.Adapter_Divisional_Management;
import com.mycentre.ui.adapter.Adapter_Duty_Sel;
import com.mycentre.ui.mvpview.NewPersonnelView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;
import com.waterbase.widget.ListDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 新建/编辑人员
 * Created by Water on 2018/5/8.
 */

public class NewPersonnelPresenter {

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private NewPersonnelView mView;


    private PersonnelManagementReq req; // 修改或者新增的数据
    private List<DivisionalManagementRep> divisionalManagementRepList; // 部门列表
    private List<DutyRep> dutyRepList; // 职务列表

    public NewPersonnelPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, NewPersonnelView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        req = new PersonnelManagementReq();
    }

    /**
     * 获取人员详情
     *
     * @param userId
     */
    public void getPersonnel(String userId) {
        // TODO: 2018/5/9 获取人员详情

        PersonnelDetailsRep detailsRep = new PersonnelDetailsRep();
        detailsRep.setUserID(userId);
        detailsRep.setUserName("张三");
        detailsRep.setDivisionalId("001");
        detailsRep.setDivisionalName("办公室");
        detailsRep.setDutyID("1");
        detailsRep.setDutyName("总经理");
        List<JurisdictionRep> jurisdictionReps1 = new ArrayList<>();
        jurisdictionReps1.add(new JurisdictionRep("1", "发布公告"));
        jurisdictionReps1.add(new JurisdictionRep("2", "创建项目"));
        jurisdictionReps1.add(new JurisdictionRep("3", "合同管理"));
        detailsRep.setJurisdictionRepList(jurisdictionReps1);
        //-----------------------------------
        mView.callbackName(detailsRep.getUserName());
        mView.selDivisionalResult(detailsRep.getDivisionalName());
        mView.selDutyResult(detailsRep.getDutyName());
        mView.selDutyJurisdictionResult(detailsRep.getJurisdictionRepList());
        req.setName(detailsRep.getUserName());
        req.setDepartmentId(detailsRep.getDivisionalId());
        req.setDutyId(detailsRep.getDutyID());
    }


    public void request(String name, int flag) {
        if (flag == A_New_Personnel.NEW) {
            // TODO: 2018/5/9 新增
            req.setName(name);


            mView.responseAdd();
        } else if (flag == A_New_Personnel.UPDATA) {
            // TODO: 2018/5/9 修改
            req.setName(name);


            mView.responseUpdata();

        }
    }

    public void click(View v) {
        if (v.getId() == R.id.rv_department) {
            // 部门
            selDepartmen();
        } else if (v.getId() == R.id.rv_duty) {
            // 职务
            selduty();
        }
    }


    /**
     * 选择上级部门
     */
    private void selDepartmen() {
        // todo 从网络获取数据
        if (divisionalManagementRepList == null) {
            divisionalManagementRepList = new ArrayList<>();
            divisionalManagementRepList.add(new DivisionalManagementRep("10001", "办公室1级1", null, 0));
            divisionalManagementRepList.add(new DivisionalManagementRep("11001", "办公室2级1", "10001", 1));
            divisionalManagementRepList.add(new DivisionalManagementRep("11101", "办公室3级1", "11001", 2));
            divisionalManagementRepList.add(new DivisionalManagementRep("11111", "办公室4级1", "11101", 3));
            divisionalManagementRepList.add(new DivisionalManagementRep("11102", "办公室3级2", "11001", 2));
            divisionalManagementRepList.add(new DivisionalManagementRep("11002", "办公室2级2", "10001", 1));
            divisionalManagementRepList.add(new DivisionalManagementRep("10002", "办公室1级2", null, 0));
            divisionalManagementRepList.add(new DivisionalManagementRep("10001", "办公室1级1", null, 0));
            divisionalManagementRepList.add(new DivisionalManagementRep("11001", "办公室2级1", "10001", 1));
            divisionalManagementRepList.add(new DivisionalManagementRep("11101", "办公室3级1", "11001", 2));
            divisionalManagementRepList.add(new DivisionalManagementRep("11111", "办公室4级1", "11101", 3));
            divisionalManagementRepList.add(new DivisionalManagementRep("11102", "办公室3级2", "11001", 2));
            divisionalManagementRepList.add(new DivisionalManagementRep("11002", "办公室2级2", "10001", 1));
            divisionalManagementRepList.add(new DivisionalManagementRep("10002", "办公室1级2", null, 0));
        }
        Adapter_Divisional_Management adapterDivisionalManagement = new Adapter_Divisional_Management(true);
        adapterDivisionalManagement.setData(divisionalManagementRepList);
        ListDialog listDialog = new ListDialog.Builder(activity)
                .setTitle("请选择")
                .setAdapter(adapterDivisionalManagement)
                .create();
        listDialog.show();
        adapterDivisionalManagement.setItemClickListener(new ItemClickListener<DivisionalManagementRep>() {
            @Override
            public void itemClick(View v, DivisionalManagementRep rep, int index) {
                req.setDepartmentId(rep.getId());
                mView.selDivisionalResult(rep.getContent());
                listDialog.dismiss();
            }
        });
    }

    /**
     * 选择职务
     */
    private void selduty() {
        if (dutyRepList == null) {
            dutyRepList = new ArrayList<>();
            List<JurisdictionRep> jurisdictionReps = new ArrayList<>();
            jurisdictionReps.add(new JurisdictionRep("1", "发布公告"));
            jurisdictionReps.add(new JurisdictionRep("2", "创建项目"));
            jurisdictionReps.add(new JurisdictionRep("3", "合同管理"));
            jurisdictionReps.add(new JurisdictionRep("4", "报销管理"));
            jurisdictionReps.add(new JurisdictionRep("5", "记账管理"));
            dutyRepList.add(new DutyRep("001", "总经理", jurisdictionReps));
            List<JurisdictionRep> jurisdictionReps1 = new ArrayList<>();
            jurisdictionReps1.add(new JurisdictionRep("1", "发布公告"));
            jurisdictionReps1.add(new JurisdictionRep("2", "创建项目"));
            jurisdictionReps1.add(new JurisdictionRep("3", "合同管理"));
            dutyRepList.add(new DutyRep("002", "总经理助理", jurisdictionReps1));
        }

        Adapter_Duty_Sel adapterDutySel = new Adapter_Duty_Sel();
        adapterDutySel.setData(dutyRepList);
        ListDialog listDialog = new ListDialog.Builder(activity)
                .setTitle("请选择")
                .setAdapter(adapterDutySel)
                .create();
        listDialog.show();
        adapterDutySel.setItemClickListener(new ItemClickListener<DutyRep>() {
            @Override
            public void itemClick(View v, DutyRep rep, int index) {
                req.setDutyId(rep.getDutyID());
                mView.selDutyResult(rep.getName());
                mView.selDutyJurisdictionResult(rep.getJurisdictionRepList());
                listDialog.dismiss();
            }
        });
    }

}
