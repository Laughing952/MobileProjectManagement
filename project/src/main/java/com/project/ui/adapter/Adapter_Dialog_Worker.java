package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.project.R;
import com.project.databinding.ItemWorkerBinding;
import com.project.response.WorkerRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * Created by Water on 2018/5/24.
 */

public class Adapter_Dialog_Worker extends BaseAdapter<WorkerRep> {

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_worker;
    }

    @Override
    public void convert(BaseViewHolder holder, WorkerRep data, int index) {
        ItemWorkerBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setWorker(data);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
