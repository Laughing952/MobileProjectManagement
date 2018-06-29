package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.global.listener.ItemLongClickListener;
import com.global.util.ImageUtils;
import com.unistrong.working.R;
import com.unistrong.working.bean.CcMeBean;
import com.unistrong.working.databinding.ItemCcMeBinding;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.ui.viewmodel.CcMeVM;
import com.unistrong.working.ui.viewmodel.GeneralApprovalItemVM;
import com.unistrong.working.ui.viewmodel.GeneralCCMeItemVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 抄送我的
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_Cc_Me extends BaseAdapter<GeneralRequestItemDetailRep> {


    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_cc_me;
    }

    @Override
    public void convert(BaseViewHolder holder, GeneralRequestItemDetailRep data, int index) {

        GeneralCCMeItemVM viewModel = new GeneralCCMeItemVM (data);
        ItemCcMeBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setViewmodel(viewModel);
        if (viewModel.getImageRep()!=null) {
            ImageUtils.loadCircleImage(bind.iv, viewModel.getImageRep().getShowImageUrl());
        }
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }


    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}

