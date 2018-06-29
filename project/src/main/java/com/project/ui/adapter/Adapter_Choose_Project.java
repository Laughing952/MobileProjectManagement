package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.project.R;
import com.project.bean.ChooseProjectBean;
import com.project.databinding.ItemChooseProjectBinding;
import com.project.ui.viewmodel.ChooseProjectVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 选择项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_Choose_Project extends BaseAdapter<ChooseProjectBean> {


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_choose_project;
    }

    @Override
    public void convert(BaseViewHolder holder, ChooseProjectBean data, int index) {
        ItemChooseProjectBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setViewmodel(new ChooseProjectVM(data));
    }


    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}

