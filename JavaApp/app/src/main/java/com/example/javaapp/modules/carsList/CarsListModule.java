package com.example.javaapp.modules.carsList;

import com.example.javaapp.di.scopes.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CarsListModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract CarsListFragment provideCarsListFragment();
}