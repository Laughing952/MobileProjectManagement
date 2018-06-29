package com.project.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.project.R;
import com.project.databinding.ItemQualityBinding;
import com.project.response.QualityListRep;
import com.project.ui.viewmodel.QualityListVM;
import com.waterbase.utile.LogUtil;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 质量检查
 * Created by Water on 2018/5/10.
 */

public class Adapter_Quality extends BaseAdapter<QualityListRep> {

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_quality;
    }

    @Override
    public void convert(BaseViewHolder holder, QualityListRep data, int index) {
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
        ItemQualityBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.rvImage.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 4));
        Adapter_Image adapterImage = new Adapter_Image();
        binding.rvImage.setAdapter(adapterImage);
        binding.setViewModel(new QualityListVM(data));
        if (data.getImageRepList() == null || data.getImageRepList().isEmpty())
            binding.rvImage.setVisibility(View.GONE);
        else
            adapterImage.setData(data.getImageRepList());
//        DividerItemDecoration divider = new DividerItemDecoration(holder.itemView.getContext(),DividerItemDecoration.HORIZONTAL);
//        divider.setDrawable(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.custom_divider));
//        binding.rvImage.addItemDecoration(divider);

    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
