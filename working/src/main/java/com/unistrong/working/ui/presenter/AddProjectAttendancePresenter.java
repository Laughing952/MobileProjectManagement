package com.unistrong.working.ui.presenter;

import android.support.annotation.NonNull;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.request.AddCustomAttendanceReq;
import com.unistrong.working.response.CreateNewTask_ProjectNameRep;
import com.unistrong.working.ui.adapter.Adapter_Add_Project_Attendance;
import com.unistrong.working.ui.mvpview.AddProjectAttendanceView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;
import com.waterbase.widget.ListDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 新建——项目考勤点
 * 作者：Laughing on 2018/5/22 15:42
 * 邮箱：719240226@qq.com
 */

public class AddProjectAttendancePresenter {
    private AddProjectAttendanceView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private List<CreateNewTask_ProjectNameRep> mResponse = new ArrayList<>();

    public AddProjectAttendancePresenter(AddProjectAttendanceView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
        if (view.getId() == R.id.rl_add_project_attendance_choose_project) {
            //选择项目名作为考勤名字
            selectPerformer();

        } else if (view.getId() == R.id.iv_add_project_attendance1) {
            //星期一的时间
            choosePunchTime(1);

        } else if (view.getId() == R.id.iv_add_project_attendance2) {
            //星期二的时间
            choosePunchTime(2);


        } else if (view.getId() == R.id.iv_add_project_attendance3) {
            //星期三的时间
            choosePunchTime(3);


        } else if (view.getId() == R.id.iv_add_project_attendance4) {
            //星期四的时间
            choosePunchTime(4);


        } else if (view.getId() == R.id.iv_add_project_attendance5) {
            //星期五的时间
            choosePunchTime(5);


        } else if (view.getId() == R.id.iv_add_project_attendance6) {
            //星期六的时间
            choosePunchTime(6);


        } else if (view.getId() == R.id.iv_add_project_attendance7) {
            //星期日的时间
            choosePunchTime(7);


        } else if (view.getId() == R.id.rl_add_project_attendance_address) {
            //考勤地点

            mView.jumpAndGetData();
        } else if (view.getId() == R.id.rl_add_project_attendance_range) {
            //有效考勤范围
            mView.setRange();

        }
    }

    /**
     * @param week 星期几 传入 1到7 分别对应周一到周天
     */
    private void choosePunchTime(int week) {

        mView.chooseCommuteTime(week);//选择上下班时间

    }


    /**
     * 设置项目名到TextView上
     */
    private void selectPerformer() {
        Adapter_Add_Project_Attendance adapter = new Adapter_Add_Project_Attendance();
        // TODO: 2018/5/25 本地数据与网络数据 切换
//        List<ProjectRep> repList = initList();
//        adapter.setData(repList);
        adapter.setData(mResponse);//从网络获取项目列表
        ListDialog listDialog = new ListDialog.Builder(activity)
                .setTitle("请选择")
                .setAdapter(adapter)
                .create();
        listDialog.show();
        adapter.setItemClickListener(new ItemClickListener<CreateNewTask_ProjectNameRep>() {
            @Override
            public void itemClick(View v, CreateNewTask_ProjectNameRep rep, int index) {
                mView.setPerformerResult(rep);
                listDialog.dismiss();
            }
        });
    }

    @NonNull
    private List<CreateNewTask_ProjectNameRep> initList() {
        List<CreateNewTask_ProjectNameRep> repList = new ArrayList<>();
        repList.add(new CreateNewTask_ProjectNameRep("123", "项目xxxxxxxxxxxx1"));
        repList.add(new CreateNewTask_ProjectNameRep("124", "项目xxxxxxxxxxxx2"));
        repList.add(new CreateNewTask_ProjectNameRep("125", "项目xxxxxxxxxxxx3"));
        repList.add(new CreateNewTask_ProjectNameRep("126", "项目xxxxxxxxxxxx4"));
        repList.add(new CreateNewTask_ProjectNameRep("126", "项目xxxxxxxxxxxx5"));
        return repList;
    }


    /**
     * 上传考勤数据到服务器
     *
     * @param addCustomAttendanceReq
     */
    public void uploadData(AddCustomAttendanceReq addCustomAttendanceReq) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadCustomAttendanceData(addCustomAttendanceReq),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        ToastUtil.showToast(activity, "新考勤点成功");
                        mView.uploadDataSuccess();
                    }
                });
    }

    /**
     * 通过 userId来获取与其关联的项目
     *
     * @param userId
     */
    public void getProjectList(Long userId) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getProjectInfo(userId),
                new DefaultObserver<List<CreateNewTask_ProjectNameRep>>() {
                    @Override
                    public void onSuccess(List<CreateNewTask_ProjectNameRep> response) {
                        mResponse = response;
                    }
                });
    }
}