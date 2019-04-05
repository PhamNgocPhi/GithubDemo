package com.systena.githupdemo.ui.register;


import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentRegisterBinding;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> {

    private RegisterViewModel registerViewModel;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_register;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerViewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel.class);

        registerViewModel.getViewState().observe(this.getViewLifecycleOwner(), this::handleViewState);
    }

    private void handleViewState(ViewState viewState) {
        if (viewState == null) {
            return;
        }

    }
}
