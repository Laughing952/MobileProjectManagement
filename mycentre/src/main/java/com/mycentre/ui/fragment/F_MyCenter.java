package com.mycentre.ui.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;

import com.global.takephoto.uilts.RoundBitmap;
import com.mycentre.R;
import com.mycentre.databinding.FMycentreBinding;
import com.mycentre.ui.activity.A_My_Center_Personal_Info;
import com.mycentre.ui.mvpview.MyCentreView;
import com.mycentre.ui.presenter.MyCentrePresenter;
import com.mycentre.util.UploadImageUtils;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseFragment;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.UiUtil;

/**
 * 我的
 * Created by Water on 2018/3/28.
 */

public class F_MyCenter extends BaseFragment implements MyCentreView {

    private static F_MyCenter instance;
    private FMycentreBinding binding;
    private MyCentrePresenter centrePresenter;
    private PreferencesManager mPreferencesManager;

    public synchronized static Fragment newInstance() {
        if (instance == null) {
            synchronized (F_MyCenter.class) {
                if (instance == null)
                    instance = new F_MyCenter();
            }
        }
        return instance;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        binding = getBind();
        binding.view.setHeight(UiUtil.getStatusBarHeight(getContext()));
        binding.setClick(v -> centrePresenter.click(v));

    }

    @Override
    protected void initData() {
        super.initData();
        centrePresenter = new MyCentrePresenter(this, (BaseActivity) getActivity(), this);
        mPreferencesManager = PreferencesManager.getInstance(F_MyCenter.this.getActivity().getApplicationContext(), "photo");
        showCacheUserPhoto(mPreferencesManager.get("photoUrl"));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.f_mycentre;
    }

    /**
     * 退出登录
     */
    @Override
    public void loginOutResult() {
        try {
            Intent intent = new Intent(getContext(), Class.forName("com.login.ui.activity.A_Login"));
            intent.putExtra("isRefresh", false);
            getContext().startActivity(intent);
            getActivity().finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 准备拨打电话
     */
    @Override
    public void call() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 111);
        }

    }

    /**
     * 跳转到个人资料页面
     */
    @Override
    public void jump2PersonalInfoPage() {
        A_My_Center_Personal_Info.startActivity(getActivity());
    }

    /**
     * 拨打电话
     */
    private void makeCall() {
        Intent intent;
        intent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:10086"));//保证此处写的电话号码与xml中一致就可以了
        //                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 打开个人中心，加载本地缓存的头像
     */
    private void showCacheUserPhoto(String url) {

//        String app_user_head_url = mPreferencesManager.get("app_user_head_url", "");
//
//        if (app_user_head_url != "" && app_user_head_url != null) {
//            showUserHeadPhoto(app_user_head_url);
//        }
        if (!StrUtil.isEmpty(url)) {
//            UploadImageUtils.loadCircleImage(binding.ivMyCenterHeadImage, url);
            UploadImageUtils.loadRectangleImage(binding.ivMyCenterHeadImage, url);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPreferencesManager == null) {
            mPreferencesManager = PreferencesManager.getInstance(F_MyCenter.this.getActivity().getApplicationContext(), "photo");
        }
        showCacheUserPhoto(mPreferencesManager.get("photoUrl"));//加载图片
    }

    @Override
    public void onDetach() {
        super.onDetach();
        instance = null;
    }

    /**
     * 用来展示用户头像图片（从本地缓存中加载）
     *
     * @param path 图片上一次在本地存的位置
     */
    private void showUserHeadPhoto(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if (bitmap != null) {
            mPreferencesManager.put("app_user_head_url", path);
            LogUtil.e("laughing", "path---------->  " + path);
            binding.ivMyCenterHeadImage.setImageBitmap(new RoundBitmap().toRoundBitmap(bitmap));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 111) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LogUtil.e(getContext(), "ok");//代表用户同意了打电话的请求
                makeCall();//拨打电话
            } else {
                LogUtil.e(getContext(), "no permissions");
            }
        }
        /**输出的日志
         *   E/TAG:: ok
         E/TAG:: permissions=android.permission.CALL_PHONE
         E/TAG:: grantResults=0
         E/TAG:: requestCode=111
         */
        for (int i = 0; i < permissions.length; i++) {
            LogUtil.e(getContext(), "permissions=" + permissions[i]);
        }
        for (int i = 0; i < grantResults.length; i++) {
            LogUtil.e(getContext(), "grantResults=" + grantResults[i]);
        }
        LogUtil.e(getContext(), "requestCode=" + requestCode);
    }
}

