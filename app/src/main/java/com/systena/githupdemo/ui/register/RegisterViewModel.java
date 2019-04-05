package com.systena.githupdemo.ui.register;

import com.google.firebase.auth.FirebaseAuth;
import com.systena.githupdemo.ui.base.BaseViewModel;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

public class RegisterViewModel extends BaseViewModel {

    private FirebaseAuth firebaseAuth;

    @Inject
    public RegisterViewModel() {
        disposable = new CompositeDisposable();
        mViewState = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();
    }
}
