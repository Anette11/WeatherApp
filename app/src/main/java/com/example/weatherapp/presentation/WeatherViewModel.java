package com.example.weatherapp.presentation;

import androidx.lifecycle.ViewModel;

import com.example.weatherapp.domain.use_case.GetWeatherUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private final GetWeatherUseCase getWeatherUseCase;

    @Inject
    public WeatherViewModel(GetWeatherUseCase getWeatherUseCase) {
        this.getWeatherUseCase = getWeatherUseCase;
    }
}