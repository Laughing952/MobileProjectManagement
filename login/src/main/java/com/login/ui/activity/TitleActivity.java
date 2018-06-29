package com.login.ui.activity;

import android.os.Bundle;

import com.waterbase.ui.BaseTitleActivity;

/**
 * Created by edz on 2018/3/27.
 */

public class TitleActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLeftOneImageVisibity(false);
        setLeftTwoImageVisibity(false);
        setRightOneImageVisibity(false);
        setRightTextViewVisibity(false);
        setRightTwoImageVisibity(false);
    }
}
