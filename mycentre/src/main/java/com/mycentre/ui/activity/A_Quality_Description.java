package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.AQualityDescriptionBinding;

/**
 * 质量说明
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_Quality_Description extends TitleActivity {

    private AQualityDescriptionBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_quality_description);
        initTitle();

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Quality_Description.class));
    }

    public void initTitle() {
        setTitleText("质量说明");//标题
    }

}
