package com.systena.githupdemo.ui.main;

import android.os.Bundle;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.ActivityMainBinding;
import com.systena.githupdemo.ui.base.BaseActivity;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private MainViewModel mainViewModel;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
    }
}
