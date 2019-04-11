package com.systena.githupdemo.ui.custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.AppLoadingBinding;

import androidx.databinding.DataBindingUtil;

public class AppLoading {
    private static boolean shown = false;

    private AlertDialog dialog = null;

    private AppLoadingBinding binding;

    private static AppLoading instance = null;

    private Context context;

    public static AppLoading getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            instance = new AppLoading(context);
            return instance;
        }
    }

    private AppLoading(Context context) {
        this.context = context;
        if (context != null && !AppLoading.isShown()) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.app_loading, null, false);
            View dialogView = binding.getRoot();
            dialogBuilder.setView(dialogView);
            dialogBuilder.setCancelable(false);
            dialog = dialogBuilder.create();
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener((dialog, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // we cannot close dialog when we press back button
                }
                return false;
            });

        }
    }

    public void show() {
        if (!((Activity) context).isFinishing()) {
            if (!AppLoading.isShown() && dialog != null) {
                forceShown();
                dialog.show();
            }
        }
    }

    public void hidden() {
        if (AppLoading.isShown() && dialog != null && dialog.isShowing()) {
            initialize();
            dialog.dismiss();
        }
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
}
