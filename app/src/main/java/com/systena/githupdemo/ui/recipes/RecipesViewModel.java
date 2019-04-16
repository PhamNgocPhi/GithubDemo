package com.systena.githupdemo.ui.recipes;

import com.systena.githupdemo.ui.base.BaseViewModel;
import com.systena.githupdemo.util.common.ResourceProvider;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

public class RecipesViewModel extends BaseViewModel {

    private ResourceProvider resourceProvider;

    @Inject
    public RecipesViewModel(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
        disposable = new CompositeDisposable();
        mViewState = new MutableLiveData<>();
    }



}
