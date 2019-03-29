package com.systena.githupdemo.ui.home;

import com.systena.githupdemo.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends BaseViewModel {

    @Inject
    public HomeViewModel() {
        disposable = new CompositeDisposable();
    }
}
