package com.mycentre.ui.adapter;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.mycentre.R;
import com.mycentre.databinding.ItemPersonnelManagementBinding;
import com.mycentre.response.PersonnelManagementRep;
import com.mycentre.ui.viewmodel.PersonnelManagementVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 人员管理
 * Created by Water on 2018/5/8.
 */

public class Adapter_Personnel_Management extends BaseAdapter<PersonnelManagementRep> {

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_personnel_management;
    }

    @Override
    public void convert(BaseViewHolder holder, PersonnelManagementRep data, int index) {
        ItemPersonnelManagementBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setViewModel(new PersonnelManagementVM(data));
        binding.setClick(v -> {
            if (clickListener == null)
                return;
            if (v.getId() == R.id.iv_updata) {
                clickListener.updata(holder.itemView, data, index);
            } else if (v.getId() == R.id.iv_del) {
                clickListener.del(holder.itemView, data, index);
            }

        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

    public interface ClickListener<T> {
        void updata(View item, T t, int index);

        void del(View item, T t, int index);

    }
}
