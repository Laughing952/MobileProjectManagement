package com.mycentre.ui.presenter;

import com.mycentre.R;
import com.mycentre.request.PersonnelManagementReq;
import com.mycentre.response.DivisionalManagementRep;
import com.mycentre.response.DutyRep;
import com.mycentre.response.JurisdictionRep;
import com.mycentre.ui.activity.A_New_Duty;
import com.mycentre.ui.mvpview.NewDutyView;
import com.mycentre.ui.mvpview.NewPersonnelView;
import com.mycentre.ui.viewmodel.JurisdictionVM;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 新建/修改职务
 * Created by Water on 2018/5/9.
 */

public class NewDutyPresenter {

    private static final String TAG = "NewDutyPresenter";

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private NewDutyView mView;
    private List<JurisdictionVM> vmList;

    public NewDutyPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, NewDutyView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void initData(DutyRep dutyRep) {
        vmList = new ArrayList<>();
        vmList.add(new JurisdictionVM("1", R.mipmap.light_on, "发布公告", false));
        vmList.add(new JurisdictionVM("2", R.mipmap.light_on, "创建项目", false));
        vmList.add(new JurisdictionVM("3", R.mipmap.light_on, "合同管理", false));
        vmList.add(new JurisdictionVM("4", R.mipmap.light_on, "报销管理", false));
        vmList.add(new JurisdictionVM("5", R.mipmap.light_on, "记账管理", false));
        vmList.add(new JurisdictionVM("6", R.mipmap.light_on, "发票管理", false));
        if (dutyRep == null) {
            mView.showData(vmList);
            return;
        } else {
            for (JurisdictionRep rep : dutyRep.getJurisdictionRepList()) {
                for (JurisdictionVM vm : vmList) {
                    if (rep.getJurisdictionId().equals(vm.getJurisdictionId())) {
                        vm.setSel(true);
                        break;
                    }
                }
            }
            for (JurisdictionVM vm : vmList) {
                LogUtil.d(TAG, "VM: " + vm.isSel());
            }
            mView.showData(vmList);
            mView.showName(dutyRep.getName());
        }
    }

    public void initData() {
        initData(null);
    }

    public void updataDuty(String dutyName) {

        mView.updataResponse();
    }

    public void request(String dutyName, int flag) {
        if (StrUtil.isEmpty(dutyName)) {
            ToastUtil.showToast(activity, "请填写职务名称");
            return;
        }
        for (JurisdictionVM vm : vmList) {
            if (vm.isSel()) {
                LogUtil.d(TAG, vm.getJurisdictionName());
            }
        }
        if (flag == A_New_Duty.NEW) {
            mView.addResponse();
        } else if (flag == A_New_Duty.UPDATA) {
            mView.updataResponse();
        }
    }
}
