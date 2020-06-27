package com.example.javaapp.data.car.source.remote;

import com.example.javaapp.data.base_response.BaseResponse;
import com.example.javaapp.data.car.Car;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CarRetrofitService {

    @GET("CompanyUsers")
    Single<BaseResponse<Car>> getCarsList(@Path("page") int pageNumber);

}
