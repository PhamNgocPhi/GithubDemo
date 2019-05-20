package com.systena.githupdemo.data.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.systena.githupdemo.data.model.Repo;
import com.systena.githupdemo.data.repository.RepoRepository;
import com.systena.githupdemo.ui.base.ViewState;
import com.systena.githupdemo.util.common.Define;

import io.reactivex.disposables.CompositeDisposable;

public class RepoDataSource extends PageKeyedDataSource<Integer, Repo> {

    private int totalItemLoaded;
    private RepoRepository repoRepository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<ViewState> viewState;
    private String query;

    //for retry load more
    private LoadParams<Integer> params;
    private LoadCallback<Integer, Repo> callback;

    public RepoDataSource(RepoRepository repoRepository, String query) {
        this.repoRepository = repoRepository;
        compositeDisposable = new CompositeDisposable();
        viewState = new MutableLiveData<>();
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Repo> callback) {
        totalItemLoaded = 0;
        compositeDisposable.add(repoRepository.searchRepo(query, 0, params.requestedLoadSize)
                .doOnSubscribe(it ->
                        viewState.postValue(new ViewState(Define.ViewState.SHOW_LOADING, null, null)))
                .subscribe(
                        repoResponse -> {
                            viewState.postValue(new ViewState(Define.ViewState.HIDE_LOADING, null, null));
                            totalItemLoaded = repoResponse.getItemsSize();
                            callback.onResult(repoResponse.getItems(), null, 1);
                        },
                        throwable ->
                                viewState.postValue(new ViewState(Define.ViewState.SHOW_ERROR, throwable, null))
                )
        );
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repo> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repo> callback) {
        this.params = params;
        this.callback = callback;
        compositeDisposable.add(repoRepository.searchRepo(query, params.key, params.requestedLoadSize)
                .doOnSubscribe(it ->
                        viewState.postValue(new ViewState(Define.ViewState.LOADING_MORE, null, null)))
                .subscribe(
                        repoResponse -> {
                            viewState.postValue(new ViewState(Define.ViewState.HIDE_LOAD_MORE, null, null));
                            totalItemLoaded += repoResponse.getItemsSize();
                            Integer nextKey = repoResponse.getTotalCount() == totalItemLoaded ? null : params.key + 1;
                            Log.i("demotest", "gfd " + nextKey);
                            callback.onResult(repoResponse.getItems(), nextKey);
                        },
                        throwable ->
                                viewState.postValue(new ViewState(Define.ViewState.ERROR_LOAD_MORE, throwable, null))
                )
        );
    }

    public void clear() {
        compositeDisposable.clear();
        viewState.postValue(null);
        totalItemLoaded = 0;
        query = "";
    }

    public void retryLoadMore() {
        loadAfter(params, callback);
    }

    public LiveData<ViewState> getViewState() {
        return viewState;
    }
}
