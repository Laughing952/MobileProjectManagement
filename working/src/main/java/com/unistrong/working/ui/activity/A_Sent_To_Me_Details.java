package com.unistrong.working.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter;
import com.global.util.DateUtils;
import com.global.util.UserIdUtil;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ASentToMeDetailsBinding;
import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.PersonRep;
import com.unistrong.working.ui.adapter.Adapter_Sent_To_Me_Details;
import com.unistrong.working.ui.mvpview.SentToMeDetailsView;
import com.unistrong.working.ui.presenter.SentToMeDetailsPresenter;
import com.unistrong.working.ui.viewmodel.SentToMeDetailsVM;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 派给我的-详情
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Sent_To_Me_Details extends TitleActivity implements SentToMeDetailsView {

    private int mFlag;
    public static int Constant_Sent_To_Me = 0x110;   //派给我的-详情
    public static int Constant_I_Arranged = 0x111;   //我安排的-详情
    public static int Constant_I_Participated = 0x112; //我参与的-详情
    private List<Map<String, Object>> mDatas;
    private GridViewAddImageAdapter mAdapter;
    private ASentToMeDetailsBinding mBinding;
    private Context mContext = this;
    private Dialog dialog;
    private SentToMeDetailsPresenter mSentToMeDetailsPresenter;
    private Adapter_Sent_To_Me_Details mAdapter_sent_to_me_details;
    private String mMsg;
    private String taskId;
    private SentToMeDetailsVM mSentToMeDetailsVM;
    public static final int A_B = 0x111;//从第一个页面A 跳转到第二个页面B 的 requestCode
    public static final int B_A = 0x211;//从中间页面B 返回到第一个页面A 的 resultCode
    public static final String B_A_DATA = "B_A_DATA";//从中间页面B 返回到第一个页面A 的 data
    private ArrayList<PersonRep> mPersonReps;
    private SentToMeDetailsVM sentToMeDetailsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_sent_to_me_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//软键盘弹出后，让屏幕整体上移
        taskId = getIntent().getStringExtra("taskId");
        mFlag = getIntent().getIntExtra("flag", -1);
        initTitle();
        initView();
        initData();
    }

    private void initView() {
        mDatas = new ArrayList<>();
//        mAdapter = new GridViewAddImageAdapter(mDatas, this);
//        mBinding.rvSentToMeDetailsWords.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSentToMeDetailsPresenter.getWorkTaskInfo(taskId);
        initListener();
    }

    private void initData() {
        mSentToMeDetailsPresenter = new SentToMeDetailsPresenter(this, this, this);
        mAdapter_sent_to_me_details = new Adapter_Sent_To_Me_Details();
        mAdapter_sent_to_me_details.setEmptyLayout(R.layout.empty);
        mAdapter_sent_to_me_details.addHeadLayout(R.layout.a_sent_to_me_details_header);
        mAdapter_sent_to_me_details.setAlwaysShowHead(true);

        mBinding.rvSentToMeDetailsWords.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvSentToMeDetailsWords.setAdapter(mAdapter_sent_to_me_details);
    }

