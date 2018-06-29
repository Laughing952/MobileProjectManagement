package com.mycentre.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mycentre.R;
import com.mycentre.databinding.ItemDutyJurisdictionBinding;
import com.mycentre.response.DutyRep;
import com.mycentre.ui.viewmodel.DutyJurisdictionVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 职务权限
 * Created by Water on 2018/5/9.
 */

public class Adapter_Duty_Jurisdiction extends BaseAdapter<DutyRep> {

    private MyClickListener myClickListener;

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_duty_jurisdiction;
    }

    @Override
    public void convert(BaseViewHolder holder, DutyRep data, int index) {
        ItemDutyJurisdictionBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewModel(new DutyJurisdictionVM(data, index));
        binding.setClick(v -> {
            if (v.getId() == R.id.iv_updata) {
                if (myClickListener != null)
                    myClickListener.updata(holder.itemView, data, index);
            } else if (v.getId() == R.id.iv_del) {
                if (myClickListener != null)
                    myClickListener.del(holder.itemView, data, index);
            }
        });
        binding.setLongClick(v -> {
            if (myClickListener != null)
                myClickListener.move(holder, data, index);
            return true;
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

    public interface MyClickListener<T> {
        void updata(View view, T t, int index);

        void del(View view, T t, int index);

        void move(RecyclerView.ViewHolder holder, T t, int index);
    }
}
