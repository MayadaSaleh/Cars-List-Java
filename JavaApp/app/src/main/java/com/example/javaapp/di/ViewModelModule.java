package com.example.javaapp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.javaapp.di.scopes.AppScope;
import com.example.javaapp.modules.carsList.CarsListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CarsListViewModel.class)
    abstract ViewModel bindCarsListViewModel(CarsListViewModel listViewModel);

    @Binds
    @AppScope
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}