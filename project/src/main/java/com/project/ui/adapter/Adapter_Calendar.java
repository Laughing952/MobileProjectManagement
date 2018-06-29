package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.project.R;
import com.project.databinding.ItemCalendarBinding;
import com.project.ui.viewmodel.CalendarVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * Created by Water on 2018/5/7.
 */

public class Adapter_Calendar extends BaseAdapter<CalendarVM> {

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_calendar;
    }

    @Override
    public void convert(BaseViewHolder holder, CalendarVM data, int index) {
        ItemCalendarBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setCalendarVM(data);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null && data.isClick())
                itemClickListener.itemClick(v, data, index);
        });

    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
