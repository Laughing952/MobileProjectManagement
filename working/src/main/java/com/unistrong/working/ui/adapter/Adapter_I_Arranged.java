package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.global.listener.ItemLongClickListener;
import com.unistrong.working.R;
import com.unistrong.working.bean.IArrangedBean;
import com.unistrong.working.databinding.ItemIArrangedBinding;
import com.unistrong.working.ui.viewmodel.IArrangedVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 我提交的-通用申请（可取消）
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_I_Arranged extends BaseAdapter<IArrangedBean> {


    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_i_arranged;
    }

    @Override
    public void convert(BaseViewHolder holder, IArrangedBean data, int index) {

        ItemIArrangedBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setViewmodel(new IArrangedVM(data));

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }


    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}

