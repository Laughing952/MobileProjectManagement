package com.mycentre.ui.presenter;

import com.mycentre.ui.mvpview.PersonnelManagementView;
import com.mycentre.response.PersonnelManagementRep;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 人员管理
 * Created by Water on 2018/5/8.
 */

public class PersonnelManagementPresenter {

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private PersonnelManagementView mView;

    public PersonnelManagementPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider
            , PersonnelManagementView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void request() {
        List<PersonnelManagementRep> repList = new ArrayList<>();
        repList.add(new PersonnelManagementRep("001", "张三"
                , "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2919820672,1724019928&fm=27&gp=0.jpg"
                , "总经理"));
        repList.add(new PersonnelManagementRep("002", "李四"
                , "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2919820672,1724019928&fm=27&gp=0.jpg"
                , "总经理助理"));
        repList.add(new PersonnelManagementRep("003", "王五"
                , "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2919820672,1724019928&fm=27&gp=0.jpg"
                , "总经理秘书"));
        repList.add(new PersonnelManagementRep("004", "赵六"
                , "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2919820672,1724019928&fm=27&gp=0.jpg"
                , "总经理司机"));
        mView.response(repList);
    }

    public void del(PersonnelManagementRep rep) {
        // TODO: 2018/5/8 删除
        mView.delSucceed(rep);
    }
}
