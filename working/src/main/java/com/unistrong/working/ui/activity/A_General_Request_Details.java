package com.unistrong.working.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter2;
import com.unistrong.working.R;
import com.unistrong.working.databinding.AGeneralRequestDetailsBinding;
import com.unistrong.working.response.CreateNewTask_PerformerRep;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.response.ImageRep;
import com.unistrong.working.ui.adapter.Adapter_Approval_history;
import com.unistrong.working.ui.mvpview.GeneralRequestIApprovedDetailsView;
import com.unistrong.working.ui.presenter.GeneralRequestDetailsPresenter;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;
import com.waterbase.utile.ViewUtil;

import java.lang.reflect.Field;


/**
 * 通用申请-我审批的（意见：同意 ，不同意，转交）
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_General_Request_Details extends TitleActivity implements GeneralRequestIApprovedDetailsView<ImageRep,GeneralRequestItemDetailRep> {

    public static final int FROMIAPPROVED=515;
    public static final int FROMICOMMIT=516;
    public static final int FROMCCME=517;
    private GridViewAddImageAdapter2 mAdapter;
    private GeneralRequestDetailsPresenter presenter;
    private Context mContext = this;
    private AGeneralRequestDetailsBinding mBinding;
    private int from;
    private Long approveId;
    private Adapter_Approval_history adapter_history;

    public static void startActivity(Fragment fragment, int from, Long approveId) {
        Intent intent = new Intent(fragment.getActivity(), A_General_Request_Details.class);
        intent.putExtra("FROM",from);
        intent.putExtra("approveId",approveId);
        fragment.startActivityForResult(intent,0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle();
        initData();
    }

    private void initView(String status,String isRead) {
        //设置不同状态下布局
        if (from==FROMCCME && "0".equals(isRead)){
            mBinding.setFrom(from);
        }else if (from==FROMICOMMIT && "0".equals(status)){
            mBinding.setFrom(from);
        }else if (from==FROMIAPPROVED && "0".equals(status)){
            mBinding.setFrom(from);
        }else {
            mBinding.setFrom(-1);
        }
//        if ("0".equals(status) || "0".equals(isRead)){
//            mBinding.setFrom(from);
//        }else{
//            mBinding.setFrom(-1);
//        }
        mAdapter = new GridViewAddImageAdapter2(this);
        mBinding.gvGeneralRequestIApproved.setAdapter(mAdapter);
        adapter_history = new Adapter_Approval_history();
        mBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerview.setAdapter(adapter_history);
    }

    private void initData() {
        presenter = new GeneralRequestDetailsPresenter(this, this, this);
        approveId = (Long) getIntent().getExtras().get("approveId");
        presenter.getGeneralRequestInfo(approveId);
    }

    private void initListener() {
        mBinding.setClick(v -> {
            String opinion = mBinding.etGeneralRequestIApprovedOpinion.getText().toString().trim();
            presenter.click(v,approveId,opinion);
        });
    }

    public void initTitle() {
        from = getIntent().getIntExtra("FROM", -1);
        switch (from){
            case FROMIAPPROVED:
                setTitleText("通用申请-我审批的");//标题(我审批的)
                break;
            case FROMICOMMIT:
                setTitleText("通用申请-我提交的");//标题(我提交的)
                break;
            case FROMCCME:
                setTitleText("通用申请-抄送我的");//标题(抄送我的)
                break;
            default:
                setTitleText("通用申请");//标题
                break;
        }
    }

    @Override
    public void resultRequestInfo(GeneralRequestItemDetailRep detailRep) {
        mBinding = setView(R.layout.a_general_request_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//软键盘弹出后，让屏幕整体上移
        initView(detailRep.getApproveStatus(),detailRep.getIsRead());
        mBinding.setViewmodel(detailRep);//绑定数据
        mAdapter.setData(detailRep.getApproveImgUrl());//设置图片数据
        adapter_history.setData(detailRep.getDeliverList());//审批历史数据
        if (detailRep.getDeliverList()==null || detailRep.getDeliverList().isEmpty()){
            mBinding.recyclerview.setVisibility(View.GONE);
        }
        setGridViewHeight();
        initListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void disposeRequestSuccess() {
        ToastUtil.showToast(this,"完成处理");
        setResult(RESULT_OK);
        finish();
    }

    /**
     * 跳转查看内容：没有内容则不跳转
     */
    @Override
    public void jump() {
        String content = mBinding.tvGeneralRequestIApprovedContent.getText().toString().trim();
        if (!StrUtil.isEmpty(content)) {
            AGeneralRequestIApprovedDetails_Content.startActivity(mContext, content);
        }
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
                if (resultCode == MyPhotoActivity.FINSH_RESULT && data != null) {
                    String path = data.getStringExtra("image");
                    presenter.uploadImage(path);
                }
                break;
        }

    }
    private void setGridViewHeight() {
        int line = (int) Math.ceil(mAdapter.getCount() / 4f);
        LogUtil.d(TAG, "line  " + line);
        int height = (int) ViewUtil.dp2px(this, 80) * line;
        LogUtil.d(TAG, "height  " + height);
        ViewUtil.setViewHeight(mBinding.gvGeneralRequestIApproved, height);
    }

}
