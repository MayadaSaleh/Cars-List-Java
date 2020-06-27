package com.example.javaapp.di;

import com.example.javaapp.di.scopes.ActivityScope;
import com.example.javaapp.modules.carsList.CarsListActivity;
import com.example.javaapp.modules.carsList.CarsListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {CarsListModule.class})
    abstract CarsListActivity bindCarsListActivity();
}