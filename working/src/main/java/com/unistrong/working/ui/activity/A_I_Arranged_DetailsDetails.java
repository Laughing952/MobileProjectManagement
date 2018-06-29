package com.unistrong.working.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter;
import com.unistrong.working.R;
import com.unistrong.working.bean.GeneralApprovalIApprovedBean;
import com.unistrong.working.bean.IArrangedDetailsBean;
import com.unistrong.working.databinding.AIArrangedDetailsBinding;
import com.unistrong.working.ui.adapter.Adapter_I_Arranged_Details;
import com.unistrong.working.ui.mvpview.IArrangedDetailsView;
import com.unistrong.working.ui.presenter.IArrangedDetailsPresenter;
import com.unistrong.working.ui.viewmodel.GeneralApprovalIApprovedVM;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 我安排的-详情
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_I_Arranged_DetailsDetails extends TitleActivity implements  IArrangedDetailsView {

    private List<Map<String, Object>> mDatas;
    private GridViewAddImageAdapter mAdapter;
    private GeneralApprovalIApprovedVM mGeneralApprovalIApprovedVM;
    private AIArrangedDetailsBinding mBinding;
    private Context mContext = this;
    private Dialog dialog;
    private IArrangedDetailsPresenter mIArrangedDetailsPresenter;
    private Adapter_I_Arranged_Details mAdapter_i_arranged_details;
    private String mMsg;
    private TextView mArrangePeopleName;
    private TextView mApprovePerson;
    private TextView mProjectName;
    private TextView mTaskName;
    private TextView mEndDate;
    private TextView mDescribe;
    private TextView mJoinPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_i_arranged_details);
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
        mAdapter_i_arranged_details = new Adapter_I_Arranged_Details();
        mAdapter_i_arranged_details.addHeadLayout(R.layout.a_i_arranged_details_header);
        initHeader();
        mIArrangedDetailsPresenter = new IArrangedDetailsPresenter(this, this, this);
        mBinding.rvSentToMeDetailsWords.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvSentToMeDetailsWords.setAdapter(mAdapter_i_arranged_details);
        mAdapter_i_arranged_details.addData(new IArrangedDetailsBean());
    }

    private void initHeader() {
        View header = View.inflate(this, R.layout.a_i_arranged_details_header, null);
        //安排人
        mArrangePeopleName = (TextView) header.findViewById(R.id.tv_i_arranged_details_header_arrange_people_name);
        //执行人
        mApprovePerson = (TextView) header.findViewById(R.id.tv_i_arranged_details_header_approve_person);
        //项目名
        mProjectName = (TextView) header.findViewById(R.id.tv_i_arranged_details_header_project_name);
        //任务名称
        mTaskName = (TextView) header.findViewById(R.id.tv_i_arranged_details_header_task_name);
        //截止日期
        mEndDate = (TextView) header.findViewById(R.id.tv_i_arranged_details_header_end_date);
        //描述
        mDescribe = (TextView) header.findViewById(R.id.tv_i_arranged_details_header_describe);
        //参与人
        mJoinPerson = (TextView) header.findViewById(R.id.tv_i_arranged_details_header_join_person);
    }

    private void initListener() {
        mBinding.setClick(v -> mIArrangedDetailsPresenter.click(v));
    }

    @Override
    public void sendTaskDynamic() {
        mMsg = mBinding.etIArrangedDetailsMsg.getText().toString().trim();
        ToastUtil.showToast(mContext, mMsg);
        if (!StrUtil.isEmpty(mMsg)) {
            mAdapter_i_arranged_details.addData(new IArrangedDetailsBean());
            mBinding.etIArrangedDetailsMsg.setText("");
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_I_Arranged_DetailsDetails.class));
    }

    public void initTitle() {
        setTitleText("我安排的-详情");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("更多");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        showDialog();
//        mCreateGeneralRequestPresenter.create(mCreateGeneralRequestVM);
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

    /**
     * 选择图片对话框
     */
    public void showDialog() {
        View localView = LayoutInflater.from(this).inflate(
                R.layout.dialog_bottom_i_arranged, null);
        TextView tv_task_participant = (TextView) localView.findViewById(R.id.tv_task_participant);
        TextView tv_urge_task = (TextView) localView.findViewById(R.id.tv_urge_task);
        TextView tv_edit_task = (TextView) localView.findViewById(R.id.tv_edit_task);
        TextView tv_cancel_task = (TextView) localView.findViewById(R.id.tv_cancel_task);
        TextView tv_cancel = (TextView) localView.findViewById(R.id.tv_cancel);
        dialog = new Dialog(this, R.style.custom_dialog);
        dialog.setContentView(localView);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        // 设置全屏
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = display.getWidth(); // 设置宽度
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        tv_cancel.setOnClickListener(arg0 -> {
            //取消按钮
            dialog.dismiss();
        });

        tv_task_participant.setOnClickListener(v -> {
            dialog.dismiss();
            // 任务参与人
            ToastUtil.showToast(mContext, "任务参与人");

        });
        tv_urge_task.setOnClickListener(v -> {
            dialog.dismiss();
            // 催办任务
            ToastUtil.showToast(mContext, "催办任务");

        });
        tv_edit_task.setOnClickListener(v -> {
            dialog.dismiss();
            // 编辑任务
            ToastUtil.showToast(mContext, "编辑任务");

        });

        tv_cancel_task.setOnClickListener(v -> {
            dialog.dismiss();
            // 取消任务
            ToastUtil.showToast(mContext, "取消任务!");

        });
    }


}
