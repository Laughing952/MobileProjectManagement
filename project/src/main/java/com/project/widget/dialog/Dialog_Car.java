package com.project.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.project.R;
import com.project.databinding.DialogCarBinding;
import com.project.databinding.DialogOutstockBinding;
import com.project.request.MaterialsReq;
import com.project.response.MaterialsRep;
import com.project.ui.adapter.Adapter_Car;
import com.project.ui.adapter.Adapter_Image;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.AppUtil;
import com.waterbase.utile.UiUtil;
import com.waterbase.utile.ViewUtil;

import java.util.List;

/**
 * Created by Water on 2018/5/8.
 */

public class Dialog_Car extends Dialog {
    public Dialog_Car(@NonNull Context context) {
        super(context);
    }

    public Dialog_Car(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private ConfirmListener confirmListener;
        private CancelListener cancelListener;
        private DelListener delListener;

        private List<MaterialsReq> materialsRepList;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder setMaterialsRepList(List<MaterialsReq> materialsRepList) {
            this.materialsRepList = materialsRepList;
            return this;
        }

        public Builder setConfirmListener(ConfirmListener confirmListener) {
            this.confirmListener = confirmListener;
            return this;
        }

        public Builder setCancelListener(CancelListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        public Builder setDelListener(DelListener delListener) {
            this.delListener = delListener;
            return this;
        }

        public Dialog_Car create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final Dialog_Car dialog = new Dialog_Car(context, R.style.custom_dialog2);
            View layout = inflater.inflate(R.layout.dialog_car, null);
            DialogCarBinding binding = DataBindingUtil.bind(layout);
            binding.rvCar.setLayoutManager(new LinearLayoutManager(context));
            Adapter_Car adapterCar = new Adapter_Car();
            adapterCar.setDelListener(new Adapter_Car.DelListener<MaterialsReq>() {
                @Override
                public void del(View v, MaterialsReq materialsReq, int index) {
                    adapterCar.removeData(materialsReq);
                    delListener.del(materialsReq);
                }
            });
            binding.rvCar.setAdapter(adapterCar);
            adapterCar.setData(materialsRepList);
            binding.setClick(v -> {
                if (v.getId() == R.id.tv_cancel) {
                    dialog.dismiss();
                    if (cancelListener != null)
                        cancelListener.cancel(adapterCar.getData());
                } else if (v.getId() == R.id.tv_ok) {
                    dialog.dismiss();
                    if (confirmListener != null)
                        confirmListener.confirm(adapterCar.getData());
                }
            });
            dialog.setContentView(layout);
            return dialog;
        }
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        int margin = (int)ViewUtil.dp2px(BaseApplication.getAppContext(), 12);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }

    public interface DelListener {
        void del(MaterialsReq materialsReq);
    }

    public interface ConfirmListener {
        void confirm(List<MaterialsReq> materialsRepList);
    }

    public interface CancelListener {
        void cancel(List<MaterialsReq> materialsRepList);
    }
}
