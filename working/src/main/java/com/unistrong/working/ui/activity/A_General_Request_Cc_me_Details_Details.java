package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter;
import com.unistrong.working.R;
import com.unistrong.working.bean.GeneralRequest_CcMeDetailsBean;
import com.unistrong.working.databinding.AGeneralRequestCcMeDetailsBinding;
import com.unistrong.working.ui.mvpview.GeneralRequestCcMeDetailsView;
import com.unistrong.working.ui.presenter.GeneralRequestCcMeDetailsPresenter;
import com.unistrong.working.ui.viewmodel.GeneralRequest_CcMeDetailsVM;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 通用申请-抄送我的
 * 作者：Laughing on 2018/5/3 20:37
 * 邮箱：719240226@qq.com
 */

public class A_General_Request_Cc_me_Details_Details extends TitleActivity implements GeneralRequestCcMeDetailsView {

    private List<Map<String, Object>> mDatas;
    private GridViewAddImageAdapter mAdapter;
    private GeneralRequestCcMeDetailsPresenter mGeneralRequestCcMeDetailsPresenter;
    private GeneralRequest_CcMeDetailsVM mGeneralRequest_ccMeDetailsVM;
    private AGeneralRequestCcMeDetailsBinding mBinding;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_general_request_cc_me_details);
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initView() {
        mDatas = new ArrayList<>();
        mAdapter = new GridViewAddImageAdapter(mDatas, this);
        mBinding.gvGeneralRequestCcMe.setAdapter(mAdapter);

    }

    private void initData() {
        mGeneralRequestCcMeDetailsPresenter = new GeneralRequestCcMeDetailsPresenter(this, this, this);
        mGeneralRequest_ccMeDetailsVM = new GeneralRequest_CcMeDetailsVM(new GeneralRequest_CcMeDetailsBean());
        mBinding.setViewmodel(mGeneralRequest_ccMeDetailsVM);//绑定数据
    }

    private void initListener() {
        mBinding.setClick(v -> mGeneralRequestCcMeDetailsPresenter.click(v));

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_General_Request_Cc_me_Details_Details.class));
    }

    public void initTitle() {
        setTitleText("通用申请-抄送我的");//标题
    }

    @Override
    public void cancelApprovalSuccess() {
        finish();
    }

    @Override
    public void jump() {
        String content = mBinding.tvGeneralRequestCcMeContent.getText().toString().trim();
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
