package com.project.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.global.even.RefreshEven;
import com.global.listener.ItemClickListener;
import com.global.ui.activity.TitleActivity;
import com.project.R;
import com.project.databinding.AWorkersListBinding;
import com.project.response.WorkerRep;
import com.project.ui.adapter.Adapter_WorkerList;
import com.project.ui.mvpview.WorkersListView;
import com.project.ui.presenter.WorkersListPresenter;
import com.project.widget.CNPinyin;
import com.project.widget.CharIndexView;
import com.project.widget.Contact;
import com.project.widget.StickyHeaderDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的工人名册
 * Created by Water on 2018/5/14.
 */

public class A_Workers_List extends TitleActivity implements WorkersListView {

    private String pId;
    private AWorkersListBinding binding;
    public static final int SEL = 90;
    private int flag;

    private WorkersListPresenter presenter;
    private LinearLayoutManager manager;
    private Adapter_WorkerList adapter;

    private ArrayList<WorkerRep> selReqList;

    private int checkNum = 0;

    public static void startActivity(Context context, String pId) {
        Intent intent = new Intent(context, A_Workers_List.class);
        intent.putExtra("pId", pId);
        context.startActivity(intent);
    }

    public static void startActivityForResult(Activity context, String pId, ArrayList<WorkerRep> selReqList, int flag, int requestCode) {
        Intent intent = new Intent(context, A_Workers_List.class);
        intent.putExtra("pId", pId);
        intent.putExtra("selReqList", selReqList);
        intent.putExtra("flag", flag);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        pId = getIntent().getStringExtra("pId");
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_add);
        setTitleText("我的工人");
        binding = setView(R.layout.a_workers_list);
        initView();
        initListener();
        initData();
    }


    private void initView() {
        flag = getIntent().getIntExtra("flag", -1);
        if (flag == SEL) {
            binding.tvOk.setVisibility(View.VISIBLE);
        } else {
            binding.tvOk.setVisibility(View.GONE);
        }
        manager = new LinearLayoutManager(this);
        binding.rvMain.setLayoutManager(manager);
        adapter = new Adapter_WorkerList(flag);

        binding.rvMain.setAdapter(adapter);
        binding.rvMain.addItemDecoration(new StickyHeaderDecoration(adapter));
    }


    private void initListener() {
        binding.ivMain.setOnCharIndexChangedListener(new CharIndexView.OnCharIndexChangedListener() {
            @Override
            public void onCharIndexChanged(char currentIndex) {
                presenter.roll(currentIndex);
            }

            @Override
            public void onCharIndexSelected(String currentIndex) {
                if (currentIndex == null) {
                    binding.tvIndex.setVisibility(View.INVISIBLE);
                } else {
                    binding.tvIndex.setVisibility(View.VISIBLE);
                    binding.tvIndex.setText(currentIndex);
                }
            }
        });
        adapter.setCheckBoxListener((isChecked) -> {
            if (isChecked)
                checkNum += 1;
            else
                checkNum -= 1;
            binding.tvOk.setText("确认(" + checkNum + "人)");
        });

        adapter.setItemClickListener(new ItemClickListener<WorkerRep>() {
            @Override
            public void itemClick(View v, WorkerRep workerRep, int index) {
                A_New_Worker.startActivity(A_Workers_List.this, workerRep.getWorkweId(), A_New_Worker.UPDATA);
            }
        });

        binding.tvOk.setOnClickListener(v -> {
            // TODO: 2018/5/21
            ArrayList<WorkerRep> repList = new ArrayList<>();
            for (CNPinyin<WorkerRep> pinyin : adapter.getCnPinyinList()) {
                if (pinyin.data.isCheck()) {
                    repList.add(pinyin.data);
                }
            }
            Intent intent = new Intent();
            intent.putExtra("checkedList", repList);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void initData() {
        presenter = new WorkersListPresenter(this, this, this);
        selReqList = (ArrayList<WorkerRep>) getIntent().getSerializableExtra("selReqList");
        if (selReqList == null) {
            binding.tvOk.setText("确认(" + 0 + "人)");
        } else {
            checkNum = selReqList.size();
            binding.tvOk.setText("确认(" + checkNum + "人)");
        }
        presenter.initData(selReqList);
    }

    @Override
    protected void rightOneImageOnClick() {
        A_New_Worker.startActivity(this, null, A_New_Worker.NEW);
    }

    @Override
    public void responseList(List<CNPinyin<WorkerRep>> pinyinList) {
        adapter.setData(pinyinList);
    }

    @Override
    public void rollRV(int position, int offset) {
        manager.scrollToPositionWithOffset(position, offset);
    }

    @Override
    public String getPId() {
        return pId;
    }

    @Subscribe
    public void evenRefresh(RefreshEven refreshEven){
        presenter.initData(selReqList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
