package com.unistrong.working.ui.presenter;

import android.view.View;

import com.global.listener.ItemClickListener;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.ui.adapter.Adapter_Create_New_Task_Performer;
import com.unistrong.working.ui.mvpview.SentToMeDetailsView;
import com.unistrong.working.ui.viewmodel.SentToMeDetailsVM;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;
import com.waterbase.widget.ListDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 派给我的-详情
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class SentToMeDetailsPresenter {
    private SentToMeDetailsView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private final HttpManager httpManager;

    public SentToMeDetailsPresenter(SentToMeDetailsView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        httpManager = new HttpManager(activity, lifecycleProvider);
    }

    public void click(View view) {
        if (view.getId() == R.id.tv_sent_to_me_details_send) {
            //发送任务动态

            mView.sendTaskDynamic();

        }
    }

    /**
     * 工作任务详细信息获取
     */
    public void getWorkTaskInfo(String taskId) {
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getWorkTaskInfo(taskId),
                new DefaultObserver<SentToMeDetailsVM>() {
                    @Override
                    public void onSuccess(SentToMeDetailsVM response) {
                        mView.resultWorkTaskInfo(response);
                    }
                });
    }

    /**
     * 发送工作任务消息
     * */
    public void sendMessageInfo(SentToMeDetailsVM sentToMeDetailsVM) {
        httpManager.doHttpDeal(RetrofitHelper.getApiService().sendMessageInfo(sentToMeDetailsVM),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.refreshData();
                    }
                });
    }

    /**
     * 选择执行人 CreateNewTask_PerformerRep
     */
    public void selectJoinPeople() {
        List<CreateNewTask_PerformerRep> repList = new ArrayList<>();
        repList.add(new CreateNewTask_PerformerRep(10001L, "Laughing1"));
        repList.add(new CreateNewTask_PerformerRep(10002L, "Laughing2"));
        repList.add(new CreateNewTask_PerformerRep(10002L, "Laughing3"));
        repList.add(new CreateNewTask_PerformerRep(10002L, "Laughing4"));
        repList.add(new CreateNewTask_PerformerRep(10002L, "Laughing5"));

        Adapter_Create_New_Task_Performer adapter_create_new_task_performer = new Adapter_Create_New_Task_Performer();

        adapter_create_new_task_performer.setData(repList);
        ListDialog listDialog = new ListDialog.Builder(activity)
                .setTitle("请选择执行人")
                .setAdapter(adapter_create_new_task_performer)
                .create();
        listDialog.show();
        adapter_create_new_task_performer.setItemClickListener(new ItemClickListener<CreateNewTask_PerformerRep>() {
            @Override
            public void itemClick(View v, CreateNewTask_PerformerRep rep, int index) {
                mView.setJoinPeopleResult(rep);
                listDialog.dismiss();
            }
        });
    }

    /**
     * 取消任务
     *
     * @param taskId
     */
    public void doCancleTask(String taskId) {
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadCancleTask(taskId),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        ToastUtil.showToast(activity, "click--取消任务成功->");
                        mView.doCancelTaskSuccess(response);
                    }
                });
    }
}
