package com.mycentre.ui.adapter;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.global.listener.ItemClickListener;
import com.mycentre.R;
import com.mycentre.databinding.ItemDivisionalManagementBinding;
import com.mycentre.response.DivisionalManagementRep;
import com.mycentre.ui.viewmodel.DivisionalManagementVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 部门管理
 * Created by Water on 2018/5/8.
 */

public class Adapter_Divisional_Management extends BaseAdapter<DivisionalManagementRep> {

    private ClickListener clickListener;
    private ItemClickListener itemClickListener;

    private boolean isGone;

    public Adapter_Divisional_Management() {
    }

    public Adapter_Divisional_Management(boolean isGone) {
        this.isGone = isGone;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_divisional_management;
    }

    @Override
    public void convert(BaseViewHolder holder, DivisionalManagementRep data, int index) {
        ItemDivisionalManagementBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewModel(new DivisionalManagementVM(data, isGone));
        binding.setClick(v -> {
            if (clickListener == null)
                return;
            if (v.getId() == R.id.iv_updata) {
                clickListener.updata(holder.itemView, data, index);
            } else if (v.getId() == R.id.iv_del) {
                clickListener.del(holder.itemView, data, index);
            }
        });
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

    public interface ClickListener<T> {
        void updata(View item, T t, int index);

        void del(View item, T t, int index);
    }
}
