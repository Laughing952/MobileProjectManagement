package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.unistrong.working.R;
import com.unistrong.working.databinding.ItemJoinPeopleBinding;
import com.unistrong.working.ui.viewmodel.JoinPeopleVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 项目成员
 * Created by Administrator Laughing on 2016/12/26.
 */


public class Adapter_Join_People extends BaseAdapter<JoinPeopleVM> {

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_join_people;
    }

    @Override
    public void convert(BaseViewHolder holder, JoinPeopleVM data, int index) {
        ItemJoinPeopleBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setClick(v -> {

            if (data.isSel()) {
                bind.ivProjectMemberBox.setImageResource(R.mipmap.ic_unchecked_box);
                data.setSel(false);
            } else {
                bind.ivProjectMemberBox.setImageResource(R.mipmap.ic_checked_box);
                data.setSel(true);
            }
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}

