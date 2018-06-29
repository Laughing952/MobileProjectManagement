package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.ui.adapter.GridViewAddImageAdapter2;
import com.project.R;
import com.project.databinding.ANewQualityBinding;
import com.project.response.ImageRep;
import com.project.response.QualityListRep;
import com.project.ui.mvpview.NewQualityView;
import com.project.ui.presenter.NewQualityPresenter;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ViewUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 新建/编辑质量检查
 * Created by Water on 2018/5/10.
 */

public class A_New_Quality extends TitleActivity implements NewQualityView {

    public static final int NEW = 67; // 新建
    public static final int UPDATA = 68; // 修改

    public static final int EXAMINE_IMAGE = 11; //检查图片
    public static final int EXAMINE_ITEMS = 12; // 检查项
    public static final int EXAMINE_RESULT = 13;  //检查结果


    private int flag;
    private ANewQualityBinding binding;
    private GridViewAddImageAdapter2 imageAdapter;
    private NewQualityPresenter presenter;
    private QualityListRep rep;

    public static void startActivity(Context context,String pId, int flag) {
        startActivity(context, pId,null, flag);
    }

    public static void startActivity(Context context,String pId, QualityListRep qualityListRep, int flag) {
        Intent intent = new Intent(context, A_New_Quality.class);
        intent.putExtra("flag", flag);
        intent.putExtra("pId", pId);
        intent.putExtra("qualityListRep", qualityListRep);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_ok);
        flag = getIntent().getIntExtra("flag", -1);
        if (flag == NEW) {
            setTitleText("新建质量检查");
        } else if (flag == UPDATA) {
            setTitleText("修改质量检查");
        }
        binding = setView(R.layout.a_new_quality);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        presenter.initData(rep);
    }

    private void initListener() {
        binding.gvImage.setOnItemClickListener((parent, view, position, id) -> {
            startActivityForResult(new Intent(this, MyPhotoActivity.class), EXAMINE_IMAGE);
        });
        binding.setClick(v -> presenter.click(v));
    }

    private void initView() {
        presenter = new NewQualityPresenter(this, this, this);
        imageAdapter = new GridViewAddImageAdapter2<ImageRep>(this);
        binding.gvImage.setAdapter(imageAdapter);
        if (flag == UPDATA) {
            rep = (QualityListRep) getIntent().getSerializableExtra("qualityListRep");
            showDate(rep.getE_date());
            showType(rep.getE_divisionalName());
            showItems(rep.getE_items());
            showResultInfo(rep.getE_resultContent());
            if (rep.getE_result() == 1)
                showResult(true);
            else
                showResult(false);
            showImageList(rep.getImageRepList());
        }
        initData();
    }

    @Override
    protected void rightOneImageOnClick() {
        presenter.request(flag,getIntent().getStringExtra("pId"),imageAdapter.getDatas());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    private void showImageList(List<ImageRep> imageRepList) {
        imageAdapter.setData(imageRepList);
        setGridViewHeight();
    }

    @Override
    public void responseImage(ImageRep imageRep) {
        imageAdapter.addData(imageRep);
        setGridViewHeight();
    }

    @Override
    public void showType(String content) {
        binding.tvExamineType.setText(content);
    }

    @Override
    public void showDate(String content) {
        binding.tvExamineDate.setText(content);
    }

    @Override
    public void showItems(String content) {
        binding.tvExamineItems.setText(content);
    }

    @Override
    public void showResultInfo(String content) {
        binding.tvExamineResult.setText(content);
    }

    @Override
    public void showResult(boolean is) {
        if (is)
            binding.ivCheckBox.setImageResource(R.mipmap.ic_turn_on);
        else
            binding.ivCheckBox.setImageResource(R.mipmap.ic_turn_off);
    }

    private void setGridViewHeight() {
        int line = (int) Math.ceil(imageAdapter.getCount() / 4f);
        LogUtil.d(TAG, "line  " + line);
        int height = (int) ViewUtil.dp2px(this, 80) * line;
        LogUtil.d(TAG, "height  " + height);
        ViewUtil.setViewHeight(binding.gvImage, height);
    }
}
