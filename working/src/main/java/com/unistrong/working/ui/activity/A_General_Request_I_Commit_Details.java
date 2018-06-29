package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter;
import com.unistrong.working.R;
import com.unistrong.working.bean.GeneralRequest_ICommitDetailsBean;
import com.unistrong.working.databinding.AGeneralRequestICommitDetailsBinding;
import com.unistrong.working.ui.mvpview.GeneralRequestRecordView;
import com.unistrong.working.ui.presenter.GeneralRequestICommitDetailsPresenter;
import com.unistrong.working.ui.viewmodel.GeneralRequest_ICommitDetailsVM;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 通用申请-我提交的（取消）
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_General_Request_I_Commit_Details extends TitleActivity implements GeneralRequestRecordView {

    private List<Map<String, Object>> mDatas;
    private GridViewAddImageAdapter mAdapter;
    private GeneralRequestICommitDetailsPresenter mGeneralRequestICommitDetailsPresenter;
    private GeneralRequest_ICommitDetailsVM mGeneralRequest_iCommitDetailsVM;
    private AGeneralRequestICommitDetailsBinding mBinding;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_general_request_i_commit_details);
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initView() {
        mDatas = new ArrayList<>();
        mAdapter = new GridViewAddImageAdapter(mDatas, this);
        mBinding.gvGeneralRequestRecord.setAdapter(mAdapter);

    }

    private void initData() {
        mGeneralRequestICommitDetailsPresenter = new GeneralRequestICommitDetailsPresenter(this, this, this);
        mGeneralRequest_iCommitDetailsVM = new GeneralRequest_ICommitDetailsVM(new GeneralRequest_ICommitDetailsBean());
        mBinding.setViewmodel(mGeneralRequest_iCommitDetailsVM);//绑定数据
    }

    private void initListener() {
        mBinding.setClick(v -> mGeneralRequestICommitDetailsPresenter.click(v));

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_General_Request_I_Commit_Details.class));
    }

    public void initTitle() {
        setTitleText("通用申请-我提交的");//标题
    }

    @Override
    public void cancelApprovalSuccess() {
        finish();
    }

    @Override
    public void jump() {
        String content = mBinding.tvGeneralRequestICommitContent.getText().toString().trim();
        if (!StrUtil.isEmpty(content)) {
            AGeneralRequestIApprovedDetails_Content.startActivity(mContext, content);
        }
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
