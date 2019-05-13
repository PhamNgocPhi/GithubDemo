package com.systena.githupdemo.ui.github.list;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentGithubBinding;
import com.systena.githupdemo.ui.adapter.RepoAdapter;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.ui.github.detail.RepoDetailFragment;
import com.systena.githupdemo.util.common.Define;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class GithubFragment extends BaseFragment<FragmentGithubBinding> {

    private GithubViewModel viewModel;
    private RepoAdapter adapter;

    public GithubFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_github;
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
        switch (viewState.getState()) {
            case Define.ViewState.SHOW_LOADING:
                showLoading();
                break;
            case Define.ViewState.HIDE_lOADING:
                hideLoading();
                break;
            case Define.ViewState.Github.SEARCH_ERROR:
                hideLoading();
                break;
            default:
                break;
        }
    }

    @Override
    protected void initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GithubViewModel.class);
    }

    @Override
    protected boolean onBackPressed() {
        return false;
    }

    @Override
    protected void initView() {
        hideEmptyView();
        adapter = new RepoAdapter();
        adapter.setOnItemClick(() -> navigationManager.addFragment(RepoDetailFragment.class, null));
        binding.rvRepository.setAdapter(adapter);
        binding.rvRepository.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.btnSearch.setOnClickListener(v -> handleSearch());
    }

    private void handleSearch() {
        String key = binding.etSearch.getText().toString();
        if (!TextUtils.isEmpty(key)) {
            hideEmptyView();
            viewModel.searchRepo(key).observe(this.getViewLifecycleOwner(), repos -> {
                if (repos != null) {
                    if (repos.getItems() == null || repos.getItems().isEmpty()) {
                        showEmptyView();
                    } else {
                        hideEmptyView();
                        adapter.setRepos(repos.getItems());
                    }
                }
            });
        }
    }

    private void hideEmptyView() {
        binding.lavEmpty.pauseAnimation();
        binding.llEmpty.setVisibility(View.GONE);
        binding.rvRepository.setVisibility(View.VISIBLE);
    }

    private void showEmptyView() {
        binding.lavEmpty.playAnimation();
        binding.llEmpty.setVisibility(View.VISIBLE);
        binding.rvRepository.setVisibility(View.GONE);
    }

}
