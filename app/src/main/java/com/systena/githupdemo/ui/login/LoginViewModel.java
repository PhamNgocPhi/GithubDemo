package com.systena.githupdemo.ui.login;

import com.systena.githupdemo.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends BaseViewModel {

    @Inject
    public LoginViewModel() {
        disposable = new CompositeDisposable();
    }

}
