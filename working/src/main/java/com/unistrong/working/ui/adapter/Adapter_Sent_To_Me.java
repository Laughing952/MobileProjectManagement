package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.unistrong.working.R;
import com.unistrong.working.bean.SentToMeBean;
import com.unistrong.working.databinding.ItemSentToMeBinding;
import com.unistrong.working.ui.viewmodel.SentToMeVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 任务反馈-派给我的
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_Sent_To_Me extends BaseAdapter<SentToMeBean> {


    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_sent_to_me;
    }

    @Override
    public void convert(BaseViewHolder holder, SentToMeBean data, int index) {

        ItemSentToMeBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setViewmodel(new SentToMeVM(data));

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }


    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}

