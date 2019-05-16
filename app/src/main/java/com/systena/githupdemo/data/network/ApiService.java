package com.systena.githupdemo.data.network;

import com.systena.githupdemo.data.model.RepoResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/repositories?sort=stars")
    Single<RepoResponse> getRepositories(@Query("q") String key, @Query("page") int page, @Query("per_page") int perPage);

}
