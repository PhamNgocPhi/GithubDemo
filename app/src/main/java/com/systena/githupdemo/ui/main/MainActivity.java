package com.systena.githupdemo.ui.main;

import android.os.Bundle;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.ActivityMainBinding;
import com.systena.githupdemo.ui.NavigationManager;
import com.systena.githupdemo.ui.base.BaseActivity;
import com.systena.githupdemo.ui.login.LoginFragment;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private MainViewModel mainViewModel;

    private NavigationManager navigationManager;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected NavigationManager getNavigationManager() {
        return navigationManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        navigationManager = new NavigationManager(this.getSupportFragmentManager(), R.id.rlMain);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        navigationManager.openNoAddToBackStack(LoginFragment.class);
    }
}
