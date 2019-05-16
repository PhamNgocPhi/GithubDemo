package com.systena.githupdemo.data.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.systena.githupdemo.data.model.Repo;
import com.systena.githupdemo.data.repository.RepoRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class RepoDataSource extends PageKeyedDataSource<Integer, Repo> {

    private RepoRepository repoRepository;
    private CompositeDisposable compositeDisposable;

    @Inject
    public RepoDataSource(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Repo> callback) {
        compositeDisposable.add(repoRepository.searchRepo("mvvm", 0, params.requestedLoadSize)
                .doOnSubscribe(it -> {

                })
                .subscribe(repoResponse -> {
                    callback.onResult(repoResponse.getItems(), null, 1);
                }, throwable -> {

                })
        );
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repo> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repo> callback) {
        compositeDisposable.add(repoRepository.searchRepo("mvvm", params.key, params.requestedLoadSize)
                .doOnSubscribe(it -> {

                })
                .subscribe(repoResponse -> {
                    //Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                    callback.onResult(repoResponse.getItems(), params.key + 1);
                }, throwable -> {

                })
        );
    }
}
