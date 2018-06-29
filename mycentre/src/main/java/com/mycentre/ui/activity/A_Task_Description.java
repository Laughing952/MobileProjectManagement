package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.ATaskDescriptionBinding;

/**
 * 任务说明
 * 作者：Laughing on 2018/4/30 17:29
 * 邮箱：719240226@qq.com
 */

public class A_Task_Description extends TitleActivity {

    private ATaskDescriptionBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_task_description);
        initTitle();

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Task_Description.class));
    }

    public void initTitle() {
        setTitleText("任务说明");//标题
    }

}
