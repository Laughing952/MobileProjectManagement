package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.global.listener.ItemLongClickListener;
import com.global.listener.ProjectItemClickListener;
import com.project.R;
import com.project.databinding.ItemOutboundRecordsBinding;
import com.project.databinding.ItemProjectDoingBinding;
import com.project.response.OutboundRecordsRep;
import com.project.response.ProjectDoingRep;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 项目-出库记录
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_Outbound_Records extends BaseAdapter<OutboundRecordsRep> {


    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;
    private ProjectItemClickListener mProjectitemclicklistener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public void setProjectItemClickListener(ProjectItemClickListener projectItemClickListener) {
        this.mProjectitemclicklistener = projectItemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_outbound_records;
    }

    @Override
    public void convert(BaseViewHolder holder, OutboundRecordsRep data, int index) {

        ItemOutboundRecordsBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setViewmodel(data);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);

        });


//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                itemLongClickListener.itemLongClick(v, data, index);
//                return true;
//            }
//        });
    }


    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}

