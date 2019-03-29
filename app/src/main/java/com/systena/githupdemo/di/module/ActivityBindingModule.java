package com.systena.githupdemo.di.module;

import com.systena.githupdemo.ui.home.HomeFragment;
import com.systena.githupdemo.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();

}
