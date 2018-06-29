package com.project.ui.adapter;

import android.databinding.DataBindingUtil;

import com.global.listener.ItemClickListener;
import com.global.listener.ItemLongClickListener;
import com.global.listener.ProjectItemClickListener;
import com.project.R;
import com.project.databinding.ItemProjectOverBinding;
import com.project.databinding.ItemSearchProjectBinding;
import com.project.response.ProjectDoingRep;
import com.project.util.LoadImageUtile;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 搜索项目
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_Search_Project extends BaseAdapter<ProjectDoingRep> {

    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;
    private ProjectItemClickListener mProjectitemclicklistener;

    public void setProjectItemClickListener(ProjectItemClickListener projectItemClickListener) {
        this.mProjectitemclicklistener = projectItemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_search_project;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    @Override
    public void convert(BaseViewHolder holder, ProjectDoingRep data, int index) {
        ItemSearchProjectBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setViewmodel(data);
        LoadImageUtile.loadSquareImage(bind.ivSearchProjectImg,data.getImage());

        //点击左边相片
        bind.ivSearchProjectImg.setOnClickListener(v -> {
            mProjectitemclicklistener.leftImageClick(v, data, index);
        });
        //点击中间
        bind.llSearchProjectProject.setOnClickListener(v -> {
            mProjectitemclicklistener.centerContentClick(v, data, index);
        });
        //点击右侧更多按钮
        bind.ivSearchProjectMore.setOnClickListener(v -> {
            mProjectitemclicklistener.rightImageClick(v, data, index);
        });


//        holder.itemView.setOnClickListener(v -> {
//            if (itemClickListener != null) {
//                LogUtil.e("TAG", "laughing---------------------->   " + "sdjadgfkajsd;lgj");
//                itemClickListener.itemClick(v, data, index);
//            }
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

