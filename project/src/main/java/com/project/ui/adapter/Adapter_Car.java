package com.project.ui.adapter;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.project.R;
import com.project.databinding.ItemCarBinding;
import com.project.request.MaterialsReq;
import com.project.response.MaterialsRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 购物车
 * Created by Water on 2018/5/23.
 */

public class Adapter_Car extends BaseAdapter<MaterialsReq> {

    private DelListener delListener;

    public void setDelListener(DelListener delListener) {
        this.delListener = delListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_car;
    }

    @Override
    public void convert(BaseViewHolder holder, MaterialsReq data, int index) {
        ItemCarBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setMaterialsReq(data);
        binding.ivDel.setOnClickListener(v -> {
            if (delListener != null)
                delListener.del(holder.itemView, data, index);
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

    public interface DelListener<T> {
        void del(View v, T t, int index);

    }
}
