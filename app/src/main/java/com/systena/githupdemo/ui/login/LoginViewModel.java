package com.systena.githupdemo.ui.login;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.systena.githupdemo.R;
import com.systena.githupdemo.ui.base.BaseViewModel;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.util.common.Define;
import com.systena.githupdemo.util.common.ResourceProvider;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends BaseViewModel {

    private FirebaseAuth firebaseAuth;
    private ResourceProvider resourceProvider;


    @Inject
    public LoginViewModel(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
        disposable = new CompositeDisposable();
        mViewState = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(String email, String pass) {
        if (validate(email, pass)) {
            firebaseAuth
                    .signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            mViewState.setValue(new ViewState(Define.ViewState.Login.GO_HOME));
                        } else {
                            ViewState viewState = new ViewState(Define.ViewState.Login.LOGIN_FAILED);
                            String message = task.getException() == null ?
                                    resourceProvider.getString(R.string.error_unknown)
                                    : task.getException().getMessage();
                            viewState.setObjectData(message);
                            mViewState.setValue(viewState);
                        }
                    });
        }
    }

    void facebookLogin(String accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mViewState.setValue(new ViewState(Define.ViewState.Login.GO_HOME));
                    } else {
                        ViewState viewState = new ViewState(Define.ViewState.Login.LOGIN_FAILED);
                        String message = task.getException() == null ?
                                resourceProvider.getString(R.string.error_unknown)
                                : task.getException().getMessage();
                        viewState.setObjectData(message);
                        mViewState.setValue(viewState);
                    }
                });
    }

    private boolean validate(String email, String pass) {
        ViewState viewState = new ViewState(Define.ViewState.Login.ERROR_VALIDATE);
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            viewState.setObjectData(resourceProvider.getString(R.string.error_empty_mail_pass));
            mViewState.setValue(viewState);
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewState.setObjectData(resourceProvider.getString(R.string.error_format_mail));
            mViewState.setValue(viewState);
            return false;
        }
        return true;
    }

}
