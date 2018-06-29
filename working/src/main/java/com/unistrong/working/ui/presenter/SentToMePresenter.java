package com.unistrong.working.ui.presenter;

import android.app.Activity;
import android.view.View;

import com.global.util.PickerViewUtil;
import com.global.util.RequestTransformUtil;
import com.global.util.UserIdUtil;
import com.global.viewmodel.TaskPriorityViewModel;
import com.global.viewmodel.TaskStateViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.bean.SentToMeBean;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.mvpview.SentToMeView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 派给我的
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class SentToMePresenter {
    private SentToMeView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private long userId;
    private HttpManager httpManager;
    private Map<String, Object> map;
    private int currentPage = 1;
    private int pageSize = 10;
    private String status;

    public SentToMePresenter(SentToMeView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        httpManager = new HttpManager(activity, lifecycleProvider);
        userId=UserIdUtil.getUserIdLong();
    }

    public void click(View view, Activity activity2) {
        if (view.getId() == R.id.tv_sent_to_me_priority) {
            //任务优先级
            choosePriority(activity2);
        } else if (view.getId() == R.id.tv_sent_to_me_state) {
            //任务状态
            chooseState(activity2);

        }
    }

    public void getSentToMeListData(){
        map=new HashMap<>();
        currentPage = 1;
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("userId", userId);
        if (status!=null){
            map.put("status",status);
        }
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getSendToMeList(map),
                new DefaultObserver<List<SentToMeBean>>() {
                    @Override
                    public void onSuccess(List<SentToMeBean> response) {
                        mView.resultSendToMeListData(response);
                    }
                });
    }
    /**
     * 加载更多
     */
    public void loadMoreData() {
        map=new HashMap<>();
        currentPage++;
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("userId",userId);
        if (status!=null){
            map.put("status",status);
        }
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getSendToMeList(map),
                new DefaultObserver<List<SentToMeBean>>() {
                    @Override
                    public void onSuccess(List<SentToMeBean> response) {
                        mView.loadMoreResult(response);
                    }
                });
    }

    //  任务优先级
    private void choosePriority(Activity activity2) {
        ArrayList<TaskPriorityViewModel> priorityViewModels = RequestTransformUtil.initTaskPriorityData();
        PickerViewUtil.showOptionsPickerView(activity2, "选择任务优先级", new PickerViewUtil.SelectResultListener<TaskPriorityViewModel>() {
            @Override
            public void result(TaskPriorityViewModel model) {
                mView.setPriority2View(model);
            }

        }, priorityViewModels);
    }

    //  任务状态
    private void chooseState(Activity activity2) {
        ArrayList<TaskStateViewModel> stateViewModels = RequestTransformUtil.initTaskStateData();
        PickerViewUtil.showOptionsPickerView(activity2, "选择任务状态", new PickerViewUtil.SelectResultListener<TaskStateViewModel>() {
            @Override
            public void result(TaskStateViewModel model) {
                status=model.getId();
                getSentToMeListData();
                mView.setState2View(model.getState());
            }

        }, stateViewModels);
    }
}
