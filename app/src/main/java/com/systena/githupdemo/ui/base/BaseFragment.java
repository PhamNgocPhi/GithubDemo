package com.systena.githupdemo.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systena.githupdemo.R;
import com.systena.githupdemo.ui.NavigationManager;
import com.systena.githupdemo.ui.custom.AppDialog;
import com.systena.githupdemo.ui.custom.AppLoading;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewDataBinding> extends DaggerFragment {

    protected T binding;

    private BaseActivity activity;
    protected NavigationManager navigationManager;


    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @LayoutRes
    protected abstract int layoutRes();

    protected abstract void resetViewState();

    protected abstract LiveData<ViewState> getViewStateLiveData();

    protected abstract void handleViewState(ViewState viewState);

    protected abstract void initViewModel();

    /**
     * start do something in view
     */
    protected abstract void initView();

    /**
     * if return true, use super.onBackPressed()
     */
    protected abstract boolean onBackPressed();

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false);
        binding.setLifecycleOwner(this.getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initView();
        if (getViewStateLiveData() != null) {
            getViewStateLiveData().observe(this.getViewLifecycleOwner(), viewState -> {
                if (viewState != null) {
                    handleViewState(viewState);
                }
            });
        }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        resetViewState();
    }

    protected void showErrorDialog(String content, String buttonText) {
        if (activity != null) {
            activity.showDialogError(content, buttonText, true, null);
        }
    }

    protected void showErrorDialog(String content, String buttonText, @NonNull AppDialog.OnDialogClickListener listener) {
        if (activity != null) {
            activity.showDialogError(content, buttonText, false, listener);
        }
    }

    protected void showErrorDialog(String content) {
        if (activity != null) {
            activity.showDialogError(content, activity.getString(R.string.ok), true, null);
        }
    }


    protected void showLoading() {
        AppLoading.getInstance(getContext()).show();
    }

    protected void hideLoading() {
        AppLoading.getInstance(getContext()).hidden();
    }

    protected BaseActivity getBaseActivity() {
        return activity;
    }

    protected boolean isDuplicateClick() {
        return activity.isDuplicateClick();
    }
}
