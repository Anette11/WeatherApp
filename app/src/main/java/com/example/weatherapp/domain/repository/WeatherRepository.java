package com.example.weatherapp.domain.repository;

import com.example.weatherapp.data.remote.dto.WeatherDto;

import io.reactivex.rxjava3.core.Single;

public interface WeatherRepository {

    Single<WeatherDto> getWeather(
            double latitude,
            double longitude,
            String timezone
    );
}