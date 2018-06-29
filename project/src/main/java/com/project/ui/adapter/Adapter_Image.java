package com.project.ui.adapter;

import android.databinding.DataBindingUtil;
import com.project.R;
import com.project.databinding.ItemImageBinding;
import com.project.response.ImageRep;
import com.project.ui.viewmodel.ImageVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * GridView图片的适配器
 * Created by Water on 2018/5/10.
 */

public class Adapter_Image extends BaseAdapter<ImageRep> {

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_image;
    }

    @Override
    public void convert(BaseViewHolder holder, ImageRep data, int index) {
        ItemImageBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setImage(new ImageVM(data));
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }
}
