package com.systena.githupdemo.ui.splash;

import com.systena.githupdemo.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SplashViewModel extends BaseViewModel {
    @Inject
    public SplashViewModel() {
        disposable = new CompositeDisposable();
    }
}
