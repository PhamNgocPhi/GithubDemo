package com.systena.githupdemo.ui.login;


import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentLoginBinding;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.ui.home.HomeFragment;
import com.systena.githupdemo.ui.register.RegisterFragment;
import com.systena.githupdemo.util.common.Define;

import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    private LoginViewModel loginViewModel;
    private CallbackManager callbackManager;

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
        hideLoading();
        switch (viewState.getState()) {
            case Define.ViewState.Login.LOGIN_FAILED:
            case Define.ViewState.Login.ERROR_VALIDATE:
                showErrorDialog(viewState.getMessage());
                break;
            case Define.ViewState.Login.GO_HOME:
                navigationManager.openAsRoot(HomeFragment.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initViewModel() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
    }

    @Override
    protected void initView() {
        callbackManager = CallbackManager.Factory.create();

        binding.btnLogin.setOnClickListener(v -> onClickLogin());
        binding.llFacebook.setOnClickListener(v -> onClickFacebook());
        binding.tvCreateAccount.setOnClickListener(v -> onClickCreateAccount());
    }

    @Override
    protected boolean onBackPressed() {
        navigationManager.navigateBack();
        return false;
    }

    private void onClickCreateAccount() {
        if (isDuplicateClick()) {
            return;
        }
        navigationManager.open(RegisterFragment.class);
    }

    private void onClickLogin() {
        if (isDuplicateClick()) {
            return;
        }
        showLoading();
        final Animation animation = AnimationUtils.loadAnimation(getBaseActivity(), R.anim.bounce);
        binding.btnLogin.startAnimation(animation);
        String email = binding.etEmail.getText().toString();
        String pass = binding.etPass.getText().toString();
        loginViewModel.login(email, pass);
    }

    private void onClickFacebook() {
        showLoading();
        if (isDuplicateClick()) {
            return;
        }
        final Animation animation = AnimationUtils.loadAnimation(getBaseActivity(), R.anim.bounce);
        binding.llFacebook.startAnimation(animation);

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginViewModel.facebookLogin(loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                hideLoading();
            }

            @Override
            public void onError(FacebookException error) {
                hideLoading();
                showErrorDialog(error.getMessage());
            }
        });

        LoginManager.getInstance().logInWithReadPermissions(getBaseActivity(), Arrays.asList("email", "public_profile"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
