package com.mycentre.ui.presenter;

import android.view.View;

import com.global.util.ImageUtils;
import com.global.util.PickerViewUtil;
import com.global.util.RequestTransformUtil;
import com.global.util.TransformUtil;
import com.global.viewmodel.MyCenterSexViewModel;
import com.mycentre.R;
import com.mycentre.api.RetrofitHelper;
import com.mycentre.response.ImageRep;
import com.mycentre.ui.mvpview.PersonalInfoView;
import com.mycentre.ui.viewmodel.PersonInfoVM;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import java.util.ArrayList;

import okhttp3.MultipartBody;

/**
 * 个人资料
 * 作者：Laughing on 2018/5/2 14:54
 * 邮箱：719240226@qq.com
 */

public class PersonalInfoPresenter {

    private PersonalInfoView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;

    public PersonalInfoPresenter(PersonalInfoView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    /**
     * 获取个人资料信息
     * */
    public void getPersonInfo(long id) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().getPersonInfo(id),
                new DefaultObserver<PersonInfoVM>() {
                    @Override
                    public void onSuccess(PersonInfoVM response) {
                        mView.showPersonInfo(response);
                    }
                });
    }

    /**
     * 上传图片
     * */
    public void uploadImage(String imgPath) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        MultipartBody.Part part = ImageUtils.fileToMultipartBodyPart(imgPath);
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadImage(part),
                new DefaultObserver<ImageRep>() {
                    @Override
                    public void onSuccess(ImageRep response) {
                        mView.resultImageUrl(response);
                        LogUtil.d("cuihao......",response.toString());
                    }
                });
    }

    public void click(View view) {
        if (view.getId() == R.id.rl_personal_center_phone) {
            //通过手机相册或者拍照获取一张头像
            //  选择头像
            mView.chooseHeadImage();
            ToastUtil.showToast(activity, "click--->");
        } else if (view.getId() == R.id.tv_personal_info_edit_sex) {
            //  选择性别
            chooseSex();
        } else if (view.getId() == R.id.tv_personal_info_save) {
            // 保存个人资料
            save();
        }

    }


    private void save() {
        PersonInfoVM personInfoVM = mView.getPersonInfoVM();
        if (StrUtil.isEmpty(personInfoVM.getUsername())) {
            ToastUtil.showToast(activity, "姓名不能为空");
            return;
        }
        if (StrUtil.isEmpty(personInfoVM.getSex())) {
            ToastUtil.showToast(activity, "性别不能为空");
            return;
        }
        if (!StrUtil.isIDCardNum(personInfoVM.getCardNo())) {
            ToastUtil.showToast(activity, "身份证号有误");
            return;
        }

        // TODO: 2018/5/4 保存个人资料
        savePersonalInfo(personInfoVM);
    }

    private void savePersonalInfo(PersonInfoVM personInfoVM) {
        HttpManager httpManager = new HttpManager(activity, lifecycleProvider);
        personInfoVM.setSex(TransformUtil.sexStrTransformNum(personInfoVM.getSexShow()));
        httpManager.doHttpDeal(RetrofitHelper.getApiService().uploadPersonInfo(personInfoVM),
                new DefaultObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.savePersonalInfoSuccess();//保存成功
                    }
                });
    }

    //  选择性别
    private void chooseSex() {
        ArrayList<MyCenterSexViewModel> mSexList = RequestTransformUtil.initSexData();
        PickerViewUtil.showOptionsPickerView(activity, "选择性别", new PickerViewUtil.SelectResultListener<MyCenterSexViewModel>() {
            @Override
            public void result(MyCenterSexViewModel model) {
                mView.setSex2View(model);
            }

        }, mSexList);
    }

}
