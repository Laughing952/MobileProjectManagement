package com.unistrong.baidumaplibrary.ui.presenter;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.baidumaplibrary.R;
import com.unistrong.baidumaplibrary.api.RetrofitHelper;
import com.unistrong.baidumaplibrary.rep.AttendanceSettingRep;
import com.unistrong.baidumaplibrary.ui.mvpview.ProjectDistributionView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 正在进行的项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class ProjectDistributionPresenter {
    private ProjectDistributionView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private HttpManager mHttpManager;

    public ProjectDistributionPresenter(ProjectDistributionView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View v) {
        // 5.	更新地图状态
        int i = v.getId();
        if (i == R.id.bt_1) {// 		1)	缩小
            mView.scaleMin();
        } else if (i == R.id.bt_2) {// 		2)	放大
            mView.scaleMax();
        } else if (i == R.id.bt_3) {// 		3)	旋转（0 ~ 360），每次在原来的基础上再旋转30度
            mView.getMapState();
        } else if (i == R.id.bt_4) {
            mView.upDown();// 		4)	俯仰（0 ~ -45），每次在原来的基础上再俯仰-5度
        } else if (i == R.id.bt_5) {
            mView.move();
        } else if (i == R.id.bt_normal) {
            //普通地图
            mView.normalMode();
        } else if (i == R.id.bt_traffic) {
            //交通地图
            mView.trafficMode();

        } else if (i == R.id.bt_satellite) {
            //卫星地图
            mView.satelliteMode();


        }

    }


    /**
     * 加载打卡地点列表
     */
    public void downLoadData(long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("currentPage", 1);
        map.put("pageSize", 10);
        mHttpManager = new HttpManager(activity, lifecycleProvider);
        mHttpManager.doHttpDeal(RetrofitHelper.getApiService().uploadAttendanceListData(map),
                new DefaultObserver<List<AttendanceSettingRep>>() {
                    @Override
                    public void onSuccess(List<AttendanceSettingRep> response) {
                        //进入打卡页面发送网络请求考勤点信息
                        mView.backData(response);
                    }
                });
    }
}
