package com.unistrong.baidumaplibrary.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.unistrong.baidumaplibrary.R;
import com.unistrong.baidumaplibrary.databinding.ItemLocationBinding;
import com.unistrong.baidumaplibrary.rep.AttendanceSettingRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 地未打卡-选择打卡名称
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class Adapter_Location extends BaseAdapter<AttendanceSettingRep> {

    private ItemClickListener itemClickListener;

    public Adapter_Location() {

    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_location;
    }

    @Override
    public void convert(BaseViewHolder holder, AttendanceSettingRep data, int index) {
        ItemLocationBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewModel(data);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
