package com.systena.githupdemo.ui.register;


import android.app.Fragment;
import android.widget.Toast;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentRegisterBinding;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.ui.home.HomeFragment;
import com.systena.githupdemo.util.common.Define;

import androidx.lifecycle.LiveData;
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
    protected void resetViewState() {
        registerViewModel.resetViewState();
    }

    @Override
    protected LiveData<ViewState> getViewStateLiveData() {
        return registerViewModel.getViewState();
    }

    @Override
    protected void handleViewState(ViewState viewState) {
        switch (viewState.getState()) {
            case Define.ViewState.Register.GO_HOME:
                navigationManager.open(HomeFragment.class);
                break;
            case Define.ViewState.SHOW_ERROR:
                Toast.makeText(getBaseActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initViewModel() {
        registerViewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel.class);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean onBackPressed() {
        navigationManager.navigateBack();
        return false;
    }
}
