package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.activity.TitleActivity;
import com.global.util.TransformUtil;
import com.global.util.UserIdUtil;
import com.global.viewmodel.MyCenterSexViewModel;
import com.mycentre.R;
import com.mycentre.databinding.AMyCenterPersonalInfoBinding;
import com.mycentre.response.ImageRep;
import com.mycentre.ui.mvpview.PersonalInfoView;
import com.mycentre.ui.presenter.PersonalInfoPresenter;
import com.mycentre.ui.viewmodel.PersonInfoVM;
import com.mycentre.util.UploadImageUtils;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.ToastUtil;

/**
 * 个人中心-个人资料 包括：头像，姓名，性别，备注
 * 作者：Laughing on 2018/5/2 11:01
 * 邮箱：719240226@qq.com
 */

public class A_My_Center_Personal_Info extends TitleActivity implements PersonalInfoView<PersonInfoVM> {

    private AMyCenterPersonalInfoBinding mBinding;
    private PersonalInfoPresenter mPresenter;
    private PersonInfoVM personInfoVM;
    private PreferencesManager mPreferencesManager;
    private Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle();
        mBinding = setView(R.layout.a_my_center_personal_info);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//软键盘弹出后，让屏幕整体上移
        initData();
        initListener();
    }

    private void initListener() {
        mBinding.setClick(v -> mPresenter.click(v));
    }

    private void initData() {
        mPresenter = new PersonalInfoPresenter(this, this, this);
        mPresenter.getPersonInfo(UserIdUtil.getUserIdLong());
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_My_Center_Personal_Info.class));
    }

    public void initTitle() {
        setTitleText("个人资料");//标题
    }

    // 性别填到控件上
    @Override
    public void setSex2View(MyCenterSexViewModel model) {
        mBinding.tvPersonalInfoEditSex.setText(model.getSex());
        personInfoVM.setSex(TransformUtil.sexStrTransformNum(model.getSexReq()));
    }

    @Override
    public void savePersonalInfoSuccess() {
        ToastUtil.showToast(this, "已保存");
        finish();
    }

    @Override
    public PersonInfoVM getPersonInfoVM() {
        return personInfoVM;
    }

    /**
     * 通过手机相册或者拍照获取一张头像
     */
    @Override
    public void chooseHeadImage() {
        Intent intent = new Intent(this, MyPhotoActivity.class);
        intent.putExtra("type", 1);//出入参数：1：圆形图像，2：方型图像
        startActivityForResult(intent, 0x04);
    }

    @Override
    public void showPersonInfo(PersonInfoVM infoRep) {

        personInfoVM = infoRep;
        personInfoVM.setSexShow(TransformUtil.sexTransformStr(personInfoVM.getSex()));
        mBinding.setViewmodel(personInfoVM);//绑定数据
        //初始化图像显示
        if (personInfoVM.getImageRep() != null) {
            UploadImageUtils.loadCircleImage(mBinding.ivPersonalInfoHead, personInfoVM.getImageRep().getShowImageUrl());
        }


    }


    /**
     * 图片选择完成后显示图片
     */
    @Override
    public void resultImageUrl(ImageRep imageRep) {
        UploadImageUtils.loadCircleImage(mBinding.ivPersonalInfoHead, imageRep.getShowImageUrl());
        personInfoVM.setImageRep(imageRep);
        savePhotoPath(imageRep.getShowImageUrl());
    }

    /**
     * 拍完照片后返回的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0x04:
                if (resultCode == MyPhotoActivity.FINSH_RESULT && data != null) {
                    String path = data.getStringExtra("image");
                    //显示图片
//                    Glide.with(mContext).load(path).into(mBinding.ivPersonalInfoHead);//测试图片是否可以加载成功
                    UploadImageUtils.loadCircleImage(mBinding.ivPersonalInfoHead, path);
                    savePhotoPath(path);

                    //图片上传
                    mPresenter.uploadImage(path);
                }
                break;
        }
    }

    public void savePhotoPath(String url) {
        if (mPreferencesManager == null) {
            mPreferencesManager = PreferencesManager.getInstance(this, "photo");
        }
        mPreferencesManager.put("photoUrl", url);
    }

}
