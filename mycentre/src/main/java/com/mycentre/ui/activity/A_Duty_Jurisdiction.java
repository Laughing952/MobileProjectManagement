package com.mycentre.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.RecyclerviewBinding;
import com.mycentre.response.DutyRep;
import com.mycentre.ui.adapter.Adapter_Duty_Jurisdiction;
import com.mycentre.ui.mvpview.DutyJurisdictionView;
import com.mycentre.ui.presenter.DutyJurisdictionPresenter;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ToastUtil;

import java.util.Collections;
import java.util.List;

/**
 * 职务权限
 * Created by Water on 2018/5/9.
 */

public class A_Duty_Jurisdiction extends TitleActivity implements DutyJurisdictionView {

    private RecyclerviewBinding binding;
    private DutyJurisdictionPresenter presenter;
    private Adapter_Duty_Jurisdiction mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    private List<DutyRep> datas;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_Duty_Jurisdiction.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRightOneImageVisibity(true);
        setRightOneImagePic(R.mipmap.ic_add);
        setTitleText("职务权限");
        binding = setView(R.layout.recyclerview);
        initView();
        initData();
    }

    protected void initView() {
        binding.swiperefreshlayout.setOnRefreshListener(() -> presenter.requestList());
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new Adapter_Duty_Jurisdiction();
        mAdapter.addHeadLayout(R.layout.head_duty_jurisdiction);
        mAdapter.addFootLayout(R.layout.foot_duty_jurisdiction);
        mAdapter.setMyClickListener(new Adapter_Duty_Jurisdiction.MyClickListener<DutyRep>() {
            @Override
            public void updata(View view, DutyRep dutyRep, int index) {
                A_New_Duty.startActivity(A_Duty_Jurisdiction.this, dutyRep, A_New_Duty.UPDATA);
            }

            @Override
            public void del(View view, DutyRep dutyRep, int index) {
                new AlertDialog.Builder(A_Duty_Jurisdiction.this)
                        .setTitle("提示：")
                        .setMessage("您将删除该项职务?")
                        .setPositiveButton("确定", (dialog, which) -> presenter.del(dutyRep))
                        .setNegativeButton("取消", null)
                        .create()
                        .show();

            }

            @Override
            public void move(RecyclerView.ViewHolder vh, DutyRep dutyRep, int index) {
                //判断被拖拽的是否是第一项和最后一项，如果不是则执行拖拽
                if (index != 0 && index != mAdapter.getItemCount() - 1) {
                    mItemTouchHelper.startDrag(vh);
                }
            }
        });
        binding.recyclerview.setAdapter(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                /*
                这个方法是设置是否滑动，以及拖拽的方向，所以在这里需要判断一下是列表布局还是网格布局
                ，如果是列表布局的话则拖拽方向为DOWN和UP
                ，如果是网格布局的话则是DOWN和UP和LEFT和RIGHT，对应这个方法的代码如下：
                 */
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                /*
                在拖动的时候不断回调的方法，在这里我们需要将正在拖拽的item和集合的item进行交换元素，然后在通知适配器更新数据，
                 */
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        // 应为这里添加个一个尾部
                        if (i < datas.size() + mAdapter.getHeadSize() - 1)
                            Collections.swap(datas, i - mAdapter.getHeadSize(), i);
                        else
                            return false;
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        // 应为这里添加个一个头部
                        if (i > mAdapter.getHeadSize())
                            Collections.swap(datas, i, i - 2);
                        else
                            return false;
                    }
                }
                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                LogUtil.d(TAG, "==================");
//                mAdapter.setData(datas);
            }

            /**
             * 长按选中Item的时候开始调用
             *
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
                mAdapter.setData(datas);
                presenter.newOrder(datas);
            }
        });
        mItemTouchHelper.attachToRecyclerView(binding.recyclerview);

    }


    private void initData() {
        presenter = new DutyJurisdictionPresenter(this, this, this);
        presenter.requestList();
    }

    @Override
    protected void rightOneImageOnClick() {
        A_New_Duty.startActivity(this, A_New_Duty.NEW);
    }

    @Override
    public void responseList(List<DutyRep> dutyReps) {
        datas = dutyReps;
        mAdapter.setData(dutyReps);
    }

    @Override
    public void orderReult() {
        ToastUtil.showToast(this, "排序成功!");
    }

    @Override
    public void delResult(DutyRep dutyRep) {
        mAdapter.removeData(dutyRep);
    }
}
