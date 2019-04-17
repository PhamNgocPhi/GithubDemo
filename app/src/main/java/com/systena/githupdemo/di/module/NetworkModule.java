package com.systena.githupdemo.di.module;

import android.content.Context;

import com.systena.githupdemo.BuildConfig;
import com.systena.githupdemo.data.network.ApiService;
import com.systena.githupdemo.util.common.Define;
import com.systena.githupdemo.util.network.NetworkConnectionInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class NetworkModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new NetworkConnectionInterceptor(context))
                .connectTimeout(Define.Network.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Define.Network.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

}
