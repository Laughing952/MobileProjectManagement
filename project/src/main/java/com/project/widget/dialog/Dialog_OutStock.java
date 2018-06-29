package com.project.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.provider.Contacts;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.project.R;
import com.project.databinding.DialogOutstockBinding;
import com.project.response.MaterialsRep;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.UiUtil;
import com.waterbase.utile.ViewUtil;

/**
 * Created by Water on 2018/5/8.
 */

public class Dialog_OutStock extends Dialog {
    public Dialog_OutStock(@NonNull Context context) {
        super(context);
    }

    public Dialog_OutStock(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private ConfirmListener confirmListener;
        private CancelListener cancelListener;
        private MaterialsRep materialsRep;
        private int num = 1;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder setMaterialsRep(MaterialsRep materialsRep) {
            this.materialsRep = materialsRep;
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

        public Dialog_OutStock create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final Dialog_OutStock dialog = new Dialog_OutStock(context, R.style.custom_dialog2);
            View layout = inflater.inflate(R.layout.dialog_outstock, null);
            DialogOutstockBinding binding = DataBindingUtil.bind(layout);
            binding.setMaterialsRep(materialsRep);
            binding.etNum.setText("1");
            binding.setClick(v -> {
                if (v.getId() == R.id.tv_cancel) {
                    dialog.dismiss();
                    if (cancelListener != null)
                        cancelListener.cancel(binding.etNum.getText().toString().replace(" ", ""));
                } else if (v.getId() == R.id.tv_ok) {
                    dialog.dismiss();
                    if (confirmListener != null)
                        confirmListener.confirm(binding.etNum.getText().toString().replace(" ", ""));
                } else if (v.getId() == R.id.tv_up) {
                    num = Integer.parseInt(binding.etNum.getText().toString().replace(" ", ""));
                    if (num >= 999)
                        return;
                    num += 1;
                    binding.etNum.setText(String.valueOf(num));
                } else if (v.getId() == R.id.tv_down) {
                    num = Integer.parseInt(binding.etNum.getText().toString().replace(" ", ""));
                    if (num == 0)
                        return;
                    num -= 1;
                    binding.etNum.setText(String.valueOf(num));
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
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        int margin = (int)ViewUtil.dp2px(BaseApplication.getAppContext(), 12);
        getWindow().getDecorView().setPadding(margin, 0, margin, 0);
        getWindow().setAttributes(layoutParams);
    }

    public interface ConfirmListener {
        void confirm(String info);
    }

    public interface CancelListener {
        void cancel(String info);
    }
}
