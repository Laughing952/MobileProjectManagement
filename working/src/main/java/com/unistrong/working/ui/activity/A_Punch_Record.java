package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.global.util.UserIdUtil;
import com.unistrong.working.R;
import com.unistrong.working.databinding.APunchRecordBinding;
import com.unistrong.working.response.PunchRecordRep;
import com.unistrong.working.ui.adapter.Adapter_Punch_Record;
import com.unistrong.working.ui.mvpview.PunchRecordView;
import com.unistrong.working.ui.presenter.PunchRecordPresenter;

import java.util.List;

/**
 * 打卡记录
 * 作者：Laughing on 2018/5/19 15:58
 * 邮箱：719240226@qq.com
 */

public class A_Punch_Record extends TitleActivity implements PunchRecordView<List<PunchRecordRep>> {

    private APunchRecordBinding mBinding;
    private PunchRecordPresenter mPresenter;
    private Adapter_Punch_Record mAdapter;
    private Context mContext = this;
    private static final String PUNCH_RECORD_NAME = "PunchRecordName";//考勤点名称
    private static final String PUNCH_RECORD_LAT = "PunchRecordLat";//考勤点纬度
    private static final String PUNCH_RECORD_LONG = "PunchRecordLong";//考勤点经度

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_punch_record);
        initTitle();
        initData();
        initListener();

    }

    private void initData() {
        mPresenter = new PunchRecordPresenter(this, this, this);
        mAdapter = new Adapter_Punch_Record();
        mBinding.rvPunchRecord.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvPunchRecord.setAdapter(mAdapter);
        //从服务器获取打卡记录数据进行展示
        mPresenter.downloadData(UserIdUtil.getUserIdLong());

    }

    private void initListener() {
        mBinding.setClick(v -> mPresenter.click(v));
        mBinding.srlPunchRecord.setOnRefreshListener(() -> {
            mPresenter.refData(UserIdUtil.getUserIdLong());//下拉刷新
        });
        mAdapter.openAutoLoadMore(true);
        mAdapter.setOnLoadMoreListener(() -> {
            mPresenter.loadMoreData(UserIdUtil.getUserIdLong());
        });

        mAdapter.setItemClickListener(new ItemClickListener<PunchRecordRep>() {
            @Override
            public void itemClick(View v, PunchRecordRep punchRecordRep, int index) {
                Intent intent = null;
                try {
                    intent = new Intent(mContext, Class.forName("com.unistrong.baidumaplibrary.ui.activity.A_Punch_Record_Marker_Overlay"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                intent.putExtra(PUNCH_RECORD_NAME, punchRecordRep.getSigninPointName());
                intent.putExtra(PUNCH_RECORD_LAT, punchRecordRep.getPersonSigninLaTitude());
                intent.putExtra(PUNCH_RECORD_LONG, punchRecordRep.getPersonSigninLongTitude());
                startActivity(intent);
            }
        });
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Punch_Record.class));
    }

    public void initTitle() {
        setTitleText("打卡记录");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("去签到");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
//        ToastUtil.showToast(this, "去签到");
        Intent intent = null;
        try {
            intent = new Intent(this, Class.forName("com.unistrong.baidumaplibrary.ui.activity.A_Punch"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.refData(UserIdUtil.getUserIdLong());//从下一个页面回来时，重新加载数据
    }

    /**
     * 获取回传的数据
     * <p>
     * int requestCode 请求码 int resultCode 结果码 Intent data 意图(带着返回参数)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10002 && resultCode == RESULT_OK) {
            // 为图片控件设置内容

//            int imageId = data.getIntExtra("imageId", R.drawable.ic_launcher);
//
//            iv.setImageResource(imageId);
        }
    }


    @Override
    public void initRepData(List<PunchRecordRep> list) {
        mBinding.srlPunchRecord.setRefreshing(false);
        mAdapter.setData(list);

    }

    @Override
    public void loadMoreResult(List<PunchRecordRep> response) {
        if (response == null || response.isEmpty()) {
            mAdapter.loadCompleted();
            return;
        }
        mAdapter.addData(response);
    }
}
