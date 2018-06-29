package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.project.R;
import com.project.databinding.ItemProjectMemberCheckedBinding;
import com.project.ui.viewmodel.ProjectMemberCheckedVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 项目成员
 * Created by Administrator Laughing on 2016/12/26.
 */


public class Adapter_Project_Member_Checked extends BaseAdapter<ProjectMemberCheckedVM> {


    public Adapter_Project_Member_Checked() {
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_project_member_checked;
    }

    @Override
    public void convert(BaseViewHolder holder, ProjectMemberCheckedVM data, int index) {
        ItemProjectMemberCheckedBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewmodel(data);
        binding.setClick(v -> {
            if (data.isSel()) {
                binding.ivProjectMemberCheck.setImageResource(R.mipmap.ic_unchecked_box);
                data.setSel(false);
            } else {
                binding.ivProjectMemberCheck.setImageResource(R.mipmap.ic_checked_box);
                data.setSel(true);
            }
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}

