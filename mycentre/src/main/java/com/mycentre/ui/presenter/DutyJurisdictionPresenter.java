package com.mycentre.ui.presenter;

import com.mycentre.response.DutyRep;
import com.mycentre.response.JurisdictionRep;
import com.mycentre.ui.mvpview.DutyJurisdictionView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 职务权限
 * Created by Water on 2018/5/9.
 */

public class DutyJurisdictionPresenter {

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private DutyJurisdictionView mView;


    public DutyJurisdictionPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, DutyJurisdictionView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    /**
     * 从服务器获取数据
     */
    public void requestList() {
        // TODO: 2018/5/9 从服务器获取数据
        List<DutyRep> dutyRepList = new ArrayList<>();
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
        List<JurisdictionRep> jurisdictionReps2 = new ArrayList<>();
        jurisdictionReps2.add(new JurisdictionRep("1", "发布公告"));
        jurisdictionReps2.add(new JurisdictionRep("2", "创建项目"));
        jurisdictionReps2.add(new JurisdictionRep("3", "合同管理"));
        jurisdictionReps2.add(new JurisdictionRep("4", "报销管理"));
        jurisdictionReps2.add(new JurisdictionRep("5", "记账管理"));
        dutyRepList.add(new DutyRep("001", "总经理秘书", jurisdictionReps2));
        List<JurisdictionRep> jurisdictionReps3 = new ArrayList<>();
        jurisdictionReps3.add(new JurisdictionRep("1", "发布公告"));
        jurisdictionReps3.add(new JurisdictionRep("2", "创建项目"));
        jurisdictionReps3.add(new JurisdictionRep("3", "合同管理"));
        dutyRepList.add(new DutyRep("002", "总经理司机", jurisdictionReps3));
        //---------------------------------------------------
        mView.responseList(dutyRepList);
    }

    /**
     * 重新排序后的数据
     *
     * @param datas
     */
    public void newOrder(List<DutyRep> datas) {
        // TODO: 2018/5/9 告诉服务器新排序

        mView.orderReult();
    }

    /**
     * 删除职务
     *
     * @param dutyRep
     */
    public void del(DutyRep dutyRep) {
        // TODO: 2018/5/9 删除职务

        mView.delResult(dutyRep);
    }
}
