package com.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.format.DateUtils;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.ARecordMeritBinding;
import com.project.ui.adapter.Adapter_Calendar;
import com.project.ui.mvpview.RecordMeritView;
import com.project.ui.presenter.RecordMeritPresenter;
import com.project.ui.viewmodel.CalendarVM;
import com.project.util.CalendarUtiles;
import com.waterbase.utile.LogUtil;

import java.util.List;

/**
 * 记工
 * Created by Water on 2018/5/7.
 */

public class A_Record_Merit extends TitleActivity implements RecordMeritView {

    private String pId;
    private String pName;
    private ARecordMeritBinding binding;
    private Adapter_Calendar adapterCalendar;

    private RecordMeritPresenter presenter;

    public static void startActivity(Context context, String pId, String pName) {
        Intent intent = new Intent(context, A_Record_Merit.class);
        intent.putExtra("pId", pId);
        intent.putExtra("pName", pName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pId = getIntent().getStringExtra("pId");
        pName = getIntent().getStringExtra("pName");
        setTitleText("记工");
        binding = setView(R.layout.a_record_merit);
        initView();
        initListener();
        initData();
    }


    protected void initView() {
        binding.rvCalendar.setLayoutManager(new GridLayoutManager(this, 7));
        adapterCalendar = new Adapter_Calendar();
        binding.rvCalendar.setAdapter(adapterCalendar);

    }


    private void initListener() {
        adapterCalendar.setItemClickListener(new ItemClickListener<CalendarVM>() {
            @Override
            public void itemClick(View v, CalendarVM calendarVM, int index) {
                LogUtil.d(TAG, "calendarVM:" + calendarVM.getDay());
                A_Add_Merit.startActivity(A_Record_Merit.this,String.valueOf(calendarVM.getYear())
                        ,String.valueOf(calendarVM.getMonth()),calendarVM.getDay());
            }
        });

        binding.setClick(v -> {
            presenter.click(v);

        });

    }

    protected void initData() {
        presenter = new RecordMeritPresenter(this,this,this);
        presenter.initCalendar();

    }



    @Override
    public void initCalendar(List<CalendarVM> calendarVMList) {
        adapterCalendar.setData(calendarVMList);
    }

    @Override
    public void initDate(String dateStr) {
        binding.tvDate.setText(dateStr);
    }

    @Override
    public String getPId() {
        return pId;
    }
}
