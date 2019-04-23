package com.systena.githupdemo.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.ActivityMainBinding;
import com.systena.githupdemo.navigation.NavigationManager;
import com.systena.githupdemo.ui.base.BaseActivity;
import com.systena.githupdemo.ui.splash.SplashFragment;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    protected void showDialogLostInternet() {
        showDialogError("Please check Network Connection", getString(R.string.ok), true, null);
    }

    @Override
    protected void showDialogUnknownError() {
        showDialogError("unknown error occurred", getString(R.string.ok), true, null);
    }

    @Override
    protected void showDialogTokenExpired() {
        showDialogError("Access token expired", getString(R.string.ok), true, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        navigationManager = new NavigationManager(this.getSupportFragmentManager(), R.id.rlMain);

        navigationManager.openNoAddToBackStack(SplashFragment.class, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
