package com.mycentre.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.mycentre.R;
import com.mycentre.databinding.ItemDutySelBinding;
import com.mycentre.response.DutyRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 职务选择
 * Created by Water on 2018/5/9.
 */

public class Adapter_Duty_Sel extends BaseAdapter<DutyRep> {

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_duty_sel;
    }

    @Override
    public void convert(BaseViewHolder holder, DutyRep data, int index) {
        ItemDutySelBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setDutyrep(data);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
