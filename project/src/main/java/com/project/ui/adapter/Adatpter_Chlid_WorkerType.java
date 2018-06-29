package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.project.R;
import com.project.databinding.ItemChlidWorkertypeBinding;
import com.project.response.WorkerTypeChildRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 工种子布局
 * Created by Water on 2018/5/14.
 */

public class Adatpter_Chlid_WorkerType extends BaseAdapter<WorkerTypeChildRep> {

    private ItemClickListener itemClickListener;
    

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_chlid_workertype;
    }

    @Override
    public void convert(BaseViewHolder holder, WorkerTypeChildRep data, int index) {
        ItemChlidWorkertypeBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setChildRep(data);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
