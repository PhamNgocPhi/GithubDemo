package com.systena.githupdemo.ui.login;

import com.google.firebase.auth.FirebaseAuth;
import com.systena.githupdemo.ui.base.BaseViewModel;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.util.common.Define;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends BaseViewModel {

    private FirebaseAuth firebaseAuth;


    @Inject
    public LoginViewModel() {
        disposable = new CompositeDisposable();
        mViewState = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(String email, String pass) {
        firebaseAuth
                .signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mViewState.setValue(new ViewState(Define.ViewState.Login.GO_HOME));
                    } else {
                        ViewState viewState = new ViewState(Define.ViewState.SHOW_ERROR);
                        viewState.setObjectData(task.getException().getMessage());
                        mViewState.setValue(viewState);
                    }
                });
    }

}
