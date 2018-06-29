package com.project.widget;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.project.R;
import com.project.databinding.DialogEdittextBinding;

/**
 * Created by Water on 2018/5/8.
 */

public class EditTextDialog extends Dialog {
    public EditTextDialog(@NonNull Context context) {
        super(context);
    }

    public EditTextDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private ConfirmListener confirmListener;
        private CancelListener cancelListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setConfirmListener(ConfirmListener confirmListener) {
            this.confirmListener = confirmListener;
            return this;
        }

        public Builder setCancelListener(CancelListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        public EditTextDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final EditTextDialog dialog = new EditTextDialog(context, R.style.custom_dialog2);
            View layout = inflater.inflate(R.layout.dialog_edittext, null);
            DialogEdittextBinding binding = DataBindingUtil.bind(layout);
            binding.setClick(v -> {
                if (v.getId() == R.id.tv_cancel) {
                    dialog.dismiss();
                    if (cancelListener != null)
                        cancelListener.cancel(binding.etHours.getText().toString());
                } else if (v.getId() == R.id.tv_ok) {
                    dialog.dismiss();
                    if (confirmListener != null)
                        confirmListener.confirm(binding.etHours.getText().toString());
                }
            });
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            return dialog;
        }
    }

    public interface ConfirmListener {
        void confirm(String info);
    }

    public interface CancelListener {
        void cancel(String info);
    }
}
