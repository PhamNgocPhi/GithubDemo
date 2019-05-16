package com.systena.githupdemo.data.datasource.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.systena.githupdemo.data.datasource.RepoDataSource;
import com.systena.githupdemo.data.model.Repo;

import javax.inject.Inject;
import javax.inject.Singleton;

public class RepoDataSourceFactory extends DataSource.Factory<Integer, Repo> {

    private RepoDataSource repoDataSource;
    private MutableLiveData<PageKeyedDataSource<Integer, Repo>> repoLiveDataSource;

    @Inject
    public RepoDataSourceFactory(RepoDataSource repoDataSource) {
        repoLiveDataSource = new MutableLiveData<>();
        this.repoDataSource = repoDataSource;
    }

    @NonNull
    @Override
    public DataSource<Integer, Repo> create() {
        repoLiveDataSource.postValue(repoDataSource);
        return repoDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Repo>> getRepoLiveDataSource() {
        return repoLiveDataSource;
    }
}
