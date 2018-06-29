package com.unistrong.working.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.unistrong.working.R;
import com.unistrong.working.databinding.AGeneralRequestIApprovedDetailsContentBinding;
import com.waterbase.utile.StrUtil;

/**
 * 我审批的(我提交的，抄送我的)-内容
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class AGeneralRequestIApprovedDetails_Content extends TitleActivity {

    private AGeneralRequestIApprovedDetailsContentBinding mBinding;
    private Context mContext = this;
    private String mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_general_request_i_approved_details_content);
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initView() {

    }

    private void initData() {
        Intent intent = getIntent();
        mContent = intent.getStringExtra("Content");
        if (!StrUtil.isEmpty(mContent)) {

            mBinding.tvGeneralRequestIApprovedDetailsContentContent.setText(mContent);
        } else {
            mBinding.tvGeneralRequestIApprovedDetailsContentContent.setText("没有内容");
        }

    }

    private void initListener() {

    }

    public static void startActivity(Context context, String content) {
        Intent intent = new Intent(context, AGeneralRequestIApprovedDetails_Content.class);
        intent.putExtra("Content", content);
        context.startActivity(intent);

    }

    public void initTitle() {
        setTitleText("内容");//标题
    }


}
