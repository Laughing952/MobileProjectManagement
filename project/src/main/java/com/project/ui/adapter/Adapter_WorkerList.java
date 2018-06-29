package com.project.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.global.listener.ItemClickListener;
import com.project.R;
import com.project.response.WorkerRep;
import com.project.ui.activity.A_Workers_List;
import com.project.util.LoadImageUtile;
import com.project.widget.CNPinyin;
import com.project.widget.Contact;
import com.project.widget.ContactHolder;
import com.project.widget.HeaderHolder;
import com.project.widget.StickyHeaderAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by you on 2017/9/11.
 */

public class Adapter_WorkerList extends RecyclerView.Adapter<ContactHolder> implements StickyHeaderAdapter<HeaderHolder> {

    private int flag;
    private List<CNPinyin<WorkerRep>> cnPinyinList;
    private CheckBoxListener checkBoxListener;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public Adapter_WorkerList(int flag) {
        this.flag = flag;
    }

    public void setCheckBoxListener(CheckBoxListener checkBoxListener) {
        this.checkBoxListener = checkBoxListener;
    }

    public List<CNPinyin<WorkerRep>> getCnPinyinList() {
        return cnPinyinList;
    }

    public void setData(List<CNPinyin<WorkerRep>> data) {
        if (cnPinyinList == null) {
            cnPinyinList = new ArrayList<>();
            cnPinyinList.addAll(data);
            notifyDataSetChanged();
        } else {
            cnPinyinList.clear();
            cnPinyinList.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return cnPinyinList == null ? 0 : cnPinyinList.size();
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workerlist, parent, false));
    }


    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        if (flag == A_Workers_List.SEL) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(cnPinyinList.get(position).data.isCheck());
            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                cnPinyinList.get(position).data.setCheck(isChecked);
                if (checkBoxListener != null)
                    checkBoxListener.checkBox(isChecked);
            });
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(v->{
            if (itemClickListener!=null){
                itemClickListener.itemClick(v,cnPinyinList.get(position).data,position);
            }
        });
        WorkerRep workerRep = cnPinyinList.get(position).data;
        LoadImageUtile.loadImage(holder.iv_header, workerRep.getImgUrl());
        holder.tv_name.setText(workerRep.getName());
    }

    @Override
    public long getHeaderId(int childAdapterPosition) {
        return cnPinyinList.get(childAdapterPosition).getFirstChar();
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder holder, int childAdapterPosition) {
        holder.tv_header.setText(String.valueOf(cnPinyinList.get(childAdapterPosition).getFirstChar()));
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new HeaderHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header, parent, false));
    }

    public interface CheckBoxListener {
        void checkBox(Boolean isChecked);
    }

}
