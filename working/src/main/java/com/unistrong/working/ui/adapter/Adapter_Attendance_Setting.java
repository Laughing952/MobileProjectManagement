package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ItemAttendanceSettingBinding;
import com.unistrong.working.response.AttendanceSettingRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 考勤设置
 * 作者：Laughing on 2018/5/23 10:10
 */


public class Adapter_Attendance_Setting extends BaseAdapter<AttendanceSettingRep> {

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_attendance_setting;
    }

    @Override
    public void convert(BaseViewHolder holder, AttendanceSettingRep data, int index) {
        ItemAttendanceSettingBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewmodel(data);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);

        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}

