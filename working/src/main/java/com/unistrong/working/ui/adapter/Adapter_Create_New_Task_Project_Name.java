package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ItemCreateNewTaskProjectNameBinding;
import com.unistrong.working.response.CreateNewTask_ProjectNameRep;
import com.unistrong.working.ui.viewmodel.CreateNewTask_ProjectNameVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 新建任务-选择项目名称
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */

public class Adapter_Create_New_Task_Project_Name extends BaseAdapter<CreateNewTask_ProjectNameRep> {

    private ItemClickListener itemClickListener;

    public Adapter_Create_New_Task_Project_Name() {

    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_create_new_task_project_name;
    }

    @Override
    public void convert(BaseViewHolder holder, CreateNewTask_ProjectNameRep data, int index) {
        ItemCreateNewTaskProjectNameBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewModel(new CreateNewTask_ProjectNameVM(data));
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
