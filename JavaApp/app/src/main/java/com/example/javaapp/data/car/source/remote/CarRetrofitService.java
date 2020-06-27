package com.example.javaapp.data.car.source.remote;

import com.example.javaapp.data.car.CarResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CarRetrofitService {

    @GET("cars?")
    Single<CarResponse> getCarsList(@Query("page") int pageNumber);

}
