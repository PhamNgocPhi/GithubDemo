package com.systena.githupdemo.di.module;

import com.systena.githupdemo.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

//    bind fragment or activity in here
//    @ContributesAndroidInjector
//    abstract CustomerListFragment bindCustomerListFragment();

}
