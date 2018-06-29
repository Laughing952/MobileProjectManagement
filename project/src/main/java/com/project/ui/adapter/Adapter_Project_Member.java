package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.project.R;
import com.project.databinding.ItemProjectMemberBinding;
import com.project.response.PersonRep;
import com.project.ui.viewmodel.ProjectMemberCheckedVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

import java.util.ArrayList;

/**
 * 项目成员
 * Created by Administrator Laughing on 2016/12/26.
 */


public class Adapter_Project_Member extends BaseAdapter<PersonRep> {

    private ArrayList<ProjectMemberCheckedVM> mProjectMemberCheckedVMS;
    private ItemProjectMemberBinding mBind;

    public Adapter_Project_Member() {
        mProjectMemberCheckedVMS = new ArrayList<>();
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_project_member;
    }

    @Override
    public void convert(BaseViewHolder holder, PersonRep data, int index) {
        mBind = DataBindingUtil.bind(holder.itemView);
        mBind.setViewmodel(data);

    }


    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}

