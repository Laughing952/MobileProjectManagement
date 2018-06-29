package com.project.ui.adapter;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.project.R;
import com.project.databinding.ItemAddMeritBinding;
import com.project.response.WorkerRep;
import com.project.ui.viewmodel.WorkerMeritVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 添加/编辑记功
 * Created by Water on 2018/5/18.
 */

public class Adapter_Add_Merit extends BaseAdapter<WorkerRep> {

    private DelListener delListener;

    public void setDelListener(DelListener delListener) {
        this.delListener = delListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_add_merit;
    }

    @Override
    public void convert(BaseViewHolder holder, WorkerRep data, int index) {
        ItemAddMeritBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewModel(new WorkerMeritVM(data, index));
        binding.setClick(v -> {
            if (v.getId() == R.id.tv_up) {
                float hours = data.getHours() + 0.5f;
                data.setHours(hours);
                notifyItemChanged(index);
            } else if (v.getId() == R.id.tv_down) {
                float hours = data.getHours() - 0.5f;
                data.setHours(hours);
                notifyItemChanged(index);
            } else if (v.getId() == R.id.iv_del) {
                if (delListener != null) {
                    delListener.del(holder.itemView, data, index);
                }
            }
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

    public interface DelListener<T> {
        void del(View v, T t, int index);
    }

}
