package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.unistrong.working.R;
import com.unistrong.working.bean.IArrangedDetailsBean;
import com.unistrong.working.databinding.ItemIArrangedDetailsBinding;
import com.unistrong.working.ui.viewmodel.IArrangedDetailsVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 我安排的-详情
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_I_Arranged_Details extends BaseAdapter<IArrangedDetailsBean> {


    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_i_arranged_details;
    }

    @Override
    public void convert(BaseViewHolder holder, IArrangedDetailsBean data, int index) {

        ItemIArrangedDetailsBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setViewmodel(new IArrangedDetailsVM(data));

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }


    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}

