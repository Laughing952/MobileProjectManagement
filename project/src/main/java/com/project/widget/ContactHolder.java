package com.project.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.R;

/**
 * Created by you on 2017/9/11.
 */

public class ContactHolder extends RecyclerView.ViewHolder {

    public final ImageView iv_header;

    public final TextView tv_name;

    public CheckBox checkBox;

    public ContactHolder(View itemView) {
        super(itemView);
        iv_header = (ImageView) itemView.findViewById(R.id.iv_header);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
    }
}
