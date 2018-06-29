package com.project.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.global.listener.ItemClickListener;
import com.project.R;
import com.project.databinding.ItemWorkertypeBinding;
import com.project.response.WorkerTypeRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 工种
 * Created by Water on 2018/5/14.
 */

public class Adapter_WorkerType extends BaseAdapter<WorkerTypeRep> {

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_workertype;
    }

    @Override
    public void convert(BaseViewHolder holder, WorkerTypeRep data, int index) {
        ItemWorkertypeBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setWorkerTypeRep(data);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 4));
        Adatpter_Chlid_WorkerType adatpterChlidWorkerType = new Adatpter_Chlid_WorkerType();
        binding.recyclerView.setAdapter(adatpterChlidWorkerType);
        adatpterChlidWorkerType.setData(data.getChildRepList());
        adatpterChlidWorkerType.setItemClickListener(itemClickListener);
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
