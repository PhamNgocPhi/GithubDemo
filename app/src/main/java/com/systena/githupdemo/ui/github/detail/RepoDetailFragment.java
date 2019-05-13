package com.systena.githupdemo.ui.github.detail;

import android.os.Bundle;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentRepoDetailBinding;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

public class RepoDetailFragment extends BaseFragment<FragmentRepoDetailBinding> {

    private RepoDetailViewModel viewModel;

    public RepoDetailFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_repo_detail;
    }

    @Override
    protected void resetViewState() {
        viewModel.resetViewState();
    }

    @Override
    protected LiveData<ViewState> getViewStateLiveData() {
        return viewModel.getViewState();
    }

    @Override
    protected void handleViewState(ViewState viewState) {

    }

    @Override
    protected void initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoDetailViewModel.class);
    }

    @Override
    protected boolean onBackPressed() {
        navigationManager.navigateBack(null);
        return false;
    }

    @Override
    protected void initView() {

    }
}
