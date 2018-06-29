package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.ui.activity.TitleActivity;
import com.google.gson.Gson;
import com.project.R;
import com.project.databinding.AAddMeritBinding;
import com.project.response.WorkerRep;
import com.project.ui.adapter.Adapter_Add_Merit;
import com.project.ui.mvpview.AddMeritView;
import com.project.ui.presenter.AddMeritPresenter;
import com.project.ui.viewmodel.CalendarVM;
import com.waterbase.utile.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加/编辑记功
 * Created by Water on 2018/5/18.
 */

public class A_Add_Merit extends TitleActivity implements AddMeritView {

    private AAddMeritBinding binding;
    private AddMeritPresenter presenter;
    private Adapter_Add_Merit adapterAddMerit;

    public static void startActivity(Context context, String year, String month, String day) {
        Intent intent = new Intent(context, A_Add_Merit.class);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText("编辑记功");
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_add);
        binding = setView(R.layout.a_add_merit);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        presenter = new AddMeritPresenter(this, this, this);
        presenter.initDate(getIntent());
        binding.rvMerit.setLayoutManager(new LinearLayoutManager(this));
        adapterAddMerit = new Adapter_Add_Merit();
        adapterAddMerit.setDelListener(new Adapter_Add_Merit.DelListener<WorkerRep>() {
            @Override
            public void del(View v, WorkerRep workerRep, int index) {
                presenter.removeData(workerRep);
            }
        });
        binding.rvMerit.setAdapter(adapterAddMerit);
    }

    private void initListener() {
        binding.setClick(v -> presenter.click(v));
    }


    private void initData() {
        presenter.initWorkerList();
    }

    @Override
    protected void rightOneImageOnClick() {
        super.rightOneImageOnClick();
        presenter.save(adapterAddMerit.getData());
    }

    @Override
    public void initDate(String date) {
        binding.setDate(date);
    }

    @Override
    public void initNormalHours(String normalHours) {
        binding.setNormalHours(normalHours);
    }

    @Override
    public void initWorkerList(List<WorkerRep> workerRepList) {
        adapterAddMerit.setData(workerRepList);
    }

    @Override
    public ArrayList<WorkerRep> getAdapterData() {
        return (ArrayList<WorkerRep>) adapterAddMerit.getData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
