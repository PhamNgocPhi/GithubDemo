package com.systena.githupdemo.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.AppToolbarBinding;

import androidx.annotation.StringRes;

public class AppToolbar extends RelativeLayout {

    private AppToolbarBinding binding;

    public AppToolbar(Context context) {
        super(context);
        init();
    }

    public AppToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AppToolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflate(getContext(), R.layout.app_toolbar, this);
        binding = AppToolbarBinding.inflate(inflater);
    }

    public AppToolbar setTitle(String title) {
        binding.tvTitle.setText(title);
        return this;
    }

    public AppToolbar setTitle(@StringRes int titleId) {
        binding.tvTitle.setText(titleId);
        return this;
    }

    public AppToolbar onClickBack(View.OnClickListener listener) {
        binding.llBack.setOnClickListener(listener);
        return this;
    }

}
