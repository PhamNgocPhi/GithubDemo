package com.systena.githupdemo.ui.main;

import com.systena.githupdemo.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainViewModel extends BaseViewModel {

    @Inject
    public MainViewModel() {
        disposable = new CompositeDisposable();
    }
}
