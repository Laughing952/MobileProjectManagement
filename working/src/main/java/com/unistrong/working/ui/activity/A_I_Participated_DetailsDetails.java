package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter;
import com.unistrong.working.R;
import com.unistrong.working.bean.GeneralApprovalIApprovedBean;
import com.unistrong.working.bean.IParticipatedDetailsBean;
import com.unistrong.working.databinding.AIParticipatedDetailsBinding;
import com.unistrong.working.ui.adapter.Adapter_I_Participated_Details;
import com.unistrong.working.ui.mvpview.IParticipatedDetailsView;
import com.unistrong.working.ui.presenter.IParticipatedDetailsPresenter;
import com.unistrong.working.ui.viewmodel.GeneralApprovalIApprovedVM;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 我参与的-详情
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_I_Participated_DetailsDetails extends TitleActivity implements IParticipatedDetailsView {

    private List<Map<String, Object>> mDatas;
    private GridViewAddImageAdapter mAdapter;
    private GeneralApprovalIApprovedVM mGeneralApprovalIApprovedVM;
    private AIParticipatedDetailsBinding mBinding;
    private Context mContext = this;
    private IParticipatedDetailsPresenter mIParticipatedDetailsPresenter;
    private Adapter_I_Participated_Details mAdapter_i_participated_details;
    private String mMsg;
    private TextView mArrangePersonName;
    private TextView mApprovePerson;
    private TextView mProjectName;
    private TextView mTaskName;
    private TextView mEndDate;
    private TextView mDescribe;
    private TextView mJoinPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_i_participated_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//软键盘弹出后，让屏幕整体上移
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initView() {
        mDatas = new ArrayList<>();
//        mAdapter = new GridViewAddImageAdapter(mDatas, this);
//        mBinding.rvSentToMeDetailsWords.setAdapter(mAdapter);

    }

    private void initData() {
        mGeneralApprovalIApprovedVM = new GeneralApprovalIApprovedVM(new GeneralApprovalIApprovedBean());
        mBinding.setViewmodel(mGeneralApprovalIApprovedVM);//绑定数据
        messageList();
    }

    /**
     * 处理消息的初始化数据
     */
    private void messageList() {
        mAdapter_i_participated_details = new Adapter_I_Participated_Details();
        mAdapter_i_participated_details.addHeadLayout(R.layout.a_i_participated_details_header);
        initHeader();
        mIParticipatedDetailsPresenter = new IParticipatedDetailsPresenter(this, this, this);
        mBinding.rvSentToMeDetailsWords.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvSentToMeDetailsWords.setAdapter(mAdapter_i_participated_details);
        mAdapter_i_participated_details.addData(new IParticipatedDetailsBean());
    }

    private void initHeader() {
        View header = View.inflate(this, R.layout.a_i_participated_details_header, null);
        //安排人
        mArrangePersonName = (TextView) header.findViewById(R.id.tv_i_participated_details_header_arrange_person_name);
        //执行人
        mApprovePerson = (TextView) header.findViewById(R.id.tv_i_participated_details_header_approve_person);
        //项目名
        mProjectName = (TextView) header.findViewById(R.id.tv_i_participated_details_project_name);
        //任务名称
        mTaskName = (TextView) header.findViewById(R.id.tv_i_participated_details_header_task_name);
        //截止日期
        mEndDate = (TextView) header.findViewById(R.id.tv_i_participated_details_header_end_date);
        //描述
        mDescribe = (TextView) header.findViewById(R.id.tv_i_participated_details_header_describe);
        //参与人
        mJoinPerson = (TextView) header.findViewById(R.id.tv_i_participated_details_header_join_person);
    }

    private void initListener() {
        mBinding.setClick(v -> mIParticipatedDetailsPresenter.click(v));
    }

    @Override
    public void sendTaskDynamic() {
        mMsg = mBinding.etIParticipatedDetailsMsg.getText().toString().trim();
        ToastUtil.showToast(mContext, mMsg);
        if (!StrUtil.isEmpty(mMsg)) {
            mAdapter_i_participated_details.addData(new IParticipatedDetailsBean());
            mBinding.etIParticipatedDetailsMsg.setText("");
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_I_Participated_DetailsDetails.class));
    }

    public void initTitle() {
        setTitleText("我参与的-详情");//标题
    }

    /**
     * 拍完照片后返回的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0x05:
                if (resultCode == MyPhotoActivity.FINSH_RESULT && data != null) {
                    String path = data.getStringExtra("image");
                    photoPath(path);
                    Uri uri = data.getData();
                    LogUtil.e("TAG", "laughing---123------------------->   " + uri.toString());
//                    showUserHeadPhoto(path);
                }
                break;
        }

    }

    public void photoPath(String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("path", path);
        mDatas.add(map);
        mAdapter.notifyDataSetChanged();
    }


}
