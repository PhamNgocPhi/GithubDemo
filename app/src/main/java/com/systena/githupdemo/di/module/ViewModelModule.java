package com.systena.githupdemo.di.module;

import com.systena.githupdemo.di.util.ViewModelFactory;
import com.systena.githupdemo.di.util.ViewModelKey;
import com.systena.githupdemo.ui.github.GithubViewModel;
import com.systena.githupdemo.ui.home.HomeViewModel;
import com.systena.githupdemo.ui.login.LoginViewModel;
import com.systena.githupdemo.ui.main.MainViewModel;
import com.systena.githupdemo.ui.recipes.RecipesViewModel;
import com.systena.githupdemo.ui.register.RegisterViewModel;
import com.systena.githupdemo.ui.splash.SplashViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    abstract ViewModel bindRegisterViewModel(RegisterViewModel registerViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RecipesViewModel.class)
    abstract ViewModel bindRecipesViewModel(RecipesViewModel recipesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(GithubViewModel.class)
    abstract ViewModel bindGithubViewModel(GithubViewModel githubViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
