package com.unistrong.baidumaplibrary.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.unistrong.baidumaplibrary.R;
import com.unistrong.baidumaplibrary.bean.MyPoi;
import com.unistrong.baidumaplibrary.databinding.ItemAttendanceLocationBinding;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 考勤范围选择
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class Adapter_Attendance_Location extends BaseAdapter<MyPoi> {

    private ItemClickListener itemClickListener;

    public Adapter_Attendance_Location() {

    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_attendance_location;
    }

    @Override
    public void convert(BaseViewHolder holder, MyPoi data, int index) {
        ItemAttendanceLocationBinding binding = DataBindingUtil.bind(holder.itemView);
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
