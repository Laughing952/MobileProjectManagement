package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;

/**
 * 如何更新任务进度
 * 作者：Laughing on 2018/5/29 17:29
 * 邮箱：719240226@qq.com
 */

public class A_How_To_Update_Program_Progress extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.a_how_to_update_program_progress);
        setTitleText("如何更新任务进度");//标题
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_How_To_Update_Program_Progress.class));
    }
}
