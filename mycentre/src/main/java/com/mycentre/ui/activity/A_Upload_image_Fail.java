package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;

/**
 * 上传图片失败
 * 作者：Laughing on 2018/5/29 17:29
 * 邮箱：719240226@qq.com
 */

public class A_Upload_image_Fail extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.a_upload_image_fail);
        setTitleText("上传图片失败");//标题
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Upload_image_Fail.class));
    }
}
