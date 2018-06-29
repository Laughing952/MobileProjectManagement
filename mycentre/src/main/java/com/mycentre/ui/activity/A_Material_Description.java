package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.AMaterialDescriptionBinding;

/**
 * 物资说明
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_Material_Description extends TitleActivity {

    private AMaterialDescriptionBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_material_description);
        initTitle();

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Material_Description.class));
    }

    public void initTitle() {
        setTitleText("物资说明");//标题
    }

}
