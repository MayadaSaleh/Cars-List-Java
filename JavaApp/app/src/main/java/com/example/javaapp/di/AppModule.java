package com.example.javaapp.di;

import android.app.Application;
import android.content.Context;

import com.example.javaapp.di.scopes.AppScope;

import dagger.Binds;
import dagger.Module;

@Module(includes = NetworkModule.class)
public abstract class AppModule {

    @AppScope
    @Binds
    abstract Context provideContext(Application application);
}