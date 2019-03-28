package com.systena.githupdemo.di.component;

import android.app.Application;

import com.systena.githupdemo.GithubApplication;
import com.systena.githupdemo.di.module.ActivityBindingModule;
import com.systena.githupdemo.di.module.AppDatabaseModule;
import com.systena.githupdemo.di.module.AppModule;
import com.systena.githupdemo.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ActivityBindingModule.class,
        ApplicationModule.class,
        AppModule.class,
        AppDatabaseModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent {
    void inject(GithubApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
