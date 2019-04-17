package com.systena.githupdemo.ui.github;

import com.systena.githupdemo.data.model.RepoResponse;
import com.systena.githupdemo.data.repository.RepoRepository;
import com.systena.githupdemo.ui.base.BaseViewModel;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.util.common.Define;
import com.systena.githupdemo.util.common.ResourceProvider;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

public class GithubViewModel extends BaseViewModel {

    private ResourceProvider resourceProvider;
    private RepoRepository repoRepository;
    private MutableLiveData<RepoResponse> reposLiveData;

    @Inject
    public GithubViewModel(ResourceProvider resourceProvider, RepoRepository repoRepository) {
        disposable = new CompositeDisposable();
        mViewState = new MutableLiveData<>();
        this.resourceProvider = resourceProvider;
        this.repoRepository = repoRepository;
        reposLiveData = new MutableLiveData<>();
    }

    public LiveData<RepoResponse> searchRepo(String key) {
        ViewState viewState = new ViewState();
        disposable.add(
                repoRepository.searchRepo(key)
                        .doOnSubscribe(it -> {
                            viewState.setState(Define.ViewState.SHOW_LOADING);
                            mViewState.setValue(viewState);
                        })
                        .subscribe(data -> {
                            viewState.setState(Define.ViewState.HIDE_lOADING);
                            mViewState.setValue(viewState);
                            reposLiveData.setValue(data);
                        }, throwable -> {
                            viewState.setState(Define.ViewState.Github.SEARCH_ERROR);
                            viewState.setObjectData(throwable);
                            mViewState.setValue(viewState);
                        })
        );
        return reposLiveData;
    }

    public LiveData<RepoResponse> getReposLiveData() {
        return reposLiveData;
    }
}
