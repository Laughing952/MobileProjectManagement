package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ItemAddProjectAttendanceBinding;
import com.unistrong.working.response.CreateNewTask_ProjectNameRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 新建任务-选择项目名称
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class Adapter_Add_Project_Attendance extends BaseAdapter<CreateNewTask_ProjectNameRep> {

    private ItemClickListener itemClickListener;

    public Adapter_Add_Project_Attendance() {

    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_add_project_attendance;
    }

    @Override
    public void convert(BaseViewHolder holder, CreateNewTask_ProjectNameRep data, int index) {
        ItemAddProjectAttendanceBinding binding = DataBindingUtil.bind(holder.itemView);
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
