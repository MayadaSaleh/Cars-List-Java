package com.example.javaapp.data.car.source.remote;

import com.example.javaapp.data.base_response.BaseResponse;
import com.example.javaapp.data.car.Car;
import com.example.javaapp.data.car.CarResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class CarRemoteDataSource {

    private final CarRetrofitService carRetrofitService;

    @Inject
    public CarRemoteDataSource(CarRetrofitService carRetrofitService) {
        this.carRetrofitService = carRetrofitService;
    }

    public Single<CarResponse> getCarsList(int pageNumber) {
        return carRetrofitService.getCarsList(pageNumber);
    }

}
