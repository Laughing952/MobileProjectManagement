package com.project.ui.adapter;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.project.R;
import com.project.databinding.ItemMaterialsBinding;
import com.project.response.MaterialsRep;
import com.project.ui.activity.A_In_Stock;
import com.project.ui.viewmodel.MaterialsVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * Created by Water on 2018/5/22.
 */

public class Adapter_Materials extends BaseAdapter<MaterialsRep> {

    private ItemClickListener itemClickListener;
    private OutStockListener outStockListener;
    private int flag;

    public Adapter_Materials(int flag) {
        super();
        this.flag = flag;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOutStockListener(OutStockListener outStockListener) {
        this.outStockListener = outStockListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_materials;
    }

    @Override
    public void convert(BaseViewHolder holder, MaterialsRep data, int index) {
        ItemMaterialsBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewModel(new MaterialsVM(data));
        if (flag == A_In_Stock.SAVE_STOCK) {
            binding.ivAdd.setVisibility(View.GONE);
            binding.llCompany.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(v -> {
                if (itemClickListener != null)
                    itemClickListener.itemClick(v, data, index);
            });
        } else if (flag == A_In_Stock.OUT_STOCK) {
            binding.ivAdd.setVisibility(View.VISIBLE);
            binding.llCompany.setVisibility(View.GONE);
            binding.ivAdd.setOnClickListener(v -> {
                if (outStockListener != null)
                    outStockListener.outStock(v, data, index);
            });
        }
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

    public interface OutStockListener<T> {
        void outStock(View v, T t, int index);
    }
}
