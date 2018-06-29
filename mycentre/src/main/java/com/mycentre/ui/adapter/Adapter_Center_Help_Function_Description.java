package com.mycentre.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.mycentre.R;
import com.mycentre.bean.CenterHelpFunctionDescriptionBean;
import com.mycentre.databinding.ItemCenterHelpFunctionDescriptionBinding;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 功能说明
 * <p>
 * Created by Laughing on 2018/5/20 10:48
 */

public class Adapter_Center_Help_Function_Description extends BaseAdapter<CenterHelpFunctionDescriptionBean> {

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_center_help_function_description;
    }

    @Override
    public void convert(BaseViewHolder holder, CenterHelpFunctionDescriptionBean data, int index) {
        ItemCenterHelpFunctionDescriptionBinding binding = DataBindingUtil.bind(holder.itemView);
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
