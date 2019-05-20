package com.systena.githupdemo.data.datasource.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.systena.githupdemo.data.datasource.RepoDataSource;
import com.systena.githupdemo.data.model.Repo;
import com.systena.githupdemo.data.repository.RepoRepository;

import javax.inject.Singleton;

@Singleton
public class RepoDataSourceFactory extends DataSource.Factory<Integer, Repo> {

    private RepoDataSource repoDataSource;
    private MutableLiveData<PageKeyedDataSource<Integer, Repo>> repoLiveDataSource;

    public RepoDataSourceFactory(RepoRepository repoRepository, String query) {
        repoLiveDataSource = new MutableLiveData<>();
        repoDataSource = new RepoDataSource(repoRepository, query);
    }

    @NonNull
    @Override
    public DataSource<Integer, Repo> create() {
        repoLiveDataSource.postValue(repoDataSource);
        return repoDataSource;
    }

    public RepoDataSource getRepoDataSource() {
        return repoDataSource;
    }
}
