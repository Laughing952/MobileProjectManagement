package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.response.JoinPeopleRep;
import com.unistrong.working.ui.mvpview.JoinPeopleView;
import com.unistrong.working.ui.viewmodel.JoinPeopleVM;
import com.waterbase.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class JoinPeoplePresenter {
    private JoinPeopleView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private List<JoinPeopleVM> vmList;

    public JoinPeoplePresenter(JoinPeopleView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
//        if (view.getId() == R.id.rl_create_project_member) {
//            //项目成员
//            A_Project_Member.startActivity(activity);
//
//        }
    }

    /**
     * @param mJoinPeopleRep
     */
    public void initData(ArrayList<JoinPeopleRep> mJoinPeopleRep) {
        vmList = new ArrayList<>();

        vmList.add(new JoinPeopleVM("1", R.mipmap.light_on, "发布公告", false));
        vmList.add(new JoinPeopleVM("2", R.mipmap.light_on, "创建项目", false));
        vmList.add(new JoinPeopleVM("3", R.mipmap.light_on, "合同管理", false));
        vmList.add(new JoinPeopleVM("4", R.mipmap.light_on, "报销管理", false));
        vmList.add(new JoinPeopleVM("5", R.mipmap.light_on, "记账管理", false));
        vmList.add(new JoinPeopleVM("6", R.mipmap.light_on, "发票管理", false));
        mView.showData(vmList);
    }
}
