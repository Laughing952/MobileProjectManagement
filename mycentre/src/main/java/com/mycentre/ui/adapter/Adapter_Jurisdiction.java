package com.mycentre.ui.adapter;

import android.databinding.DataBindingUtil;

import com.mycentre.R;
import com.mycentre.databinding.ItemJurisdictionBinding;
import com.mycentre.response.JurisdictionRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 权限
 * Created by Water on 2018/5/8.
 */

public class Adapter_Jurisdiction extends BaseAdapter<JurisdictionRep> {
    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_jurisdiction;
    }

    @Override
    public void convert(BaseViewHolder holder, JurisdictionRep data, int index) {
        ItemJurisdictionBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setJurisdictionRep(data);
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
