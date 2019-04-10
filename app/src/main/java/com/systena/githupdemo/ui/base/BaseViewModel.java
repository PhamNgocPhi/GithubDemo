package com.systena.githupdemo.ui.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposable;

    protected MutableLiveData<ViewState> mViewState;

    public LiveData<ViewState> getViewState() {
        return mViewState;
    }

    public void resetViewState() {
        if (mViewState != null) {
            mViewState.setValue(null);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
