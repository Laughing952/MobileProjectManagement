package com.mycentre.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.mycentre.R;
import com.mycentre.databinding.ItemNewDutyBinding;
import com.mycentre.ui.viewmodel.JurisdictionVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 新建/修改职务
 * Created by Water on 2018/5/9.
 */

public class Adapter_New_Duty extends BaseAdapter<JurisdictionVM> {


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_new_duty;
    }

    @Override
    public void convert(BaseViewHolder holder, JurisdictionVM data, int index) {
        ItemNewDutyBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewModel(data);
        binding.setClick(v -> {
            if (data.isSel()) {
                binding.ivCheckBox.setImageResource(R.mipmap.ic_turn_off);
                data.setSel(false);
            } else {
                binding.ivCheckBox.setImageResource(R.mipmap.ic_turn_on);
                data.setSel(true);
            }
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
