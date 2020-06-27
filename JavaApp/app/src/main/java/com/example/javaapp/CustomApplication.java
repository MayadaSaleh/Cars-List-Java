package com.example.javaapp;

import com.example.javaapp.di.AppComponent;
import com.example.javaapp.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class CustomApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}