package com.systena.githupdemo.di.component;

import android.app.Application;

import com.systena.githupdemo.GithubApplication;
import com.systena.githupdemo.di.module.ActivityBindingModule;
import com.systena.githupdemo.di.module.AppDatabaseModule;
import com.systena.githupdemo.di.module.AppModule;
import com.systena.githupdemo.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        NetworkModule.class,
        AppModule.class,
        AppDatabaseModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(GithubApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
