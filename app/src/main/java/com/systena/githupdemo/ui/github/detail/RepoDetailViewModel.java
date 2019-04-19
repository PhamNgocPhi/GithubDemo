package com.systena.githupdemo.ui.github.detail;

import com.systena.githupdemo.ui.base.BaseViewModel;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

public class RepoDetailViewModel extends BaseViewModel {

    @Inject
    public RepoDetailViewModel() {
        disposable = new CompositeDisposable();
        mViewState = new MutableLiveData<>();
    }

}
