package com.example.javaapp.data.car.source;

import com.example.javaapp.data.base_response.BaseResponse;
import com.example.javaapp.data.car.Car;
import com.example.javaapp.data.car.source.remote.CarRemoteDataSource;

import javax.inject.Inject;

import io.reactivex.Single;

public class CarRepository {

    private final CarRemoteDataSource remoteDataSource;

    @Inject
    public CarRepository(CarRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public Single<BaseResponse<Car>> getCarsList(int pageNumber) {
        return remoteDataSource.getCarsList(pageNumber);
    }
}
