package com.systena.githupdemo.ui.login;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentLoginBinding;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.ui.home.HomeFragment;
import com.systena.githupdemo.ui.register.RegisterFragment;
import com.systena.githupdemo.util.common.Define;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    protected int layoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    protected void resetViewState() {
        loginViewModel.resetViewState();
    }

    @Override
    protected LiveData<ViewState> getViewStateLiveData() {
        return loginViewModel.getViewState();
    }

    @Override
    protected void handleViewState(ViewState viewState) {
        switch (viewState.getState()) {
            case Define.ViewState.SHOW_ERROR:
                Toast.makeText(getBaseActivity(), viewState.getObjectData().toString(), Toast.LENGTH_LONG).show();
                break;
            case Define.ViewState.Login.GO_HOME:
                navigationManager.open(HomeFragment.class);
                Toast.makeText(getBaseActivity(), "home", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void initViewModel() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
    }

    @Override
    protected void initView() {
        binding.btnLogin.setOnClickListener(v -> {
            loginViewModel.login(binding.tietEmail.getText().toString(), binding.tietPass.getText().toString());
        });

        binding.btnRegister.setOnClickListener(v -> navigationManager.open(RegisterFragment.class));
    }

}
