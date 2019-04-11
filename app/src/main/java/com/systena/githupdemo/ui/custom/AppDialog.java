package com.systena.githupdemo.ui.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.AppDialogBinding;

import androidx.databinding.DataBindingUtil;

public class AppDialog {

    private AppDialogBinding binding;

    private static boolean shown = false;

    private AlertDialog dialog = null;

    public AppDialog(Context context) {
        if (context != null && !AppDialog.isShown()) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            binding = DataBindingUtil.inflate(inflater, R.layout.app_dialog, null, false);
            View view = binding.getRoot();
            dialogBuilder.setView(view);
            dialogBuilder.setCancelable(false);
            dialog = dialogBuilder.create();
            dialog.setCanceledOnTouchOutside(false);
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener((dialog, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // we cannot close dialog when we press back button
                }
                return false;
            });

            binding.tvTitle.setVisibility(View.GONE);
            binding.tvContent.setVisibility(View.GONE);
            binding.btnPositive.setVisibility(View.GONE);
            binding.btnNegative.setVisibility(View.GONE);
            binding.btnPrimary.setVisibility(View.GONE);
        }
    }

    public AppDialog setTitle(String title) {
        if (dialog != null && !TextUtils.isEmpty(title)) {
            binding.tvTitle.setVisibility(View.VISIBLE);
            binding.tvTitle.setText(title);
        }
        return this;
    }

    public AppDialog setContent(String content) {
        if (dialog != null && !TextUtils.isEmpty(content)) {
            binding.tvContent.setVisibility(View.VISIBLE);
            binding.tvContent.setText(content);
        }
        return this;
    }

    public AppDialog onClickPrimaryButton(String buttonText, AppDialog.OnDialogClickListener listener) {
        if (dialog != null) {
            binding.btnPrimary.setVisibility(View.VISIBLE);
            binding.btnPrimary.setText(buttonText);
            binding.btnPrimary.setOnClickListener(v -> {
                dialog.dismiss();
                AppDialog.initialize();
                if (listener != null) {
                    listener.onDialogButtonClick();
                }
            });
        }
        return this;
    }

    public AppDialog onClickPositiveButton(String buttonText, AppDialog.OnDialogClickListener listener) {
        if (dialog != null) {
            binding.btnPositive.setVisibility(View.VISIBLE);
            binding.btnPositive.setText(buttonText);
            binding.btnPositive.setOnClickListener(v -> {
                dialog.dismiss();
                AppDialog.initialize();
                if (listener != null) {
                    listener.onDialogButtonClick();
                }
            });
        }
        return this;
    }

    public AppDialog onClickNegativeButton(String buttonText, AppDialog.OnDialogClickListener listener) {
        if (dialog != null) {
            binding.btnNegative.setVisibility(View.VISIBLE);
            binding.btnNegative.setText(buttonText);
            binding.btnNegative.setOnClickListener(v -> {
                dialog.dismiss();
                AppDialog.initialize();
                if (listener != null) {
                    listener.onDialogButtonClick();
                }
            });
        }
        return this;
    }

    public void show() {
        if (!AppDialog.isShown() && dialog != null) {
            dialog.show();
            forceShown();
        }
    }

    public AppDialog setCanceledOnTouchOutside() {
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnCancelListener(dialog -> {
                dialog.dismiss();
                AppDialog.initialize();
            });
        }
        return this;
    }

    private static boolean isShown() {
        return shown;
    }

    private static void forceShown() {
        shown = true;
    }

    private static void initialize() {
        shown = false;
    }

    public interface OnDialogClickListener {
        void onDialogButtonClick();
    }
}
