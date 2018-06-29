package com.project.ui.presenter;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.global.even.RefreshEven;
import com.global.listener.ItemClickListener;
import com.global.takephoto.activity.MyPhotoActivity;
import com.global.ui.adapter.Adapter_TextList;
import com.global.util.ImageUtils;
import com.global.util.PickerViewUtil;
import com.google.gson.Gson;
import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.request.NewQualityReq;
import com.project.response.ImageRep;
import com.project.response.ExamineTypeRep;
import com.project.response.QualityListRep;
import com.project.ui.activity.A_Editext;
import com.project.ui.activity.A_New_Quality;
import com.project.ui.mvpview.NewQualityView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.StrUtil;
import com.waterbase.utile.ToastUtil;
import com.waterbase.widget.ListDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * 新建/编辑质量检查
 * Created by Water on 2018/5/10.
 */

public class NewQualityPresenter {

    private static final String TAG = "QualityPresenter";

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private NewQualityView mView;
    private List<ExamineTypeRep> examineTypeRepList; // 检查类型
    private NewQualityReq req; // 提交保存和修改的请求参数

    public NewQualityPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, NewQualityView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;

    }

    public void initData(QualityListRep rep) {
        req = new NewQualityReq(rep);
    }

    public void uploadImage(Uri data, String path) {
        // 上传图片
//        mView.responseImage(new ImageRep(path));
        MultipartBody.Part part = ImageUtils.fileToMultipartBodyPart(path);
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().uploadImage(part),
                new DefaultObserver<ImageRep>() {
                    @Override
                    public void onSuccess(ImageRep response) {
                        mView.responseImage(response);
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case A_New_Quality.EXAMINE_IMAGE:
                if (resultCode == MyPhotoActivity.FINSH_RESULT && data != null) {
                    String path = data.getStringExtra("image");
                    uploadImage(data.getData(), path);
                }
                break;
            case A_New_Quality.EXAMINE_ITEMS:
                if (resultCode == activity.RESULT_OK && data != null) {
                    String items = data.getStringExtra("content");
                    req.setExamineItems(items);
                    mView.showItems(items);
                }
                break;
            case A_New_Quality.EXAMINE_RESULT:
                if (resultCode == activity.RESULT_OK && data != null) {
                    String result = data.getStringExtra("content");
                    req.setExamineResultInfo(result);
                    mView.showResultInfo(result);
                }
                break;
        }
    }

    public void click(View v) {
        if (v.getId() == R.id.rl_examine_type) {
            // 类型选择
            selType();
        } else if (v.getId() == R.id.rl_examine_date) {
            // 日期选择
            PickerViewUtil.showSelectDatePickerViewBefore(activity, "选择日期", null
                    , dateStr -> {
                        req.setExamineDate(dateStr);
                        mView.showDate(dateStr);
                    });
        } else if (v.getId() == R.id.ll_examine_items) {
            // 检查项
            A_Editext.startActivityForResult(activity, "检查项", req.getExamineItems()
                    , "请填写检查项", A_New_Quality.EXAMINE_ITEMS);
        } else if (v.getId() == R.id.ll_examine_result) {
            // 检查结果
            A_Editext.startActivityForResult(activity, "检查结果", req.getExamineResultInfo()
                    , "请填写检查结果", A_New_Quality.EXAMINE_RESULT);
        } else if (v.getId() == R.id.iv_checkBox) {
            // 是否通过
            if (req.getExamineResult() == 1) {
                mView.showResult(false);
                req.setExamineResult(2);
            } else {
                mView.showResult(true);
                req.setExamineResult(1);
            }
        }
    }

    /**
     * 类型选择
     */
    private void selType() {
        if (examineTypeRepList == null) {
            HttpManager manager = new HttpManager(activity, lifecycleProvider);
            manager.doHttpDeal(RetrofitHelper.getApiService().queryType(),
                    new DefaultObserver<List<ExamineTypeRep>>() {
                        @Override
                        public void onSuccess(List<ExamineTypeRep> typeReps) {
                            examineTypeRepList = typeReps;
                            showType();
                        }
                    });
        } else
            showType();
    }

    private void showType() {
        Adapter_TextList adapterDutySel = new Adapter_TextList<ExamineTypeRep>();
        adapterDutySel.setData(examineTypeRepList);
        ListDialog listDialog = new ListDialog.Builder(activity)
                .setTitle("请选择")
                .setAdapter(adapterDutySel)
                .create();
        listDialog.show();
        adapterDutySel.setItemClickListener(new ItemClickListener<ExamineTypeRep>() {
            @Override
            public void itemClick(View v, ExamineTypeRep rep, int index) {
                req.setExamineTypeiD(rep.getId());
                mView.showType(rep.getName());
                listDialog.dismiss();
            }
        });
    }

    /**
     * 新增、修改
     *
     * @param flag
     */
    public void request(int flag, String pid, List<ImageRep> imageList) {
        if (StrUtil.isEmpty(req.getExamineTypeiD())) {
            ToastUtil.showToast(activity, "请选择检查类型");
            return;
        }
        if (StrUtil.isEmpty(req.getExamineTypeiD())) {
            ToastUtil.showToast(activity, "请选择检查日期");
            return;
        }
        if (StrUtil.isEmpty(req.getExamineItems())) {
            ToastUtil.showToast(activity, "请填写检查项");
            return;
        }
        req.setP_ID(pid);
        req.setImageList(imageList);
        req.setCreater(PreferencesManager.getInstance(BaseApplication.getAppContext()).get("userId"));
        if (flag == A_New_Quality.NEW) {
            // 新建
            if (req.getExamineResult() == 0)
                req.setExamineResult(2);
            LogUtil.d(TAG, "新增req:  " + new Gson().toJson(req));
            HttpManager manager = new HttpManager(activity, lifecycleProvider);
            manager.doHttpDeal(RetrofitHelper.getApiService().newQuality(req),
                    new DefaultObserver<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            showAbarbeitung();
                        }
                    });
        } else if (flag == A_New_Quality.UPDATA) {
            // 修改
            HttpManager manager = new HttpManager(activity, lifecycleProvider);
            manager.doHttpDeal(RetrofitHelper.getApiService().updateQuality(req),
                    new DefaultObserver<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            showAbarbeitung();
                        }
                    });
        }
    }

    private void showAbarbeitung() {
        if (req.getExamineResult() == 1) {
            activity.finish();
            EventBus.getDefault().post(new RefreshEven());
        } else
            new AlertDialog.Builder(activity)
                    .setTitle("提示:")
                    .setMessage("安排整改?")
                    .setPositiveButton("马上安排", (dialog, which) -> {
                        // TODO: 2018/5/11
                        ToastUtil.showToast(activity, "还没实现该功能");
                    })
                    .setNegativeButton("暂不安排", (dialog, which) -> {
                        activity.finish();
                        EventBus.getDefault().post(new RefreshEven());
                    })
                    .create()
                    .show();
    }

}
