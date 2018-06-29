package com.project.ui.presenter;

import android.content.Intent;
import android.view.View;

import com.project.R;
import com.project.request.NewQualityReq;
import com.project.request.WorkerReq;
import com.project.response.WorkerRep;
import com.project.ui.activity.A_Record_Merit;
import com.project.ui.activity.A_Workers_List;
import com.project.ui.mvpview.AddMeritView;
import com.project.ui.mvpview.NewQualityView;
import com.project.widget.EditTextDialog;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加/编辑记功
 * Created by Water on 2018/5/18.
 */

public class AddMeritPresenter {

    private static final String TAG = "AddMeritPresenter";
    private static int ADD_WORKER = 66;

    private float NORMAL_HOURS = 10.0f; // 默认标准工时

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private AddMeritView mView;
    private String year;
    private String month;
    private String day;
    private List<WorkerRep> workerReqList;


    public AddMeritPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, AddMeritView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    /**
     * 初始化默认数据
     *
     * @param intent
     */
    public void initDate(Intent intent) {
        year = intent.getStringExtra("year");
        month = intent.getStringExtra("month");
        day = intent.getStringExtra("day");
        StringBuilder sb = new StringBuilder();
        sb.append(year).append("年").append(month).append("月").append(day).append("日");
        mView.initDate(sb.toString());
        mView.initNormalHours(String.format("%.1f%s", NORMAL_HOURS, "小时/天"));
    }

    public void click(View v) {
        if (v.getId() == R.id.iv_setting) {
            new EditTextDialog.Builder(activity)
                    .setConfirmListener(new EditTextDialog.ConfirmListener() {
                        @Override
                        public void confirm(String info) {
                            NORMAL_HOURS = Float.parseFloat(info);
                            mView.initNormalHours(String.format("%.1f%s", NORMAL_HOURS, "小时/天"));
                        }
                    }).create()
                    .show();
        } else if (v.getId() == R.id.ll_selworker) {
            A_Workers_List.startActivityForResult(activity, "", mView.getAdapterData(), A_Workers_List.SEL, ADD_WORKER);
        }
    }

    public void initWorkerList() {
        workerReqList = new ArrayList<>();
        workerReqList.add(new WorkerRep("1", 3.0F, "刘启"));
        workerReqList.add(new WorkerRep("2", 4.0F, "崔浩"));
        workerReqList.add(new WorkerRep("3", 5.0F, "袁超"));

        mView.initWorkerList(workerReqList);
    }

    public void removeData(WorkerRep workerRep) {
        workerReqList.remove(workerRep);
        mView.initWorkerList(workerReqList);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == activity.RESULT_OK && requestCode == ADD_WORKER) {
            workerReqList = (ArrayList<WorkerRep>) data.getSerializableExtra("checkedList");
            int size = workerReqList.size();
            for (int i = 0; i < size; i++) {
                if (workerReqList.get(i).getHours() == 0) {
                    workerReqList.get(i).setHours(NORMAL_HOURS);
                }
            }
            mView.initWorkerList(workerReqList);
        }
    }

    /**
     * todo 保存记功信息
     *
     * @param data
     */
    public void save(List<WorkerRep> data) {
        LogUtil.d(TAG, "REQ: " + data.toString());

    }
}
