package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.util.ImageUtils;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ItemSuggest1Binding;
import com.unistrong.working.ui.viewmodel.ApprovalHistoryVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 通用申请详情-审批历史
 * 作者：cuihao on 2018/5/22 10:15
 *
 */


public class Adapter_Approval_history extends BaseAdapter<ApprovalHistoryVM> {


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_suggest1;
    }

    @Override
    public void convert(BaseViewHolder holder, ApprovalHistoryVM viewModel, int index) {
        ItemSuggest1Binding binding=DataBindingUtil.bind(holder.itemView);
        binding.setViewModel(viewModel);
        if (viewModel.getImageRep()!=null){
            ImageUtils.loadCircleImage(binding.tvSuggestHeadImg,viewModel.getImageRep().getShowImageUrl());
        }
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}

