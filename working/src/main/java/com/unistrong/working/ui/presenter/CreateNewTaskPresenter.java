package com.unistrong.working.ui.presenter;

import android.view.View;

import com.global.util.ImageUtils;
import com.global.util.PickerViewUtil;
import com.global.util.UserIdUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.unistrong.working.R;
import com.unistrong.working.api.RetrofitHelper;
import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.CreateNewTask_ProjectNameRep;
import com.unistrong.working.response.ImageRep;
import com.unistrong.working.ui.activity.A_Create_New_Task;
import com.unistrong.working.ui.activity.A_General_Approval;
import com.unistrong.working.ui.mvpview.CreateNewTaskView;
import com.unistrong.working.ui.viewmodel.CreateNewTaskVM;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.DateUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * 创建/编辑 通用申请
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class CreateNewTaskPresenter {
    private CreateNewTaskView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private final HttpManager httpManager;
    private ArrayList<CreateNewTask_PerformerRep> itemHeadList;
    private long userId;
    private List<CreateNewTask_ProjectNameRep> projectList;
    private String projectId;

    public CreateNewTaskPresenter(CreateNewTaskView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        httpManager = new HttpManager(activity, lifecycleProvider);
        userId = UserIdUtil.getUserIdLong();
    }

    public void click(View view, int flag) {
        if (view.getId() == R.id.rl_create_new_task_project_name) {
            //添加时选择项目名/编辑时不可以选择
            judgmentIsAddOrEditClick(flag);

        } else if (view.getId() == R.id.rl_create_new_task_des) {
            //任务描述
            mView.startTaskDescribe();

        } else if (view.getId() == R.id.rl_create_new_task_performer) {
            //执行人
            if (projectId == null) {
                ToastUtil.showToast(activity, "请先选择项目");
            } else {
                queryPersonInfo();
            }
        } else if (view.getId() == R.id.rl_create_new_task_end_date) {
            //截止日期
            mView.selectEndDate();

        }
    }

    /**
     * 添加时选择项目名/编辑时不可以选择
     */
    private void judgmentIsAddOrEditClick(int flag) {
        if (flag == A_Create_New_Task.Constant_Add) {
           queryProjectInfo();
        } else if (flag == A_Create_New_Task.Constant_Edit) {
            //不用弹出选择项目框
        }

    }

    public void create(CreateNewTaskVM createNewTaskVM, int flag) {
        if (StrUtil.isEmpty(createNewTaskVM.getItemId())) {
            ToastUtil.showToast(activity, "项目名不能为空");
            return;
        }
        if (StrUtil.isEmpty(createNewTaskVM.getTaskName())) {
            ToastUtil.showToast(activity, "任务名称不能为空");
            return;
        }
        if (StrUtil.isEmpty(createNewTaskVM.getEndDate())) {
            ToastUtil.showToast(activity, "截止日期不能为空");
            return;
        }
        if (createNewTaskVM.getTaskHeadId() == 0) {
            ToastUtil.showToast(activity, "执行人不能为空");
            return;
        }
        // TODO: 2018/5/4 创建工作任务
        createProject(flag, createNewTaskVM);

    }

    private void createProject(int flag, CreateNewTaskVM createNewTaskVM) {
        if (flag == A_Create_New_Task.Constant_Add) {
            //新建任务
            uploadTaskInfo(createNewTaskVM);
        } else if (flag == A_Create_New_Task.Constant_Edit) {
            //编辑任务
            uploadTaskInfo(createNewTaskVM);

        }
    }

    /**
     * 上传创建/编辑任务信息
     */
    private void uploadTaskInfo(CreateNewTaskVM createNewTaskVM) {
        createNewTaskVM.setCreater(userId);
        createNewTaskVM.setTimeLimit(DateUtil.getDateByFormat(createNewTaskVM.endDate, "yyyy-MM-dd"));
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadTaskInfo(createNewTaskVM),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.createProjectSuccess();
                    }
                });
    }

    /**
     * 查询项目信息
     */
    private void queryProjectInfo() {
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getProjectInfo(UserIdUtil.getUserIdLong()),

                new DefaultObserver<List<CreateNewTask_ProjectNameRep>>() {
                    @Override
                    public void onSuccess(List<CreateNewTask_ProjectNameRep> response) {
                        projectList = response;
                        if (projectList != null) {
                            selectProject();
                        } else {
                            ToastUtil.showToast(activity, "请检查网络");
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
        }, projectList);
       /* List<CreateNewTask_ProjectNameRep> repList = new ArrayList<>();
        repList.add(new CreateNewTask_ProjectNameRep("10001", "xx项目1"));
        repList.add(new CreateNewTask_ProjectNameRep("10002", "xx项目2"));
        repList.add(new CreateNewTask_ProjectNameRep("10002", "xx项目3"));
        repList.add(new CreateNewTask_ProjectNameRep("10002", "xx项目4"));
        repList.add(new CreateNewTask_ProjectNameRep("10002", "xx项目5"));

        Adapter_Create_New_Task_Project_Name adapter = new Adapter_Create_New_Task_Project_Name();

        adapter.setData(repList);
        ListDialog listDialog = new ListDialog.Builder(activity)
                .setTitle("请选择项目名")
                .setAdapter(adapter)
                .create();
        listDialog.show();
        adapter.setItemClickListener(new ItemClickListener<CreateNewTask_ProjectNameRep>() {
            @Override
            public void itemClick(View v, CreateNewTask_ProjectNameRep rep, int index) {
                mView.setProjectNameResult(rep);
                listDialog.dismiss();
            }

        });*/
    }

    /**
     * 查询人员信息
     */
    public void queryPersonInfo() {
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getPersonInfo(projectId),
                new DefaultObserver<ArrayList<CreateNewTask_PerformerRep>>() {
                    @Override
                    public void onSuccess(ArrayList<CreateNewTask_PerformerRep> mProjectMemberCheckedVMS) {
                        itemHeadList = mProjectMemberCheckedVMS;
                        selectPerformer();
                    }
                });
    }

    /**
     * 选择执行人 CreateNewTask_PerformerRep
     */
    private void selectPerformer() {
        PickerViewUtil.showOptionsPickerView(activity, "请选择执行人", new PickerViewUtil.SelectResultListener<CreateNewTask_PerformerRep>() {
            @Override
            public void result(CreateNewTask_PerformerRep rep) {
                mView.setPerformerResult(rep);
            }
        }, itemHeadList);
        /*List<CreateNewTask_PerformerRep> repList = new ArrayList<>();
        repList.add(new CreateNewTask_PerformerRep(10001L, "Laughing1"));
        repList.add(new CreateNewTask_PerformerRep(10002L, "Laughing2"));
        repList.add(new CreateNewTask_PerformerRep(10002L, "Laughing3"));
        repList.add(new CreateNewTask_PerformerRep(10002L, "Laughing4"));
        repList.add(new CreateNewTask_PerformerRep(10002L, "Laughing5"));

        Adapter_Create_New_Task_Performer adapter_create_new_task_performer = new Adapter_Create_New_Task_Performer();

        adapter_create_new_task_performer.setData(repList);
        ListDialog listDialog = new ListDialog.Builder(activity)
                .setTitle("请选择执行人")
                .setAdapter(adapter_create_new_task_performer)
                .create();
        listDialog.show();
        adapter_create_new_task_performer.setItemClickListener(new ItemClickListener<CreateNewTask_PerformerRep>() {
            @Override
            public void itemClick(View v, CreateNewTask_PerformerRep rep, int index) {
                mView.setPerformerResult(rep);
                listDialog.dismiss();
            }
        });*/
    }

    /**
     * 上传图片
     */
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

}
