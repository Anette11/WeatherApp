package com.example.weatherapp.presentation;

import androidx.lifecycle.ViewModel;

import com.example.weatherapp.domain.data.GetWeatherResponse;
import com.example.weatherapp.domain.use_case.GetWeatherUseCase;

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

    @Inject
    public WeatherViewModel(GetWeatherUseCase getWeatherUseCase) {
        this.getWeatherUseCase = getWeatherUseCase;
    }

    private Double latitude = 0.0;
    private Double longitude = 0.0;

    public void getWeather() {
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