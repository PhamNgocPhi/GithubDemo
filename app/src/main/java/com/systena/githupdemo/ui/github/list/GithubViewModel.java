package com.systena.githupdemo.ui.github.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.systena.githupdemo.data.datasource.factory.RepoDataSourceFactory;
import com.systena.githupdemo.data.model.Repo;
import com.systena.githupdemo.data.repository.RepoRepository;
import com.systena.githupdemo.ui.base.BaseViewModel;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.util.common.ResourceProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class GithubViewModel extends BaseViewModel {

    private ResourceProvider resourceProvider;
    private RepoDataSourceFactory repoDataSourceFactory;
    LiveData<PagedList<Repo>> repoPagedList;
    private RepoRepository repoRepository;

    @Inject
    public GithubViewModel(ResourceProvider resourceProvider, RepoRepository repoRepository) {
        disposable = new CompositeDisposable();
        mViewState = new MutableLiveData<>();
        this.resourceProvider = resourceProvider;
        this.repoRepository = repoRepository;
    }

    public LiveData<ViewState> getDataSourceViewState() {
        return repoDataSourceFactory.getRepoDataSource().getViewState();
    }

    public void setQuery(String query) {
        initPagingConfig(query);
    }

    private void initPagingConfig(String query) {
        repoDataSourceFactory = new RepoDataSourceFactory(repoRepository, query);
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();
        //Building the paged list
        repoPagedList = (new LivePagedListBuilder(repoDataSourceFactory, pagedListConfig)).build();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (repoDataSourceFactory != null) {
            repoDataSourceFactory.getRepoDataSource().clear();
        }
    }
}
