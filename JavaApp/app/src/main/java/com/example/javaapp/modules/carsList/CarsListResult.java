package com.example.javaapp.modules.carsList;

import com.example.javaapp.data.base_response.BaseResponse;
import com.example.javaapp.data.car.Car;

import java.util.List;

class CarsListResult {
    private final List<CarListItemBinder> carsItems;
    private final BaseResponse<Car> response;
    private final Boolean isLoadMore;

    CarsListResult(List<CarListItemBinder> carsItems, BaseResponse<Car> response, Boolean isLoadMore) {
        this.carsItems = carsItems;
        this.response = response;
        this.isLoadMore = isLoadMore;
    }

    public List<CarListItemBinder> getCarsItems() {
        return carsItems;
    }

    public BaseResponse<Car> getResponse() {
        return response;
    }

    public Boolean getLoadMore() {
        return isLoadMore;
    }
}
