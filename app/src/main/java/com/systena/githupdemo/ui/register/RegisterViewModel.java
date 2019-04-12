package com.systena.githupdemo.ui.register;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.firebase.auth.FirebaseAuth;
import com.systena.githupdemo.R;
import com.systena.githupdemo.ui.base.BaseViewModel;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.util.common.Define;
import com.systena.githupdemo.util.common.ResourceProvider;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

public class RegisterViewModel extends BaseViewModel {

    private FirebaseAuth firebaseAuth;
    private ResourceProvider resourceProvider;

    @Inject
    public RegisterViewModel(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
        disposable = new CompositeDisposable();
        mViewState = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register(String email, String pass) {
        if (validate(email, pass)) {
            firebaseAuth
                    .createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            mViewState.setValue(new ViewState(Define.ViewState.Register.GO_HOME));
                        } else {
                            ViewState viewState = new ViewState(Define.ViewState.Register.REGISTER_FAILED);
                            String message = task.getException() == null ?
                                    resourceProvider.getString(R.string.error_unknown)
                                    : task.getException().getMessage();
                            viewState.setObjectData(message);
                            mViewState.setValue(viewState);
                        }
                    });
        }
    }

    private boolean validate(String email, String pass) {
        ViewState viewState = new ViewState(Define.ViewState.Register.ERROR_VALIDATE);
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
