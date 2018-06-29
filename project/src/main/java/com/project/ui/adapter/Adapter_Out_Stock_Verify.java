package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.project.R;
import com.project.databinding.ItemOutStockVerifyBinding;
import com.project.request.MaterialsReq;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 出库:确认信息
 * Created by Water on 2018/5/24.
 */

public class Adapter_Out_Stock_Verify extends BaseAdapter<MaterialsReq> {
    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_out_stock_verify;
    }

    @Override
    public void convert(BaseViewHolder holder, MaterialsReq data, int index) {
        ItemOutStockVerifyBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setMaterialsReq(data);

    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
