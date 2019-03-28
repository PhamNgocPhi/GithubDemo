package com.systena.githupdemo.di.module;

import com.systena.githupdemo.di.util.ViewModelFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {
//    @Binds
//    @IntoMap
//    @ViewModelKey(ListViewModel.class)
//    abstract ViewModel bindListViewModel(ListViewModel listViewModel);
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailsViewModel.class)
//    abstract ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
