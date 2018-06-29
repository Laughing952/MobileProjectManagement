package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.response.AttendanceSettingRep;
import com.unistrong.working.ui.mvpview.AttendanceSettingView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考勤设置
 * 作者：Laughing on 2018/5/22 16:52
 * 邮箱：719240226@qq.com
 */

public class AttendanceSettingPresenter {
    private AttendanceSettingView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private int currentPage = 1;
    private int pageSize = 10;

    public AttendanceSettingPresenter(AttendanceSettingView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
        //        if (view.getId() == R.id.rl_project_program_doing) {
        //            //正在执行的项目
        //            A_Project_Doing.startActivity(activity);
        //        } else if (view.getId() == R.id.rl_project_program_over) {
        //            //已完成的项目
        //            A_Project_Over.startActivity(activity);
        //
        //        }
    }


    /**
     * 获取已添加的考勤列表
     *
     * @param userId 该用户关联下的考勤列表
     */
    public void getAttendanceList(Long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadAttendanceListData(map),
                new DefaultObserver<List<AttendanceSettingRep>>() {
                    @Override
                    public void onSuccess(List<AttendanceSettingRep> response) {

                        if (currentPage == 1)
                            mView.loadData(response);
                        else
                            mView.loadMoreData(response);
                    }
                });
    }

    /**
     * 下拉刷新
     *
     * @param userId
     */
    public void refData(Long userId) {
        currentPage = 1;
        getAttendanceList(userId);
    }

    /**
     * 加载更多
     *
     * @param userId
     */
    public void loadMoreData(Long userId) {
        currentPage++;
        getAttendanceList(userId);
        LogUtil.e("TAG", "laughing-------------------currentPage--->   " + currentPage);
    }
}
