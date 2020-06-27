package com.example.javaapp.di;

import android.app.Application;

import com.example.javaapp.CustomApplication;
import com.example.javaapp.di.scopes.AppScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@AppScope
@Component(modules = {AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        DataBindingModule.class,
        AppModule.class, ViewModelModule.class, ActivitiesModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(CustomApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}