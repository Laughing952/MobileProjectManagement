package com.unistrong.baidumaplibrary.ui.presenter;

import android.view.View;

import com.global.listener.ItemClickListener;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.baidumaplibrary.api.RetrofitHelper;
import com.unistrong.baidumaplibrary.rep.AttendanceSettingRep;
import com.unistrong.baidumaplibrary.req.AddCustomAttendanceReq;
import com.unistrong.baidumaplibrary.req.LocationPunchReq;
import com.unistrong.baidumaplibrary.ui.adapter.Adapter_Location;
import com.unistrong.baidumaplibrary.ui.mvpview.LocationView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;
import com.waterbase.widget.ListDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定位打卡
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class LocationPresenter {
    private LocationView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private HttpManager mHttpManager;
    private List<AttendanceSettingRep> mList;

    public LocationPresenter(LocationView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        mHttpManager = new HttpManager(activity, lifecycleProvider);
    }

    public void click(View v) {


    }

    /**
     * 加载打卡地点列表
     */
    public void downLoadData(long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("currentPage", 1);
        map.put("pageSize", 10);
        mHttpManager.doHttpDeal(RetrofitHelper.getApiService().uploadAttendanceListData(map),
                new DefaultObserver<List<AttendanceSettingRep>>() {
                    @Override
                    public void onSuccess(List<AttendanceSettingRep> response) {
                        //进入打卡页面发送网络请求考勤点信息
                        mView.backData(response);
                    }
                });
    }


    /**
     * 请选考勤点
     */
    public void selectPerformer(List<AttendanceSettingRep> repList) {
        Adapter_Location adapter = new Adapter_Location();
        adapter.setData(repList);
        ListDialog listDialog = new ListDialog.Builder(activity)
                .setTitle("请选考勤点")
                .setAdapter(adapter)
                .create();
        listDialog.show();
        adapter.setItemClickListener(new ItemClickListener<AttendanceSettingRep>() {
            @Override
            public void itemClick(View v, AttendanceSettingRep rep, int index) {
                mView.setPerformerResult(rep);
                listDialog.dismiss();

            }
        });
    }

    /**
     * 获取打卡点的位置信息（注意：不是定位出来的信息）
     *
     * @param mId
     */
    public void getPointInfo(int flag, String mId) {

        mHttpManager.doHttpDeal(RetrofitHelper.getApiService().downloadAttendanceData(mId),
                new DefaultObserver<AddCustomAttendanceReq>() {
                    @Override
                    public void onSuccess(AddCustomAttendanceReq response) {
                        //考勤点信息拿到经纬度与定位的经纬度用来计算考勤距离
                        mView.backPointInfo(flag, response);

                    }
                });
    }


    /**
     * 提交打卡点的位置信息
     *
     * @param locationPunchReq
     */
    public void commitPunchData(LocationPunchReq locationPunchReq) {

        mHttpManager.doHttpDeal(RetrofitHelper.getApiService().downloadPunchData(locationPunchReq),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        //考勤点信息拿到经纬度与定位的经纬度用来计算考勤距离
                        ToastUtil.showToast(activity, "打卡成功");
                        mView.finishiPage();
                    }
                });
    }
}
