package com.example.javaapp.di;

import com.example.javaapp.di.scopes.AppScope;
import com.example.javaapp.utils.binding_utils.BindingAdapters;

import dagger.Module;
import dagger.Provides;

@Module
class DataBindingModule {

    @Provides
    @AppScope
    public BindingAdapters provideBindingAdapters() {
        return new BindingAdapters();
    }
}