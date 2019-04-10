package com.systena.githupdemo.ui.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentHomeBinding;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeViewModel viewmodel;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void resetViewState() {
        viewmodel.resetViewState();
    }

    @Override
    protected LiveData<ViewState> getViewStateLiveData() {
        return viewmodel.getViewState();
    }

    @Override
    protected void handleViewState(ViewState viewState) {

    }

    @Override
    protected void initViewModel() {
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean onBackPressed() {
        navigationManager.navigateBack();
        return false;
    }

}