//    /**
//     * 处理消息的初始化数据
//     */
//    private void messageList() {
//
//    }


    private void initListener() {
        mBinding.setClick(v -> mSentToMeDetailsPresenter.click(v));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Sent_To_Me_Details.class));
    }

    public static void startActivity(Context context, int flag) {
        context.startActivity(new Intent(context, A_Sent_To_Me_Details.class));
//        mFlag = flag;
    }

    public static void startActivity(Context context, int flag, String taskId) {
        Intent intent = new Intent(context, A_Sent_To_Me_Details.class);
        intent.putExtra("taskId", taskId);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    public void initTitle() {
        if (mFlag == Constant_Sent_To_Me) {
            //派给我的-详情
            initTitleText("派给我的-详情");

        } else if (mFlag == Constant_I_Arranged) {
            //我安排的-详情
            initTitleText("我安排的-详情");
        } else if (mFlag == Constant_I_Participated) {
            //我参与的-详情
            setTitleText("我参与的-详情");//标题

        }

    }

    /**
     * 设置标题的方法
     *
     * @param title
     */
    private void initTitleText(String title) {
        setTitleText(title);//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("更多");
    }

    /**
     *
     */
    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        if (mFlag == Constant_Sent_To_Me) {
            //派给我的-详情
            showDialog1();

        } else if (mFlag == Constant_I_Arranged) {
            //我安排的-详情
            showDialog2();

        } else if (mFlag == Constant_I_Participated) {
            //我参与的-详情
            //showDialog1();//没有更多按钮-不用弹出底部对话框
        }
        //mCreateGeneralRequestPresenter.create(mCreateGeneralRequestVM);
    }

    @Override
    public void sendTaskDynamic() {
        mMsg = mBinding.etSentToMeDetailsMsg.getText().toString().trim();
        if (!StrUtil.isEmpty(mMsg)) {
            sentToMeDetailsVM.setFeedbackNote(mMsg);
            sentToMeDetailsVM.setCreater(UserIdUtil.getUserIdLong());
            sentToMeDetailsVM.setMessageStatus("3");
            mSentToMeDetailsPresenter.sendMessageInfo(sentToMeDetailsVM);
        }else{
            ToastUtil.showToast(this,"发送内容不能为空");
        }
    }

    /**
     * 我安排的-详情-设置参与人
     *
     * @param rep
     */
    @Override
    public void setJoinPeopleResult(CreateNewTask_PerformerRep rep) {
        //RecyclerView的header 中设置参与人
        ToastUtil.showToast(mContext, rep.getUsername());
        mAdapter_sent_to_me_details.setJoinPerson(rep.getUsername());
    }

    @Override
    public void resultWorkTaskInfo(SentToMeDetailsVM sentToMeDetailsVM) {
        this.sentToMeDetailsVM=sentToMeDetailsVM;
//        mBinding.setViewmodel(mSentToMeDetailsVM);//绑定数据
        if (sentToMeDetailsVM.getFeedbackList() == null || sentToMeDetailsVM.getFeedbackList().isEmpty()) {
            mAdapter_sent_to_me_details.setEmptyLayout(R.layout.empty);
        } else {
            mAdapter_sent_to_me_details.setData(sentToMeDetailsVM.getFeedbackList());
            mBinding.rvSentToMeDetailsWords.scrollToPosition(0);
        }
        mAdapter_sent_to_me_details.setHeadData(sentToMeDetailsVM);//绑定数据
    }

    /**
     * 取消任务成功
     * @param response
     */
    @Override
    public void doCancelTaskSuccess(Object response) {
        finish();
    }

    @Override
    public void refreshData() {
        ToastUtil.showToast(this,"发送成功");
        mBinding.etSentToMeDetailsMsg.getText().clear();
        mSentToMeDetailsPresenter.getWorkTaskInfo(taskId);
    }

    /**
     * 拍完照片后返回的数据和任务参与人数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0x05://获取照片
                if (resultCode == MyPhotoActivity.FINSH_RESULT && data != null) {
                    String path = data.getStringExtra("image");
                    photoPath(path);
                    Uri uri = data.getData();
                    LogUtil.e("TAG", "laughing---123------------------->   " + uri.toString());
//                    showUserHeadPhoto(path);
                }
                break;
            case A_B://获取参与人（逻辑与选择项目成员相同）
                if (resultCode == B_A && data != null) {
                    String path = data.getStringExtra("image");
                    // 把下一页的数据携带回来
                    if (data != null) {
                        ArrayList<PersonRep> personReps = (ArrayList<PersonRep>) data.getSerializableExtra(B_A_DATA);
//                        mBinding.et.setText(personReps.size() + "人");
                        // TODO: 2018/5/30  从此处获取参与人
                        LogUtil.e("TAG", "laughing---------------------->   " + personReps.size());
                        ToastUtil.showToast(mContext, "click--->" + personReps.size());
                        mPersonReps.clear();
                        mPersonReps.addAll(personReps);
                    }

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
     * 选择图片对话框1-派给我的-详情
     */
    public void showDialog1() {
        View localView = LayoutInflater.from(this).inflate(
                R.layout.dialog_feedback_progress, null);
        TextView priority = (TextView) localView.findViewById(R.id.tv_set_priority);
        TextView progress = (TextView) localView.findViewById(R.id.tv_feedback_progress);
        TextView tv_cancel = (TextView) localView.findViewById(R.id.tv_cancel);
        dialog = new Dialog(mContext, com.global.R.style.custom_dialog);
        DateUtils.initDialog(dialog, localView, getWindowManager());
        dialog.show();
        tv_cancel.setOnClickListener(arg0 -> {
            //取消按钮
            dialog.dismiss();
        });

        priority.setOnClickListener(v -> {
            dialog.dismiss();
            // 设置优先级
            ToastUtil.showToast(mContext, "设置优先级");

        });

        progress.setOnClickListener(v -> {
            dialog.dismiss();
            // 反馈任务进度
            ToastUtil.showToast(mContext, "反馈任务进度!");
            if (sentToMeDetailsVM.getSchedule()!=null) {
                A_Seek_Bar_Task_Progress.startActivity(mContext, sentToMeDetailsVM.getSchedule(),
                        sentToMeDetailsVM.getTaskId());
            }else {
                A_Seek_Bar_Task_Progress.startActivity(mContext, "0",
                        sentToMeDetailsVM.getTaskId());
            }
        });
    }

    /**
     * 选择图片对话框2-我安排的-详情
     */
    public void showDialog2() {
        View localView = LayoutInflater.from(this).inflate(
                R.layout.dialog_bottom_i_arranged, null);
        TextView tv_task_participant = (TextView) localView.findViewById(R.id.tv_task_participant);
        TextView tv_urge_task = (TextView) localView.findViewById(R.id.tv_urge_task);
        TextView tv_edit_task = (TextView) localView.findViewById(R.id.tv_edit_task);
        TextView tv_cancel_task = (TextView) localView.findViewById(R.id.tv_cancel_task);
        TextView tv_cancel = (TextView) localView.findViewById(R.id.tv_cancel);
        dialog = new Dialog(mContext, com.global.R.style.custom_dialog);
        DateUtils.initDialog(dialog, localView, getWindowManager());
        dialog.show();
        tv_cancel.setOnClickListener(arg0 -> {
            //取消按钮
            dialog.dismiss();
        });

        tv_task_participant.setOnClickListener(v -> {
            dialog.dismiss();
            // 任务参与人
//            mSentToMeDetailsPresenter.selectJoinPeople();
            jumpAndGetMembers();

        });
        tv_urge_task.setOnClickListener(v -> {
            dialog.dismiss();
            // 催办任务
            ToastUtil.showToast(mContext, "催办任务");

        });
        tv_edit_task.setOnClickListener(v -> {
            dialog.dismiss();
            // 编辑任务
//            SentToMeDetailsVM entToMeDetailsVM=new SentToMeDetailsVM();
//            entToMeDetailsVM.setItemId("123456");//测试数据
            A_Create_New_Task.startActivity(mContext, A_Create_New_Task.Constant_Edit, mSentToMeDetailsVM);

        });

        tv_cancel_task.setOnClickListener(v -> {
            dialog.dismiss();
            // 取消任务

            if (mSentToMeDetailsVM != null) {
                mSentToMeDetailsPresenter.doCancleTask(mSentToMeDetailsVM.getTaskId());
            }

        });
    }

    /**
     * 获取
     */
    private void jumpAndGetMembers() {
        Intent intent = null;
        mPersonReps = new ArrayList<>();
        try {
            intent = new Intent(mContext, Class.forName("com.project.ui.activity.A_Project_Member"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        intent.putExtra(A_Create_New_Task.EDIT_DATA, mPersonReps);
        startActivityForResult(intent, A_B);
    }
}
