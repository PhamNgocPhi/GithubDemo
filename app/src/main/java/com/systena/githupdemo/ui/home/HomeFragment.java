package com.systena.githupdemo.ui.home;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentHomeBinding;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.ui.github.GithubFragment;
import com.systena.githupdemo.ui.recipes.RecipesFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

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
    protected void resetViewState() {
        viewmodel.resetViewState();
    }

    @Override
    protected LiveData<ViewState> getViewStateLiveData() {
        return viewmodel.getViewState();
    }

    @Override
    protected void handleViewState(ViewState viewState) {

    }

    @Override
    protected void initViewModel() {
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
    }

    @Override
    protected boolean onBackPressed() {
        if (!navigationManager.navigateBack()) {
            getBaseActivity().moveTaskToBack(true);
        }
        return false;
    }

    @Override
    protected void initView() {
        binding.bnvBottomBar.setOnNavigationItemSelectedListener(listener);

        loadFragment(new RecipesFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = item -> {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigationHome:
                fragment = new RecipesFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigationGithub:
                fragment = new GithubFragment();
                loadFragment(fragment);
                return true;
        }

        return false;
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getBaseActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContainer, fragment);
        transaction.commit();
    }

}
