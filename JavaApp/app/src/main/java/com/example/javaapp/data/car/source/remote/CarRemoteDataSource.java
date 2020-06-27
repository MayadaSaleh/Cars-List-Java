package com.example.javaapp.data.car.source.remote;

import com.example.javaapp.data.base_response.BaseResponse;
import com.example.javaapp.data.car.Car;

import javax.inject.Inject;

import io.reactivex.Single;

public class CarRemoteDataSource {

    private final CarRetrofitService carRetrofitService;

    @Inject
    public CarRemoteDataSource(CarRetrofitService carRetrofitService) {
        this.carRetrofitService = carRetrofitService;
    }

    public Single<BaseResponse<Car>> getCarsList(int pageNumber) {
        return carRetrofitService.getCarsList(pageNumber);
    }

}
