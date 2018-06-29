package com.unistrong.working.ui.presenter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.util.DateUtils;
import com.global.util.ImageUtils;
import com.global.util.PickerViewUtil;
import com.global.util.UserIdUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.request.DisposeGeneralRequestReq;
import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.response.ImageRep;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.adapter.Adapter_Create_New_Task_Performer;
import com.unistrong.working.ui.mvpview.GeneralRequestIApprovedDetailsView;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;
import com.waterbase.widget.ListDialog;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * 创建通用申请
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class GeneralRequestDetailsPresenter {
    private GeneralRequestIApprovedDetailsView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private final HttpManager httpManager;
    private DisposeGeneralRequestReq disposeReq;
    private List<CreateNewTask_PerformerRep> personList;
    private String projectId;
    private final long userId;

    public GeneralRequestDetailsPresenter(GeneralRequestIApprovedDetailsView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        httpManager = new HttpManager(activity, lifecycleProvider);
        disposeReq = new DisposeGeneralRequestReq();
        userId = UserIdUtil.getUserIdLong();
    }
    /**
     * 上传图片
     * */
    public void uploadImage(String imgPath) {
        MultipartBody.Part part = ImageUtils.fileToMultipartBodyPart(imgPath);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadImage(part),
                new DefaultObserver<ImageRep>() {
            @Override
            public void onSuccess(ImageRep response) {
                mView.resultImageUrl(response);
            }
        });
        }
    public void click(View view,long approveId,String opinion) {
        int id=view.getId();
        if (id == R.id.rl_general_request_i_approved_content) {
            //点击查看-内容
            mView.jump();
        }else if(id==R.id.rb_general_request_i_approved_agree){
            setDisposeReqAndUpload(approveId,opinion,"2",null,null);
        }else if(id==R.id.rb_general_request_i_approved_not_agree){
            setDisposeReqAndUpload(approveId,opinion,"1",null,null);
        }else if(id==R.id.rb_general_request_i_approved_transfer){
            queryPersonInfo(approveId,opinion);
        }else if(id==R.id.rb_general_request_i_approved_cancel){
            setDisposeReqAndUpload(approveId,null,null,"1",null);
        }else if(id==R.id.rb_general_request_i_approved_cc_me){
            setDisposeReqAndUpload(approveId,null,null,null,"1");
        }
    }

    /**
     * 通用申请处理
     * */
    public void disposeGeneralRequest(DisposeGeneralRequestReq disposeReq) {
        httpManager.doHttpDeal(RetrofitHelper.getApiService().disposeGeneralRequest(disposeReq),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.disposeRequestSuccess();
                    }
                });
    }

    /**
     * 通用申请详细信息获取
     * */
    public void getGeneralRequestInfo(Long approveId) {
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getGeneralRequestInfo(approveId),
                new DefaultObserver<GeneralRequestItemDetailRep>() {
                    @Override
                    public void onSuccess(GeneralRequestItemDetailRep response) {
                        projectId = response.getItemId();
                        mView.resultRequestInfo(response);
                    }
                });
    }

    /**
     * 查询人员信息
     *
     */
    private void queryPersonInfo(long approveId,String opinion) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getPersonInfo(projectId),
                new DefaultObserver<List<CreateNewTask_PerformerRep>>() {
                    @Override
                    public void onSuccess(List<CreateNewTask_PerformerRep> response) {
                        personList=response;
                        if (personList!=null){
                            selectDeliverTo(approveId,opinion);
                        }else{
                            ToastUtil.showToast(activity,"请检查网络");
                        }

                    }
                });
    }

    public void setDisposeReqAndUpload(long approveId,String opinion,String
            approveStatus,String isDel,String isRead){
        disposeReq.setIsDel(isDel);
        disposeReq.setIsRead(isRead);
        disposeReq.setApproveComment(opinion);
        disposeReq.setApproveStatus(approveStatus);
        disposeReq.setApproveId(approveId);
        disposeGeneralRequest(disposeReq);
    }

    /**
     * 选择转交人
     */
    private void selectDeliverTo(long approveId,String opinion) {
        PickerViewUtil.showOptionsPickerView(activity, "请选择转交人", new PickerViewUtil.SelectResultListener<CreateNewTask_PerformerRep>() {
            @Override
            public void result(CreateNewTask_PerformerRep rep) {
                DisposeGeneralRequestReq disposeGeneralRequestReq = new DisposeGeneralRequestReq();
                disposeGeneralRequestReq.setDeliverCkUserId(rep.getUserId());
                disposeGeneralRequestReq.setDeliverCkUserName(rep.getUsername());
                disposeGeneralRequestReq.setApproveId(approveId);
                disposeGeneralRequestReq.setUserId(userId);
                disposeGeneralRequestReq.setApproveComment(opinion);
                showDialog(disposeGeneralRequestReq);
            }
        },personList);

//        Adapter_Create_New_Task_Performer adapter_create_new_task_performer = new Adapter_Create_New_Task_Performer();
//
//        adapter_create_new_task_performer.setData(personList);
//        ListDialog listDialog = new ListDialog.Builder(activity)
//                .setTitle("请选择执行人")
//                .setAdapter(adapter_create_new_task_performer)
//                .create();
//        listDialog.show();
//        adapter_create_new_task_performer.setItemClickListener(new ItemClickListener<CreateNewTask_PerformerRep>() {
//            @Override
//            public void itemClick(View v, CreateNewTask_PerformerRep rep, int index) {
//                listDialog.dismiss();
//                DisposeGeneralRequestReq disposeGeneralRequestReq = new DisposeGeneralRequestReq();
//                disposeGeneralRequestReq.setDeliverCkUser(rep.getUsername());
//                disposeGeneralRequestReq.setApproveId(approveId);
//                disposeGeneralRequestReq.setUserId(A_General_Approval.id);
//                disposeGeneralRequestReq.setApproveComment(opinion);
//                showDialog(disposeGeneralRequestReq);
//            }
//
//        });
    }
    public void showDialog(DisposeGeneralRequestReq rep){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        builder.setMessage("您确定移交给"+rep.getDeliverCkUserName()+"?");
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                disposeGeneralRequest(rep);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(R.color.color_disposed));
       /* try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(dialog);
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setTextColor(Color.BLUE);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }*/
    }
    }

