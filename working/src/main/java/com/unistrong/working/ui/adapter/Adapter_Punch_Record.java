package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.global.listener.ItemLongClickListener;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ItemPunchRecordBinding;
import com.unistrong.working.response.PunchRecordRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 打卡记录
 * 作者：Laughing on 2018/5/19 16:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_Punch_Record extends BaseAdapter<PunchRecordRep> {


    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_punch_record;
    }

    @Override
    public void convert(BaseViewHolder holder, PunchRecordRep data, int index) {

//        GeneralApprovalItemVM viewModel = new GeneralApprovalItemVM (data);
        ItemPunchRecordBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setViewmodel(data);

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }


    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}

