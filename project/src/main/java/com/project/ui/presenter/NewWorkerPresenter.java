package com.project.ui.presenter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.global.even.RefreshEven;
import com.global.takephoto.activity.MyPhotoActivity;
import com.global.util.ImageUtils;
import com.google.gson.Gson;
import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.request.WorkerReq;
import com.project.response.ImageRep;
import com.project.response.ProjectHomeRep;
import com.project.response.WorkerRep;
import com.project.ui.activity.A_New_Worker;
import com.project.ui.activity.A_Sel_WorkerType;
import com.project.ui.mvpview.NewWorkerView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import okhttp3.MultipartBody;

/**
 * 新建/修改工人信息
 * Created by Water on 2018/5/14.
 */

public class NewWorkerPresenter {

    private static final String TAG = "NewWorkerPresenter";
    public static final int IDCARD_IMAGE = 11; //检查图片
    public static final int SEL_WORKERTYPE = 13; //检查图片

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private NewWorkerView mView;

    private WorkerReq workerReq;
    private String workerID;

    public NewWorkerPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, NewWorkerView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void initData(int flag, String workerID) {
        if (flag == A_New_Worker.NEW) {
            workerReq = new WorkerReq();
            mView.initData(workerReq);
        } else if (flag == A_New_Worker.UPDATA) {
            // 从服务器获取数据
            this.workerID = workerID;
            HttpManager manager = new HttpManager(activity, lifecycleProvider);
            manager.doHttpDeal(RetrofitHelper.getApiService().queryWorker(workerID)
                    , new DefaultObserver<WorkerReq>() {
                        @Override
                        public void onSuccess(WorkerReq workerReq) {
                            NewWorkerPresenter.this.workerReq = workerReq;
                            mView.initData(NewWorkerPresenter.this.workerReq);
                        }
                    });
        }
    }

    public void click(View v) {
        if (v.getId() == R.id.rl_workerType) {
            // TODO: 2018/5/14 选择工种
            A_Sel_WorkerType.startActivityForResult(activity, workerReq.getWorkerType(), SEL_WORKERTYPE);
        } else if (v.getId() == R.id.iv_selImage) {
            // 选择照片
            Intent intent = new Intent(activity, MyPhotoActivity.class);
            intent.putExtra("type", 2);
            activity.startActivityForResult(intent, IDCARD_IMAGE);
        } else if (v.getId() == R.id.tv_del) {
            // 删除工人
            HttpManager manager = new HttpManager(activity, lifecycleProvider);
            manager.doHttpDeal(RetrofitHelper.getApiService().delWorker(workerID)
                    , new DefaultObserver<Object>() {
                        @Override
                        public void onSuccess(Object workerReq) {
                            ToastUtil.showToast(activity, "删除工人成功");
                            activity.finish();
                            EventBus.getDefault().post(new RefreshEven());
                        }
                    });
        }
    }

    /**
     * 添加或者修改
     */
    public void request(int flag) {
        if (StrUtil.isEmpty(workerReq.getWorkerName())) {
            ToastUtil.showToast(activity, "工人名称不能为空");
            return;
        }
        if (StrUtil.isEmpty(workerReq.getWorkerIDCardNum()) || !StrUtil.isIDCardNum(workerReq.getWorkerIDCardNum())) {
            ToastUtil.showToast(activity, "请输入正确的证件号码");
            return;
        }
        if (StrUtil.isEmpty(workerReq.getWorkerMobile()) || !StrUtil.isMobileNo(workerReq.getWorkerMobile())) {
            ToastUtil.showToast(activity, "请输入正确的手机号码");
            return;
        }
//        if (workerReq.getWorkerType() == 0) {
//            ToastUtil.showToast(activity, "请选择工种");
//            return;
//        }
        if (StrUtil.isEmpty(workerReq.getSalary()) || Double.parseDouble(workerReq.getSalary()) == 0) {
            ToastUtil.showToast(activity, "请输入工人工资");
            return;
        }
        if (StrUtil.isEmpty(workerReq.getIDCardImg())) {
            ToastUtil.showToast(activity, "请选择证件照片");
            return;
        }
        LogUtil.d(TAG, "req:  " + new Gson().toJson(workerReq));
        if (flag == A_New_Worker.NEW) {
            //  新建工人
            HttpManager manager = new HttpManager(activity, lifecycleProvider);
            manager.doHttpDeal(RetrofitHelper.getApiService().newWorker(workerReq),
                    new DefaultObserver<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            ToastUtil.showToast(activity, "新增工人信息成功");
                            activity.finish();
                            EventBus.getDefault().post(new RefreshEven());
                        }
                    });
        } else if (flag == A_New_Worker.UPDATA) {
            // 修改工人
            HttpManager manager = new HttpManager(activity, lifecycleProvider);
            manager.doHttpDeal(RetrofitHelper.getApiService().updateWorker(workerReq),
                    new DefaultObserver<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            ToastUtil.showToast(activity, "修改工人成功");
                            activity.finish();
                            EventBus.getDefault().post(new RefreshEven());
                        }
                    });
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MyPhotoActivity.FINSH_RESULT && requestCode == IDCARD_IMAGE) {
            if (resultCode == MyPhotoActivity.FINSH_RESULT && data != null) {
                String path = data.getStringExtra("image");
                uploadImage(data.getData(), path);
            }
        }
        if (resultCode == activity.RESULT_OK && requestCode == SEL_WORKERTYPE) {
            String childId = data.getStringExtra("childId");
            String childConetnt = data.getStringExtra("childConetnt");
            mView.showWorkerType(childConetnt);
            workerReq.setWorkerType(childId);
        }
    }

    public void uploadImage(Uri data, String path) {
        // 上传图片
        mView.responseImage(path);
        MultipartBody.Part part = ImageUtils.fileToMultipartBodyPart(path);
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().uploadImage(part),
                new DefaultObserver<ImageRep>() {
                    @Override
                    public void onSuccess(ImageRep response) {
                        workerReq.setIDCardImg(response.getThumUrl());
                    }
                });
    }
}
