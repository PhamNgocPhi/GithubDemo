package com.systena.githupdemo.ui.github.list;

import com.systena.githupdemo.data.datasource.factory.RepoDataSourceFactory;
import com.systena.githupdemo.data.model.Repo;
import com.systena.githupdemo.data.model.RepoResponse;
import com.systena.githupdemo.data.repository.RepoRepository;
import com.systena.githupdemo.ui.base.BaseViewModel;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.util.common.Define;
import com.systena.githupdemo.util.common.ResourceProvider;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import io.reactivex.disposables.CompositeDisposable;

public class GithubViewModel extends BaseViewModel {

    private ResourceProvider resourceProvider;
    private RepoRepository repoRepository;
    private MutableLiveData<RepoResponse> reposLiveData;

    private RepoDataSourceFactory repoDataSourceFactory;
    LiveData<PagedList<Repo>> repoPagedList;
    LiveData<PageKeyedDataSource<Integer, Repo>> liveDataSource;

    @Inject
    public GithubViewModel(ResourceProvider resourceProvider, RepoDataSourceFactory repoDataSourceFactory) {
        disposable = new CompositeDisposable();
        mViewState = new MutableLiveData<>();
        this.resourceProvider = resourceProvider;
        this.repoDataSourceFactory = repoDataSourceFactory;
        reposLiveData = new MutableLiveData<>();

        liveDataSource = repoDataSourceFactory.getRepoLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();
        //Building the paged list
        repoPagedList = (new LivePagedListBuilder(repoDataSourceFactory, pagedListConfig))
                .build();
    }

//    public LiveData<RepoResponse> searchRepo(String key) {
//        ViewState viewState = new ViewState();
//        disposable.add(
//                repoRepository.searchRepo(key)
//                        .doOnSubscribe(it -> {
//                            viewState.setState(Define.ViewState.SHOW_LOADING);
//                            mViewState.setValue(viewState);
//                        })
//                        .subscribe(data -> {
//                            viewState.setState(Define.ViewState.HIDE_lOADING);
//                            mViewState.setValue(viewState);
//                            reposLiveData.setValue(data);
//                        }, throwable -> {
//                            viewState.setState(Define.ViewState.Github.SEARCH_ERROR);
//                            viewState.setObjectData(throwable);
//                            mViewState.setValue(viewState);
//                        })
//        );
//        return reposLiveData;
//    }
//
//    public LiveData<RepoResponse> getReposLiveData() {
//        return reposLiveData;
//    }
}
