package com.systena.githupdemo.ui.base;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposable;

    @Override
    protected void onCleared() {
        super.onCleared();

        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
