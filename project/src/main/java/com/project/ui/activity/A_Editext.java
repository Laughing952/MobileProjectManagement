package com.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AEdittextBinding;
import com.waterbase.ui.BaseActivity;

/**
 * 只有一个编辑框的Activity
 * 项目检查、检查结果
 * Created by Water on 2018/5/11.
 */

public class A_Editext extends TitleActivity {

    private AEdittextBinding binding;

    public static void startActivityForResult(BaseActivity activity, String titleText
            , String content, String hint, int requestCode) {
        Intent intent = new Intent(activity, A_Editext.class);
        intent.putExtra("titleText", titleText);
        intent.putExtra("hint", hint);
        intent.putExtra("content", content);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getStringExtra("titleText");
        setTitleText(title);
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_add);
        binding = setView(R.layout.a_edittext);
        initView();
    }

    private void initView() {
        String hint = getIntent().getStringExtra("hint");
        String content = getIntent().getStringExtra("content");
        binding.etContent.setHint(hint);
        binding.etContent.setText(content);
    }

    @Override
    protected void rightOneImageOnClick() {
        Intent intent = new Intent();
        intent.putExtra("content", binding.etContent.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
