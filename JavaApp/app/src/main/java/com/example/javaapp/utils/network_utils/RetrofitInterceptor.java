package com.example.javaapp.utils.network_utils;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetrofitInterceptor implements Interceptor {

    @Inject
    public RetrofitInterceptor() {
    }

    @Override
    @NonNull
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        return chain.proceed(request);
    }
}