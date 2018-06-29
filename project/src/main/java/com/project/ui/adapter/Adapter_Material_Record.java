package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.project.R;
import com.project.databinding.ItemMaterialRecordBinding;
import com.project.response.MaterialRecordRep;
import com.project.ui.viewmodel.MaterialRecordVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 物资流水
 * Created by Water on 2018/5/23.
 */

public class Adapter_Material_Record extends BaseAdapter<MaterialRecordRep> {
    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_material_record;
    }

    @Override
    public void convert(BaseViewHolder holder, MaterialRecordRep data, int index) {
        ItemMaterialRecordBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewmodel(new MaterialRecordVM(data));
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
