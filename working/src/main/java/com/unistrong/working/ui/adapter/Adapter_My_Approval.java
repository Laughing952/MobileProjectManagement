package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.global.listener.ItemLongClickListener;
import com.global.util.ImageUtils;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ItemMyApprovalBinding;
import com.unistrong.working.response.GeneralRequestItemDetailRep;
import com.unistrong.working.ui.viewmodel.GeneralApprovalItemVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 通用申请-我审批的
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_My_Approval extends BaseAdapter<GeneralRequestItemDetailRep> {


    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_my_approval;
    }

    @Override
    public void convert(BaseViewHolder holder, GeneralRequestItemDetailRep data, int index) {
        GeneralApprovalItemVM viewModel = new GeneralApprovalItemVM (data);
        ItemMyApprovalBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewmodel(viewModel);
        if (viewModel.getImageRep()!=null){
            ImageUtils.loadCircleImage(binding.ivMyApprovalHeadImg,viewModel.getImageRep().getShowImageUrl());
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

