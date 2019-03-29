package com.systena.githupdemo;

import com.facebook.stetho.Stetho;
import com.systena.githupdemo.di.component.AppComponent;
import com.systena.githupdemo.di.component.DaggerAppComponent;
import com.systena.githupdemo.util.network.RxBus;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class GithubApplication extends DaggerApplication {

    private RxBus rxBus;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        rxBus = new RxBus();
    }

    public RxBus getRxBus() {
        return rxBus;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }
}
