package com.project.ui.presenter;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.global.bean.Label;
import com.global.util.PopUtil;
import com.project.R;
import com.project.api.RetrofitHelper;
import com.project.databinding.PopSelstaterBinding;
import com.project.response.ImageRep;
import com.project.response.ProjectHomeRep;
import com.project.response.QualityListRep;
import com.project.ui.mvpview.QualityView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.PopupWindowUtile;
import com.waterbase.utile.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 质量检查
 * Created by Water on 2018/5/10.
 */

public class QualityPresenter {

    private static final String TAG = "QualityPresenter";

    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private QualityView mView;

    private int staterIndex;
    private String date;

    private int currentPage = 1;
    private int pageSize = 10;


    public QualityPresenter(BaseActivity activity, LifecycleProvider lifecycleProvider, QualityView mView) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
    }

    public void request() {
        // TODO: 2018/5/10 从服务区获取数据
        HttpManager manager = new HttpManager(activity, lifecycleProvider);
        manager.doHttpDeal(RetrofitHelper.getApiService().qualityList(mView.getPId(), staterIndex, date, currentPage, pageSize)
//        manager.doHttpDeal(RetrofitHelper.getApiService().qualityList(mView.getPId(), staterIndex, date)
                , new DefaultObserver<List<QualityListRep>>() {
                    @Override
                    public void onSuccess(List<QualityListRep> repList) {
                        if (currentPage == 1)
                            mView.responseList(repList);
                        else
                            mView.responseMoerList(repList);
                    }
                });
    }

    public void Refresh() {
        currentPage = 1;
        request();
    }

    public void loadmore() {
        currentPage += 1;
        request();
    }


    public void selStater(RelativeLayout title, View bg) {
        View view = PopupWindowUtile.showAdaptive3(activity, title, bg, R.layout.pop_selstater
                , PopupWindowUtile.BOTTOM, false, true);
        PopSelstaterBinding binding = DataBindingUtil.bind(view);
        binding.setClickNum(staterIndex);
        binding.setClick(v -> {
            if (v.getId() == R.id.tv_pass) {
                staterIndex = 1;
                binding.setClickNum(staterIndex);
                request();
                mView.selStaterResult("通过");
            } else if (v.getId() == R.id.tv_nopass) {
                staterIndex = 2;
                binding.setClickNum(staterIndex);
                request();
                mView.selStaterResult("不通过");
            } else if (v.getId() == R.id.tv_all) {
                staterIndex = 0;
                binding.setClickNum(staterIndex);
                request();
                mView.selStaterResult("全部");
            }
            PopupWindowUtile.closePopupWindow();
        });
    }

    public void selDate(RelativeLayout title, View bg) {

        PopUtil.selDate(activity, title, bg, new PopUtil.SelDateListener() {
            @Override
            public void result(Label year, Label month) {
                mView.selDateResult(year.getContent() + month.getContent());
                date = String.valueOf(year.getId()) +"-"+ String.valueOf(month.getId());
            }

            @Override
            public void cancel() {
                mView.selDateResult("所有时间");
                date = null;
            }
        });
    }
}
