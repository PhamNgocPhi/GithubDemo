package com.systena.githupdemo.ui.splash;

import com.google.firebase.auth.FirebaseAuth;
import com.systena.githupdemo.ui.base.BaseViewModel;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.util.common.Define;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

public class SplashViewModel extends BaseViewModel {

    private FirebaseAuth firebaseAuth;

    @Inject
    public SplashViewModel() {
        disposable = new CompositeDisposable();
        firebaseAuth = FirebaseAuth.getInstance();
        mViewState = new MutableLiveData<>();
        checkLoggedIn();
    }

    private void checkLoggedIn() {
        ViewState viewState = new ViewState();
        if(firebaseAuth.getCurrentUser() == null) {
            viewState.setState(Define.ViewState.Splash.GO_LOGIN);
        } else {
            viewState.setState(Define.ViewState.Splash.GO_HOME);
        }
        mViewState.setValue(viewState);
    }
}
