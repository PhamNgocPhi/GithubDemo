package com.systena.githupdemo.ui.splash;


import android.os.Bundle;
import android.os.Handler;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentSplashBinding;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.ui.home.HomeFragment;
import com.systena.githupdemo.ui.login.LoginFragment;
import com.systena.githupdemo.util.common.Define;

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
        hideLoading();
        switch (viewState.getState()) {
            case Define.ViewState.Splash.GO_HOME:
                new Handler().postDelayed(() -> navigationManager.open(HomeFragment.class, null), 2000);
                break;
            case Define.ViewState.Splash.GO_LOGIN:
                new Handler().postDelayed(() -> navigationManager.open(LoginFragment.class, null), 2000);
                break;
        }
    }

    @Override
    protected void initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel.class);
    }

    @Override
    protected void initView() {
        showLoading();
    }

    @Override
    protected boolean onBackPressed() {
        return false;
    }

    @Override
    protected void handleReceivedData(Bundle bundle) {

    }
}
