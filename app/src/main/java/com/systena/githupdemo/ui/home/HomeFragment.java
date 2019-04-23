package com.systena.githupdemo.ui.home;


import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentHomeBinding;
import com.systena.githupdemo.navigation.BottomNavigation;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.ui.github.list.GithubFragment;
import com.systena.githupdemo.ui.recipes.RecipesFragment;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeViewModel viewmodel;
    private BottomNavigation bottomNavigation;

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
        if (!navigationManager.navigateBack(null)) {
            getBaseActivity().moveTaskToBack(true);
        }
        return false;
    }

    @Override
    protected void handleReceivedData(Bundle bundle) {

    }

    @Override
    protected void initView() {
        binding.bnvBottomBar.setOnNavigationItemSelectedListener(listener);
        bottomNavigation = new BottomNavigation(getChildFragmentManager(), R.id.flContainer);

        bottomNavigation.openFragment(RecipesFragment.class);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = item -> {
        switch (item.getItemId()) {
            case R.id.navigationHome:
                bottomNavigation.openFragment(RecipesFragment.class);
                return true;
            case R.id.navigationGithub:
                bottomNavigation.openFragment(GithubFragment.class);
                return true;
        }

        return false;
    };

}
