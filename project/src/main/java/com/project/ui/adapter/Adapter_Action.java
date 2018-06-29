package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.project.R;
import com.project.bean.ActionBean;
import com.project.databinding.ItemActionBinding;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * Created by Water on 2018/5/7.
 */

public class Adapter_Action extends BaseAdapter<ActionBean> {

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_action;
    }

    @Override
    public void convert(BaseViewHolder holder, ActionBean data, int index) {
        ItemActionBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setAction(data);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
