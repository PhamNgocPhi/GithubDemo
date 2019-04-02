package com.systena.githupdemo.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systena.githupdemo.ui.NavigationManager;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewDataBinding> extends DaggerFragment {

    protected T binding;

    private BaseActivity activity;
    private NavigationManager navigationManager;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false);
        binding.setLifecycleOwner(this.getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
        navigationManager = activity.getNavigationManager();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public BaseActivity getBaseActivity() {
        return activity;
    }

    protected boolean isDuplicateClick() {
        return activity.isDuplicateClick();
    }
}
