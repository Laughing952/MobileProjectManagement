package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter2;
import com.global.util.DateUtils;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ACreateNewTaskBinding;
import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.CreateNewTask_ProjectNameRep;
import com.unistrong.working.response.ImageRep;
import com.unistrong.working.ui.mvpview.CreateNewTaskView;
import com.unistrong.working.ui.presenter.CreateNewTaskPresenter;
import com.unistrong.working.ui.viewmodel.CreateNewTaskVM;
import com.unistrong.working.ui.viewmodel.SentToMeDetailsVM;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ToastUtil;
import com.waterbase.utile.ViewUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 创建/编辑 通用申请
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Create_New_Task extends TitleActivity implements CreateNewTaskView {

    public static int Constant_Add = 0x210;//添加
    public static int Constant_Edit = 0x211;//编辑
    private List<Map<String, Object>> mDatas;
    private GridViewAddImageAdapter2 mAdapter;
    private CreateNewTaskPresenter mCreateNewTaskPresenter;
    private CreateNewTaskVM mCreateNewTaskVM;
    private ACreateNewTaskBinding mBinding;
    private Context mContext = this;
    private static int mFlag;
    public static final String EDIT_DATA = "EDIT_DATA";//编辑数据
    public static SentToMeDetailsVM mSentToMeDetailsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_create_new_task);
        initTitle();
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mDatas = new ArrayList<>();
        mAdapter = new GridViewAddImageAdapter2(this);
        mBinding.gvCreateNewTask.setAdapter(mAdapter);
    }

    private void initData() {
        mCreateNewTaskPresenter = new CreateNewTaskPresenter(this, this, this);
        mCreateNewTaskVM = new CreateNewTaskVM();
        mBinding.setViewmodel(mCreateNewTaskVM);//绑定数据
        doJudgmentAddOrEdit();//判断是增加还是编辑
    }

    /**
     * 判断是增加还是编辑
     */
    private void doJudgmentAddOrEdit() {
        LogUtil.e("TAG", "laughing---------------------->   " + mFlag);
        if (mFlag == Constant_Add) {    //添加
            //不用展示数据
            LogUtil.e("TAG", "laughing------------------不用展示数据----> +mSentToMeDetailsVM  ");
        } else if (mFlag == Constant_Edit) {     //编辑

            mSentToMeDetailsVM = (SentToMeDetailsVM) getIntent().getSerializableExtra(EDIT_DATA);
            if (mSentToMeDetailsVM != null) {
                //展示数据
                LogUtil.e("TAG", "laughing----------------展示数据------>   " + mSentToMeDetailsVM.getItemId());
                showTaskInfo();

            } else {
                LogUtil.e("TAG", "laughing----------------null------>   ");
            }
        }
    }

    /**
     * 展示要编辑的信息
     */
    private void showTaskInfo() {
//        mBinding.tvCreateNewTaskProjectName.setText(mSentToMeDetailsVM.getProjectName()+"");//
        mBinding.etCreateNewTaskTitle.setText(mSentToMeDetailsVM.getTaskName());//任务名称
        mBinding.tvCreateNewTaskDes.setText(mSentToMeDetailsVM.getFeedbackNote());//反馈描述
        mBinding.tvCreateNewTaskPerformer.setText(mSentToMeDetailsVM.getTaskHeadName());//执行人
        mBinding.tvCreateNewTaskEndDate.setText(DateUtils.transformDate2Str(mSentToMeDetailsVM.getTimeLimit()));//任务时限
        //图片展示mSentToMeDetailsVM.getTaskImgUrl()
        List<ImageRep> imageRepList = new ArrayList<>();
        String taskImgUrl = mSentToMeDetailsVM.getTaskImgUrl();
        String[] split = taskImgUrl.split(";", taskImgUrl.length());

        for (int i = 0; i < split.length; i++) {
            ImageRep imageRep = new ImageRep();
            imageRep.setHeadImg(split[i]);// 原图
            imageRep.setThumbnail(split[i]);// 缩略图
            imageRepList.add(imageRep);
        }

        mAdapter.setData(imageRepList);//设置图片数据

    }

    private void initListener() {
        mBinding.gvCreateNewTask.setOnItemClickListener((parent, view, position, id) -> {

            startActivityForResult(new Intent(this, MyPhotoActivity.class), 0x05);
        });
        mBinding.setClick(v -> mCreateNewTaskPresenter.click(v, mFlag));


    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Create_New_Task.class));
    }

    public static void startActivity(Context context, int flag, SentToMeDetailsVM sentToMeDetailsVM) {
        Intent intent = new Intent(context, A_Create_New_Task.class);
        intent.putExtra(EDIT_DATA, sentToMeDetailsVM);
        context.startActivity(intent);
        mFlag = flag;
    }

    public void initTitle() {
        if (mFlag == Constant_Add) {
            setTitleText("新建任务");//标题
            setRightTextViewVisibity(true);
            setRightTextViewText("创建");
        } else if (mFlag == Constant_Edit) {
            setTitleText("编辑任务");//标题
            setRightTextViewVisibity(true);
            setRightTextViewText("保存");
        }

    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();

        if (mFlag == Constant_Add) {
            //新建任务
            mCreateNewTaskVM.setImgUrl(mAdapter.getDatas());
            mCreateNewTaskPresenter.create(mCreateNewTaskVM, Constant_Add);

        } else if (mFlag == Constant_Edit) {
            //编辑任务
            mCreateNewTaskVM.setImgUrl(mAdapter.getDatas());
            mCreateNewTaskPresenter.create(mCreateNewTaskVM, Constant_Edit);
            ToastUtil.showToast(mContext, "编辑任务");
        }
    }

    @Override
    public void createProjectSuccess() {
        ToastUtil.showToast(this, "任务创建成功");
        finish();
    }

    @Override
    public void setProjectNameResult(CreateNewTask_ProjectNameRep rep) {
        mBinding.tvCreateNewTaskProjectName.setText(rep.getItemname());
        mCreateNewTaskVM.setItemId(rep.getItemid());
    }

    @Override
    public void startTaskDescribe() {
        startActivityForResult(new Intent(this, A_Create_New_Task_Describe.class), 0x123);
    }

    /**
     * 选择执行人
     *
     * @param rep
     */
    @Override
    public void setPerformerResult(CreateNewTask_PerformerRep rep) {
        mBinding.tvCreateNewTaskPerformer.setText(rep.getUsername());
        mCreateNewTaskVM.setTaskHeadId(rep.getUserId());
    }

    @Override
    public void selectEndDate() {
        mBinding.tvCreateNewTaskEndDate.setOnClickListener(v ->
                DateUtils.showDateDialogFromToday(this, "截止日期", mBinding.tvCreateNewTaskEndDate)
        );

    }

    @Override
    public void resultImageUrl(ImageRep imageRep) {
        mAdapter.addData(imageRep);
        setGridViewHeight();
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
                //拍照返回的结果
                if (resultCode == MyPhotoActivity.FINSH_RESULT && data != null) {
                    String path = data.getStringExtra("image");
                    mCreateNewTaskPresenter.uploadImage(path);
//                    photoPath(path);
//                    Uri uri = data.getData();
//                    LogUtil.e("TAG", "laughing---123------------------->   " + uri.toString());
//                    showUserHeadPhoto(path);
                }
                break;
            case 0x123:
                //任务描述返回的结果
                if (resultCode == RESULT_OK && data != null) {
                    String create_new_task_describe = data.getStringExtra("Create_New_Task_Describe");
                    mBinding.tvCreateNewTaskDes.setText(create_new_task_describe);
                }
                break;

        }

    }

    private void setGridViewHeight() {
        int line = (int) Math.ceil(mAdapter.getCount() / 4f);
        LogUtil.d(TAG, "line  " + line);
        int height = (int) ViewUtil.dp2px(this, 80) * line;
        LogUtil.d(TAG, "height  " + height);
        ViewUtil.setViewHeight(mBinding.gvCreateNewTask, height);
    }
}
