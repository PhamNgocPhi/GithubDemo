package com.systena.githupdemo.ui.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentHomeBinding;
import com.systena.githupdemo.ui.base.BaseFragment;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
    }
}
