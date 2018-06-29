package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.bean.CenterHelpFunctionDescriptionBean;
import com.mycentre.databinding.ACenterHelpFunctionDescriptionBinding;
import com.mycentre.ui.adapter.Adapter_Center_Help_Function_Description;
import com.mycentre.ui.mvpview.CenterHelpFunctionDescriptionView;
import com.mycentre.ui.presenter.CenterHelpFunctionDescriptionPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_Center_Help_Function_Description extends TitleActivity implements CenterHelpFunctionDescriptionView {

    private ACenterHelpFunctionDescriptionBinding binding;
    private CenterHelpFunctionDescriptionPresenter mPrensenter;
    private Adapter_Center_Help_Function_Description adapterApply;
    private Adapter_Center_Help_Function_Description adapterMsg;
    private Context mContext = this;
    private String pId;
    private String pName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setView(R.layout.a_center_help_function_description);
        initTitle();
        initView();
        initData();
        initListerner();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Center_Help_Function_Description.class));
    }

    public void initTitle() {
        setTitleText("功能说明");//标题
    }

    private void initData() {
        mPrensenter = new CenterHelpFunctionDescriptionPresenter(this, this, this);
        List<CenterHelpFunctionDescriptionBean> applyActionList = new ArrayList<>();
        applyActionList.add(new CenterHelpFunctionDescriptionBean(1, "质量", R.mipmap.topbar_icon_more_blue));
        applyActionList.add(new CenterHelpFunctionDescriptionBean(2, "记工", R.mipmap.topbar_icon_more_blue));
        applyActionList.add(new CenterHelpFunctionDescriptionBean(3, "物资", R.mipmap.topbar_icon_more_blue));

        adapterApply.setData(applyActionList);
        List<CenterHelpFunctionDescriptionBean> msgActionList = new ArrayList<>();
        msgActionList.add(new CenterHelpFunctionDescriptionBean(1, "任务", R.mipmap.topbar_icon_more_blue));
        msgActionList.add(new CenterHelpFunctionDescriptionBean(2, "申请", R.mipmap.topbar_icon_more_blue));

        adapterMsg.setData(msgActionList);
    }

    protected void initView() {
        binding.srlCenterHelpFunctionDescription.setOnRefreshListener(() -> {
            // TODO: 2018/5/7 刷新的方法
//            mPrensenter.request(pId);

        });
        binding.rvCenterHelpFunctionDescriptionApply.setLayoutManager(new GridLayoutManager(this, 4));
        adapterApply = new Adapter_Center_Help_Function_Description();
        binding.rvCenterHelpFunctionDescriptionApply.setAdapter(adapterApply);
        binding.rvCenterHelpFunctionDescriptionMsg.setLayoutManager(new GridLayoutManager(this, 4));
        adapterMsg = new Adapter_Center_Help_Function_Description();
        binding.rvCenterHelpFunctionDescriptionMsg.setAdapter(adapterMsg);
    }

    private void initListerner() {

        adapterApply.setItemClickListener(new ItemClickListener<CenterHelpFunctionDescriptionBean>() {
            @Override
            public void itemClick(View v, CenterHelpFunctionDescriptionBean action, int index) {
                switch (action.getId()) {
                    case 1: // 质量
                        A_Quality_Description.startActivity(mContext);
                        break;
                    case 2: // 记工
                        A_Job_Description.startActivity(mContext);
                        break;
                    case 3: // 物资
                        A_Material_Description.startActivity(mContext);
                        break;
                }
            }

        });

        adapterMsg.setItemClickListener(new ItemClickListener<CenterHelpFunctionDescriptionBean>() {
            @Override
            public void itemClick(View v, CenterHelpFunctionDescriptionBean action, int index) {
                switch (action.getId()) {
                    case 1: //任务
                        A_Task_Description.startActivity(mContext);
                        break;
                    case 2: // 申请
                        A_Request_Description.startActivity(mContext);
                        break;
                }
            }

        });
    }
}
