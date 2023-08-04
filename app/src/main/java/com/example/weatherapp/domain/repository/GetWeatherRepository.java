package com.example.weatherapp.domain.repository;

import com.example.weatherapp.data.remote.dto.GetWeatherResponseDto;

import io.reactivex.rxjava3.core.Single;

public interface GetWeatherRepository {

    Single<GetWeatherResponseDto> getWeather(
            Double latitude,
            Double longitude
    );
}