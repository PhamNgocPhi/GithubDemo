package com.systena.githupdemo.di.module;

import com.systena.githupdemo.ui.github.detail.RepoDetailFragment;
import com.systena.githupdemo.ui.github.list.GithubFragment;
import com.systena.githupdemo.ui.home.HomeFragment;
import com.systena.githupdemo.ui.login.LoginFragment;
import com.systena.githupdemo.ui.main.MainActivity;
import com.systena.githupdemo.ui.recipes.RecipesFragment;
import com.systena.githupdemo.ui.register.RegisterFragment;
import com.systena.githupdemo.ui.splash.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector
    abstract SplashFragment bindSplashFragment();

    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();

    @ContributesAndroidInjector
    abstract RegisterFragment bindRegisterFragment();

    @ContributesAndroidInjector
    abstract RecipesFragment bindRecipesFragment();

    @ContributesAndroidInjector
    abstract GithubFragment bindGithubFragment();

    @ContributesAndroidInjector
    abstract RepoDetailFragment bindRepoDetailFragment();

}
