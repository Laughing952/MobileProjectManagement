package com.mycentre.ui.viewmodel;

import android.databinding.BindingAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mycentre.response.DivisionalManagementRep;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.ViewUtil;

/**
 * 部门管理的数据模型
 * Created by Water on 2018/5/8.
 */

public class DivisionalManagementVM {

    private DivisionalManagementRep managementRep;
    private boolean gone;

    private String content; // 显示的内容
    private float marginLeft; // 距离左边的距离
    // 每级缩进的基准
    private static final float STANDARD = ViewUtil.sp2px(BaseApplication.getAppContext(), 20);

    public DivisionalManagementVM(DivisionalManagementRep managementRep) {
        this.managementRep = managementRep;
    }

    public DivisionalManagementVM(DivisionalManagementRep managementRep, boolean gone) {
        this.managementRep = managementRep;
        this.gone = gone;
    }

    public String getContent() {
        return managementRep.getContent();
    }

    public float getMarginLeft() {
        return managementRep.getLevel() * STANDARD;
    }

    public boolean isGone() {
        return gone;
    }

    @BindingAdapter("marginLeft")
    public static void marginLeft(TextView textView, float marginLeft) {
        LogUtil.d("DivisionalManagementVM", "marginLeft:  " + marginLeft);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        params.setMargins((int) marginLeft, 0, 0, 0);
        textView.setLayoutParams(params);
    }
}
