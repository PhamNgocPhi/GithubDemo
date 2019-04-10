package com.systena.githupdemo.ui.register;

import com.google.firebase.auth.FirebaseAuth;
import com.systena.githupdemo.ui.base.BaseViewModel;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.util.common.Define;

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

    public void register(String email, String pass) {
        firebaseAuth
                .createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mViewState.setValue(new ViewState(Define.ViewState.Register.GO_HOME));
                    } else {
                        mViewState.setValue(new ViewState(Define.ViewState.SHOW_ERROR));
                    }
                });
    }
}
