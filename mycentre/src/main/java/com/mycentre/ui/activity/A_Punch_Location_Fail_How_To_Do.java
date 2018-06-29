package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;

/**
 * 考勤定位失败怎么办
 * 作者：Laughing on 2018/5/29 17:29
 * 邮箱：719240226@qq.com
 */

public class A_Punch_Location_Fail_How_To_Do extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.a_punch_location_fail_khow_to_do);
        setTitleText("考勤定位失败怎么办");//标题
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Punch_Location_Fail_How_To_Do.class));
    }
}
