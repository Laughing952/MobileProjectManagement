package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.global.listener.ItemLongClickListener;
import com.global.listener.ProjectItemClickListener;

import com.project.R;
import com.project.databinding.ItemProjectDoingBinding;
import com.project.response.ProjectDoingRep;
import com.project.util.LoadImageUtile;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 正在进行的项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_Project_Doing extends BaseAdapter<ProjectDoingRep> {


    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;
    private ProjectItemClickListener mProjectitemclicklistener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public void setProjectItemClickListener(ProjectItemClickListener projectItemClickListener) {
        this.mProjectitemclicklistener = projectItemClickListener;
    }


    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_project_doing;
    }

    @Override
    public void convert(BaseViewHolder holder, ProjectDoingRep data, int index) {

        ItemProjectDoingBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setViewmodel(data);
        LoadImageUtile.loadSquareImage(bind.ivProjectDoingImg,data.getThumbnail());

        //点击左边相片
        bind.ivProjectDoingImg.setOnClickListener(v -> {
            mProjectitemclicklistener.leftImageClick(v, data, index);
        });
        //点击中间
        bind.llProjectDoingProject.setOnClickListener(v -> {
            mProjectitemclicklistener.centerContentClick(v, data, index);
        });
        //点击右侧更多按钮
        bind.ivProjectDoingMore.setOnClickListener(v -> {
            mProjectitemclicklistener.rightImageClick(v, data, index);
        });

//        holder.itemView.setOnClickListener(v -> {
//            if (itemClickListener != null)
//                itemClickListener.itemClick(v, data, index);
//        });


//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                itemLongClickListener.itemLongClick(v, data, index);
//                return true;
//            }
//        });
    }


    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}

