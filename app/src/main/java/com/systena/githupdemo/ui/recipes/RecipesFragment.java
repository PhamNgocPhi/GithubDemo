package com.systena.githupdemo.ui.recipes;


import android.os.Bundle;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.FragmentRecipesBinding;
import com.systena.githupdemo.ui.adapter.RecipesAdapter;
import com.systena.githupdemo.ui.base.BaseFragment;
import com.systena.githupdemo.ui.base.ViewState;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends BaseFragment<FragmentRecipesBinding> {

    private RecipesViewModel viewModel;
    private RecipesAdapter adapter;

    public RecipesFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_recipes;
    }

    @Override
    protected void resetViewState() {
        viewModel.resetViewState();
    }

    @Override
    protected LiveData<ViewState> getViewStateLiveData() {
        return viewModel.getViewState();
    }

    @Override
    protected void handleViewState(ViewState viewState) {

    }

    @Override
    protected void initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipesViewModel.class);
    }

    @Override
    protected void initView() {
        adapter = new RecipesAdapter();
        binding.rvRecipes.setAdapter(adapter);
        binding.rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected boolean onBackPressed() {
        return false;
    }

    @Override
    protected void handleReceivedData(Bundle bundle) {

    }
}
