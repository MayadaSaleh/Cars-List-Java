package com.example.javaapp.modules.carsList;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.javaapp.data.base_response.BaseResponse;
import com.example.javaapp.data.car.Car;
import com.example.javaapp.data.car.CarResponse;
import com.example.javaapp.data.car.source.CarRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CarsListViewModel extends ViewModel {


    private final CarRepository carRepository;
    private CompositeDisposable disposable;
    private final MutableLiveData<CarsListResult> firstList = new MutableLiveData<>();
    private final MutableLiveData<CarsListResult> newCars = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<String> errorMessages = new MutableLiveData<>();
    private int pageNumber = 1;
    private Boolean fromLoadMore;
    private Boolean loadMore = true;

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    LiveData<String> getErrorMessages() {
        return errorMessages;
    }

    LiveData<CarsListResult> getNewCars() {
        return newCars;
    }

    LiveData<CarsListResult> getFirstList() {
        return firstList;
    }

    @Inject
    public CarsListViewModel(CarRepository carRepository) {
        this.carRepository = carRepository;
        disposable = new CompositeDisposable();
        fromLoadMore = false;
        getData();

    }

    private void getData() {
        if (!fromLoadMore) {
            pageNumber = 1;
            loading.setValue(true);
        }

        disposable.add(carRepository.getCarsList(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CarResponse>() {

                    @Override
                    public void onSuccess(CarResponse resultsResponse) {
                        loading.setValue(false);

                        if (resultsResponse != null) {
                            if (resultsResponse.getError() != null) {
                                loadMore = false;
                                errorMessages.setValue(resultsResponse.getError().getMessage());
                            } else {
                                mapResponseToCarBinder(resultsResponse);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.setValue(false);
                        errorMessages.setValue(e.getMessage());
                    }
                }));
    }

    private void mapResponseToCarBinder(BaseResponse<Car> result) {
        List<CarListItemBinder> toBindList = new ArrayList<>();

        for (Car car : result.getData()) {
            CarListItemBinder newItem = new CarListItemBinder(car.getBrand(), "Is used: " + car.getUsed().toString(), car.getImageUrl());
            toBindList.add(newItem);
        }

        if (fromLoadMore) {
            newCars.setValue(new CarsListResult(toBindList, result,
                    loadMore));
        } else {
            firstList.setValue(new CarsListResult(toBindList, result,
                    loadMore));
        }
    }

    void onLoadMore() {
        pageNumber++;
        fromLoadMore = true;
        getData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

}