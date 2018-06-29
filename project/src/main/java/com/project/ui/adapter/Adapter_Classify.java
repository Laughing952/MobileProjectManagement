package com.project.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.global.listener.ItemClickListener;
import com.project.R;
import com.project.databinding.ItemClassifyeBinding;
import com.project.ui.viewmodel.ClassifyeVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

import java.util.List;

/**
 * 物资分类
 * Created by Water on 2018/5/22.
 */

public class Adapter_Classify extends BaseAdapter<ClassifyeVM> {

    private int selIndex = 0;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void setData(@Nullable List<? extends ClassifyeVM> data) {
        super.setData(data);
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_classifye;
    }

    @Override
    public void convert(BaseViewHolder holder, ClassifyeVM data, int index) {
        ItemClassifyeBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewModel(data);
        if (data.isCheck())
            selIndex = index;
        holder.itemView.setOnClickListener(v -> {
            if (index != selIndex) {
                getData().get(selIndex).setCheck(false);
                notifyItemChanged(selIndex);
                selIndex = index;
                data.setCheck(true);
                notifyItemChanged(index);
                if (itemClickListener != null)
                    itemClickListener.itemClick(v, data, index);
            }
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
