package com.systena.githupdemo.data.repository;

import com.systena.githupdemo.data.AppDatabase;
import com.systena.githupdemo.data.model.RepoResponse;
import com.systena.githupdemo.data.network.ApiService;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepoRepository {
    private AppDatabase appDatabase;
    private ApiService apiService;

    @Inject
    public RepoRepository(AppDatabase appDatabase, ApiService apiService) {
        this.appDatabase = appDatabase;
        this.apiService = apiService;
    }

    public Single<RepoResponse> searchRepo(String key) {
        key = key + "+language:java";
        return apiService.getRepositories(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
