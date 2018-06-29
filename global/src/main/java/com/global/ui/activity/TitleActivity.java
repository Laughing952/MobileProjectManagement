package com.global.ui.activity;

import android.os.Bundle;

import com.waterbase.ui.BaseTitleActivity;

import org.greenrobot.eventbus.EventBus;


public class TitleActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLeftTwoImageVisibity(false);
        setRightOneImageVisibity(false);
        setRightTextViewVisibity(false);
        setRightTwoImageVisibity(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
