package com.unistrong.working.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.response.PunchRecordRep;
import com.unistrong.working.ui.mvpview.PunchRecordView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 打卡记录
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class PunchRecordPresenter {
    private PunchRecordView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private int currentPage = 1;
    private int pageSize = 10;
    private HttpManager httpManager;

    public PunchRecordPresenter(PunchRecordView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
//        if (view.getId() == R.id.ll_working_general_approval) {
//            //通用申请
//            A_General_Approval.startActivity(activity);
//        }
    }


    /**
     * 从服务器获取打卡记录数据
     *
     * @param userId
     */
    public void downloadData(Long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().downloadPunchRecordData(map),
                new DefaultObserver<List<PunchRecordRep>>() {
                    @Override
                    public void onSuccess(List<PunchRecordRep> response) {

                        if (currentPage == 1)
                            mView.initRepData(response);
                        else
                            mView.loadMoreResult(response);
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
        downloadData(userId);
    }

    public void loadMoreData(Long userId) {
        currentPage++;
        downloadData(userId);
        LogUtil.e("TAG", "laughing-------------------currentPage--->   " + currentPage);
    }
}
