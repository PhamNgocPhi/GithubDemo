package com.systena.githupdemo.di.module;

import android.app.Application;
import android.content.Context;

import com.systena.githupdemo.util.common.IResourceProvider;
import com.systena.githupdemo.util.common.ResourceProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {
    @Binds
    @Singleton
    abstract Context provideContext(Application application);

    @Binds
    @Singleton
    abstract IResourceProvider provideResourceProvider(ResourceProvider resourceProvider);
}
