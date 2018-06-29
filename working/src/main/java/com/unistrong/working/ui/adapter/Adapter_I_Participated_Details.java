package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.unistrong.working.R;
import com.unistrong.working.bean.IParticipatedDetailsBean;
import com.unistrong.working.databinding.ItemIParticipatedDetailsBinding;
import com.unistrong.working.ui.viewmodel.IParticipatedDetailsVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 任务反馈-派给我的
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_I_Participated_Details extends BaseAdapter<IParticipatedDetailsBean> {


    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_i_participated_details;
    }

    @Override
    public void convert(BaseViewHolder holder, IParticipatedDetailsBean data, int index) {

        ItemIParticipatedDetailsBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setViewmodel(new IParticipatedDetailsVM(data));

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }


    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}

