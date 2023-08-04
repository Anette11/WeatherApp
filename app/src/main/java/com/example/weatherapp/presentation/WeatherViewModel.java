package com.example.weatherapp.presentation;

import androidx.lifecycle.ViewModel;

import com.example.weatherapp.domain.data.GetWeatherResponse;
import com.example.weatherapp.domain.use_case.GetWeatherUseCase;
import com.example.weatherapp.utils.LocationCoordinatesContainer;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private final GetWeatherUseCase getWeatherUseCase;
    private final LocationCoordinatesContainer locationCoordinatesContainer;

    @Inject
    public WeatherViewModel(
            GetWeatherUseCase getWeatherUseCase,
            LocationCoordinatesContainer locationCoordinatesContainer
    ) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.locationCoordinatesContainer = locationCoordinatesContainer;
    }

    public LocationCoordinatesContainer getLocationCoordinatesContainer() {
        return locationCoordinatesContainer;
    }

    public void getWeather(
            double latitude,
            double longitude
    ) {
        getWeatherUseCase.execute(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<GetWeatherResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Timber.d("onSubscribe()");
                    }

                    @Override
                    public void onSuccess(@NonNull GetWeatherResponse getWeatherResponse) {
                        Timber.d("onSuccess()");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.d("onError()");
                    }
                });
    }
}