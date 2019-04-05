package com.systena.githupdemo.ui.splash;


import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentSplashBinding;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends BaseFragment<FragmentSplashBinding> {

    private SplashViewModel viewModel;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_splash;
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel.class);
    }

    @Override
    protected void initView() {

    }
}
