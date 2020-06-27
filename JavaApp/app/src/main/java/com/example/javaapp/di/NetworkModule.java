package com.example.javaapp.di;

import androidx.annotation.NonNull;

import com.example.javaapp.Constants;
import com.example.javaapp.data.car.source.remote.CarRetrofitService;
import com.example.javaapp.di.scopes.AppScope;
import com.example.javaapp.utils.network_utils.RetrofitInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @AppScope
    @Provides
    public HttpLoggingInterceptor provideOkHttpInterceptors() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @AppScope
    @Provides
    public RetrofitInterceptor provideRetrofitInterceptor() {
        return new RetrofitInterceptor();
    }

    @AppScope
    @Provides
    public Retrofit provideRetrofit(@NonNull OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson)) // Serialize Objects
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

    }

    @AppScope
    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, RetrofitInterceptor retrofitInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(retrofitInterceptor)
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }


    @AppScope
    @Provides
    public Gson provideGsonFactory() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    @AppScope
    @Provides
    public CarRetrofitService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(CarRetrofitService.class);
    }
}