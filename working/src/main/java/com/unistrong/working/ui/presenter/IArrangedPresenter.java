package com.unistrong.working.ui.presenter;

import android.view.View;

import com.global.util.PickerViewUtil;
import com.global.util.RequestTransformUtil;
import com.global.util.UserIdUtil;
import com.global.viewmodel.TaskIsReadViewModel;
import com.global.viewmodel.TaskStateViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.bean.IArrangedBean;
import com.unistrong.working.bean.SentToMeBean;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.mvpview.IArrangedView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class IArrangedPresenter {
    private IArrangedView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private long userId;
    private final HttpManager httpManager;
    private Map<String, Object> map;
    private int currentPage = 1;
    private int pageSize = 10;
    private String status;

    public IArrangedPresenter(IArrangedView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        httpManager = new HttpManager(activity, lifecycleProvider);
        userId= UserIdUtil.getUserIdLong();
    }

    public void click(View view) {
        if (view.getId() == R.id.tv_i_arranged_is_read) {
            //查看全部/查看未阅读
            chooseHoleOrRead();
        } else if (view.getId() == R.id.tv_i_arranged_state) {
            //已完成的项目
            chooseState();
        }
    }

    //  查看全部/查看未阅读
    private void chooseHoleOrRead() {
        ArrayList<TaskIsReadViewModel> readViewModels = RequestTransformUtil.initTaskReadData();
        PickerViewUtil.showOptionsPickerView(activity, "选择任务查看类型", new PickerViewUtil.SelectResultListener<TaskIsReadViewModel>() {
            @Override
            public void result(TaskIsReadViewModel model) {
                mView.seeAllOrRead2View(model.getRead());
            }

        }, readViewModels);
    }

    //  任务状态
    private void chooseState() {
        ArrayList<TaskStateViewModel> stateViewModels = RequestTransformUtil.initTaskStateData();
        PickerViewUtil.showOptionsPickerView(activity, "选择任务状态", new PickerViewUtil.SelectResultListener<TaskStateViewModel>() {
            @Override
            public void result(TaskStateViewModel model) {
                status=model.getId();
                getIArrangedListData();
                mView.setState2View(model.getState());
            }

        }, stateViewModels);
    }

    public void getIArrangedListData(){
        map = new HashMap<>();
        currentPage = 1;
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("userId", userId);
        if (status!=null){
            map.put("status",status);
        }
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getIArrangedList(map),
                new DefaultObserver<List<IArrangedBean>>() {
                    @Override
                    public void onSuccess(List<IArrangedBean> response) {
                        mView.resultIArrangedListData(response);
                    }
                });
    }

    /**
     * 加载更多
     */
    public void loadMoreData() {
        map = new HashMap<>();
        currentPage++;
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("userId",userId);
        if (status!=null){
            map.put("status",status);
        }
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getIArrangedList(map),
                new DefaultObserver<List<IArrangedBean>>() {
                    @Override
                    public void onSuccess(List<IArrangedBean> response) {
                        mView.loadMoreResult(response);
                    }
                });
    }
}
