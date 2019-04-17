package com.systena.githupdemo.ui.github;


import android.text.TextUtils;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentGithubBinding;
import com.systena.githupdemo.ui.adapter.RepoAdapter;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;
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
        adapter = new RepoAdapter();
        binding.rvRepository.setAdapter(adapter);
        binding.rvRepository.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.btnSearch.setOnClickListener(v -> handleSearch());
    }

    private void handleSearch() {
        String key = binding.etSearch.getText().toString();
        if (!TextUtils.isEmpty(key)) {
            //viewModel.searchRepo(key);
            viewModel.searchRepo(key).observe(this.getViewLifecycleOwner(), repos -> {
                if (repos != null) {
                    adapter.setRepos(repos.getItems());
                }
            });
        }
    }

}
