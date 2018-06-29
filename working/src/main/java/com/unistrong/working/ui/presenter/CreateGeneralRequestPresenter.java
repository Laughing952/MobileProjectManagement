package com.unistrong.working.ui.presenter;

import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.util.ImageUtils;
import com.global.util.PickerViewUtil;
import com.global.util.UserIdUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.request.CreateGeneralRequestReq;
import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.CreateNewTask_ProjectNameRep;
import com.unistrong.working.response.ImageRep;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.adapter.Adapter_Create_New_Task_Performer;
import com.unistrong.working.ui.adapter.Adapter_Create_New_Task_Project_Name;
import com.unistrong.working.ui.mvpview.CreateGeneralRequestView;
import com.unistrong.working.ui.viewmodel.CreateGeneralRequestVM;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;
import com.waterbase.widget.ListDialog;
import com.waterbase.widget.pickerview.OptionsPickerView;
import com.waterbase.widget.recycleview.IFullSpan;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * 创建通用申请
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class CreateGeneralRequestPresenter {
    private CreateGeneralRequestView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private List<CreateNewTask_PerformerRep> personList;
    private List<CreateNewTask_ProjectNameRep> projectList;
    private String projectId;

    public CreateGeneralRequestPresenter(CreateGeneralRequestView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void click(View view) {
        if (view.getId() == R.id.rl_create_general_request_project) {
            //项目名称选择
            queryProjectInfo();
        } else if (view.getId() == R.id.rl_create_general_request_approve_person) {
            //审批人
            if (projectId == null) {
                ToastUtil.showToast(activity, "请先选择项目");
            } else{
                queryPersonInfo("请选择审批人", "0");
            }
        } else if (view.getId() == R.id.rl_create_general_request_cc_person) {
            //抄送人
            if (projectId==null){
                ToastUtil.showToast(activity,"请先选择项目");
            }else {
                queryPersonInfo("请选择抄送人","1");
            }
        }
    }

    public void create(CreateGeneralRequestReq mCreateGeneralRequestReq) {
        if (StrUtil.isEmpty(mCreateGeneralRequestReq.getItemid())) {
            ToastUtil.showToast(activity, "项目不能为空");
            return;
        }
        if (StrUtil.isEmpty(mCreateGeneralRequestReq.getApproveTitle())) {
            ToastUtil.showToast(activity, "标题不能为空");
            return;
        }
        if (mCreateGeneralRequestReq.getApproveCkUserId()==0) {
            ToastUtil.showToast(activity, "审批人不能为空");
            return;
        }
        if (mCreateGeneralRequestReq.getImgUrl().isEmpty() &&
                StrUtil.isEmpty(mCreateGeneralRequestReq.getApproveNote())) {
            ToastUtil.showToast(activity, "备注和图片必须填写一项");
            return;
        }

        // TODO: 2018/5/4 创建通用申请
        createRequest(mCreateGeneralRequestReq);
    }

    /**
     * 上传图片
     * */
    public void uploadImage(String imgPath) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        MultipartBody.Part part = ImageUtils.fileToMultipartBodyPart(imgPath);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadImage(part),
                new DefaultObserver<ImageRep>() {
                    @Override
                    public void onSuccess(ImageRep response) {
                        mView.resultImageUrl(response);
                    }
                });
    }

    /**
     * 上传创建申请信息
     *
     * @param mCreateGeneralRequestReq
     */
    private void createRequest(CreateGeneralRequestReq mCreateGeneralRequestReq) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadCreateRequestDetails(mCreateGeneralRequestReq),
                new DefaultObserver<Object>() {
            @Override
            public void onSuccess(Object response) {
                mView.createProjectSuccess();
            }
        });
    }

    /**
     * 查询人员信息
     *
     */
    private void queryPersonInfo(String title,String code) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getPersonInfo(projectId),
                new DefaultObserver<List<CreateNewTask_PerformerRep>>() {
                    @Override
                    public void onSuccess(List<CreateNewTask_PerformerRep> response) {
                        personList=response;
                        if (personList!=null){
                            selectPerformer(title,code);
                        }else{
                            ToastUtil.showToast(activity,"请检查网络");
                        }

                    }
                });
    }

    /**
     * 查询项目信息
     *
     */
    private void queryProjectInfo() {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getProjectInfo(UserIdUtil.getUserIdLong()),
                new DefaultObserver<List<CreateNewTask_ProjectNameRep>>() {
                    @Override
                    public void onSuccess(List<CreateNewTask_ProjectNameRep> response) {
                        projectList=response;
                        if (projectList!=null){
                            selectProject();
                        }else {
                            ToastUtil.showToast(activity,"请检查网络");
                        }

                    }
                });
    }

    /**
     * 请选择项目名
     */
    public void selectProject() {

       PickerViewUtil.showOptionsPickerView(activity, "请选择项目名", new PickerViewUtil.SelectResultListener<CreateNewTask_ProjectNameRep>() {
           @Override
           public void result(CreateNewTask_ProjectNameRep rep) {
               if (rep!=null && rep.getItemid()!=null) {
                   projectId = rep.getItemid();
                   mView.setProjectNameResult(rep);
               }
           }
       },projectList);

//        Adapter_Create_New_Task_Project_Name adapter_create_new_task_projectName = new Adapter_Create_New_Task_Project_Name();
//
//        adapter_create_new_task_projectName.setData(projectList);
//        ListDialog listDialog = new ListDialog.Builder(activity)
//                .setTitle("请选择项目名")
//                .setAdapter(adapter_create_new_task_projectName)
//                .create();
//        listDialog.show();
//        adapter_create_new_task_projectName.setItemClickListener(new ItemClickListener<CreateNewTask_ProjectNameRep>() {
//            @Override
//            public void itemClick(View v, CreateNewTask_ProjectNameRep rep, int index) {
//                mView.setProjectNameResult(rep);
//                listDialog.dismiss();
//            }
//
//        });
    }

    /**
     * 选择选择人员 CreateNewTask_PerformerRep（借用执行人Rep）
     */
    private void selectPerformer(String title,String code) {
        PickerViewUtil.showOptionsPickerView(activity, title, new PickerViewUtil.SelectResultListener<CreateNewTask_PerformerRep>() {
            @Override
            public void result(CreateNewTask_PerformerRep rep) {
                if (code.equals("0")){
                    mView.setApproverResult(rep);
                }else if (code.equals("1")){
                    mView.setCcPersonResult(rep);
                }
            }
        },personList);
//        Adapter_Create_New_Task_Performer adapter_create_new_task_performer = new Adapter_Create_New_Task_Performer();
//
//        adapter_create_new_task_performer.setData(personList);
//        ListDialog listDialog = new ListDialog.Builder(activity)
//                .setTitle(title)
//                .setAdapter(adapter_create_new_task_performer)
//                .create();
//        listDialog.show();
//        adapter_create_new_task_performer.setItemClickListener(new ItemClickListener<CreateNewTask_PerformerRep>() {
//            @Override
//            public void itemClick(View v, CreateNewTask_PerformerRep rep, int index) {
//                if (code.equals("0")){
//                    mView.setApproverResult(rep);
//                }else if (code.equals("1")){
//                    mView.setCcPersonResult(rep);
//                }
//
//                listDialog.dismiss();
//            }
//
//        });
    }

}
