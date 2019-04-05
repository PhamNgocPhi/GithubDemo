package com.systena.githupdemo.ui.splash;


import android.os.Bundle;
import android.view.View;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentSplashBinding;
import com.systena.githupdemo.ui.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideActionBar();

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel.class);
    }
}
