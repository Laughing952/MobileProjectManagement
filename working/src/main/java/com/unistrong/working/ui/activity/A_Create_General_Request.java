package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter2;
import com.global.util.UserIdUtil;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ACreateGeneralRequestBinding;
import com.unistrong.working.request.CreateGeneralRequestReq;
import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.CreateNewTask_ProjectNameRep;
import com.unistrong.working.response.ImageRep;
import com.unistrong.working.ui.mvpview.CreateGeneralRequestView;
import com.unistrong.working.ui.presenter.CreateGeneralRequestPresenter;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ToastUtil;
import com.waterbase.utile.ViewUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 创建通用申请
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_Create_General_Request extends TitleActivity implements CreateGeneralRequestView<ImageRep> {

    private List<Map<String, Object>> mDatas;
    private GridViewAddImageAdapter2 mAdapter;
    private CreateGeneralRequestPresenter presenter;
    private ACreateGeneralRequestBinding mBinding;
    private Context mContext = this;
    private CreateGeneralRequestReq createGeneralRequestReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_create_general_request);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//软键盘弹出后，让屏幕整体上移
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initView() {
        mDatas = new ArrayList<>();
        mAdapter = new GridViewAddImageAdapter2(this);
        mBinding.gvCreateGeneralRequest.setAdapter(mAdapter);
    }

    private void initData() {
        presenter = new CreateGeneralRequestPresenter(this, this, this);
        createGeneralRequestReq = new CreateGeneralRequestReq();
    }

    private void initListener() {
        mBinding.gvCreateGeneralRequest.setOnItemClickListener((parent, view, position, id) -> {

            startActivityForResult(new Intent(this, MyPhotoActivity.class), 0x05);
        });
        mBinding.setClick(v -> presenter.click(v));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Create_General_Request.class));
    }

    public void initTitle() {
        setTitleText("创建通用申请");//标题
        setRightTextViewVisibity(true);
        setRightTextViewText("创建");
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        createGeneralRequestReq.setApproveTitle(mBinding.etCreateGeneralRequestTitle.getText().toString().trim());
        createGeneralRequestReq.setApproveNote(mBinding.etCreateGeneralRequestOther.getText().toString().trim());
        createGeneralRequestReq.setUserId(UserIdUtil.getUserIdLong());
        createGeneralRequestReq.setImgUrl(mAdapter.getDatas());
        presenter.create(createGeneralRequestReq);
    }


    @Override
    public void resultImageUrl(ImageRep imageRep) {
        mAdapter.addData(imageRep);
        setGridViewHeight();
    }

    @Override
    public void createProjectSuccess() {
        ToastUtil.showToast(this, "新建成功");
        finish();
    }

    /**
     * 选择项目
     *
     * @param rep
     */
    @Override
    public void setProjectNameResult(CreateNewTask_ProjectNameRep rep) {
        mBinding.etCreateGeneralRequestProject.setText(rep.getItemname());
        createGeneralRequestReq.setItemid(rep.getItemid());
    }

    /**
     * 选择审批人
     *
     * @param rep
     */
    @Override
    public void setApproverResult(CreateNewTask_PerformerRep rep) {
        mBinding.etCreateGeneralRequestApprovePerson.setText(rep.getUsername());
        createGeneralRequestReq.setApproveCkUserId(rep.getUserId());
    }

    /**
     * 选择抄送人
     *
     * @param rep
     */
    @Override
    public void setCcPersonResult(CreateNewTask_PerformerRep rep) {
        mBinding.etCreateGeneralRequestCcPerson.setText(rep.getUsername());
        createGeneralRequestReq.setApproveCcUserId(rep.getUserId());
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
                    presenter.uploadImage(path);
                }
                break;
        }

    }

    private void setGridViewHeight() {
        int line = (int) Math.ceil(mAdapter.getCount() / 4f); //图片行数（每行4张）
        LogUtil.d(TAG, "line  " + line);
        int height = (int) ViewUtil.dp2px(this, 80) * line;
        LogUtil.d(TAG, "height  " + height);
        ViewUtil.setViewHeight(mBinding.gvCreateGeneralRequest, height);
    }
}
