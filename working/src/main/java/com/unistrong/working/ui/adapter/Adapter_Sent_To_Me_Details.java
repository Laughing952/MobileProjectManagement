package com.unistrong.working.ui.adapter;

import android.databinding.DataBindingUtil;
import android.widget.TextView;

import com.global.listener.ItemClickListener;
import com.global.util.ImageUtils;
import com.unistrong.working.R;
import com.unistrong.working.databinding.ItemISentBinding;
import com.unistrong.working.databinding.ItemSentToMeDetailsBinding;
import com.unistrong.working.response.MessageEntityRep;
import com.unistrong.working.ui.viewmodel.SentToMeDetailsVM;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 任务反馈-派给我的-详情
 * 作者：Laughing on 2018/5/3 10:15
 * 邮箱：719240226@qq.com
 */


public class Adapter_Sent_To_Me_Details extends BaseAdapter<MessageEntityRep> {


    private ItemClickListener itemClickListener;
    private TextView mArrangePeopleName;
    private TextView mApprovalPerson;
    private TextView mProjectName;
    private TextView mJoinPerson;
    private TextView mDescribe;
    private TextView mEndDate;
    private TextView mTaskName;
    private SentToMeDetailsVM sentToMeDetailsVM;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        if (getData()!=null) {
            if ("3".equals(getData().get(index).getMessageStatus())) {
                return R.layout.item_i_sent;
            } else {
                return R.layout.item_sent_to_me_details;
            }
        }else {
            return R.layout.item_sent_to_me_details;
        }
    }

    @Override
    public void convert(BaseViewHolder holder, MessageEntityRep messageEntityRep, int index) {
        if ("3".equals(messageEntityRep.getMessageStatus())){
            ItemISentBinding binding = DataBindingUtil.bind(holder.itemView);
            binding.setViewmodel(messageEntityRep);
            ImageUtils.loadCircleImage(binding.ivItemSentToMeDetailsUserHead,messageEntityRep.getImageRep().getShowImageUrl());
        }else{
            ItemSentToMeDetailsBinding bind = DataBindingUtil.bind(holder.itemView);
            bind.setViewmodel(messageEntityRep);
        }

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, messageEntityRep, index);
        });
    }

    public void setHeadData(SentToMeDetailsVM sentToMeDetailsVM){
        if (sentToMeDetailsVM!=null) {
            this.sentToMeDetailsVM = sentToMeDetailsVM;
            mArrangePeopleName.setText(sentToMeDetailsVM.getCreaterName());//安排人
            mApprovalPerson.setText(sentToMeDetailsVM.getTaskHeadName());//执行人
            mProjectName.setText(sentToMeDetailsVM.getItemName());//项目名
            mTaskName.setText(sentToMeDetailsVM.getTaskName());//任务名称
            if (sentToMeDetailsVM.getPartakeList()==null){
                mJoinPerson.setText( "0人"); //参与人
            }else {
                mJoinPerson.setText(sentToMeDetailsVM.getPartakeList().size() + "人"); //参与人
            }

        }
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {
        if (layoutRes == R.layout.a_sent_to_me_details_header) {
            //安排人
            mArrangePeopleName = (TextView) holder.itemView.findViewById(R.id.tv_send_to_me_details_header_project_arrange_people_name);
            //执行人
            mApprovalPerson = (TextView) holder.itemView.findViewById(R.id.tv_send_to_me_details_header_approve_person);
            //项目名
            mProjectName = (TextView) holder.itemView.findViewById(R.id.tv_send_to_me_details_header_project_name);
            //任务名称
            mTaskName = (TextView) holder.itemView.findViewById(R.id.tv_send_to_me_details_header_task_name);
            //截止日期
            mEndDate = (TextView) holder.itemView.findViewById(R.id.et_send_to_me_details_header_end_date);
            //描述
            mDescribe = (TextView) holder.itemView.findViewById(R.id.et_send_to_me_details_header_describe);
            //参与人
            mJoinPerson = (TextView) holder.itemView.findViewById(R.id.tv_send_to_me_details_header_join_person);
//            mArrangePeopleName.setText("666");//安排人
        }
    }

    public void setJoinPerson(String data) {
        mJoinPerson.setText(data);
    }
}


