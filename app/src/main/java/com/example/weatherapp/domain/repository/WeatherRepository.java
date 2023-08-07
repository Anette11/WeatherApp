package com.example.weatherapp.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.weatherapp.data.local.dbo.HourlyDbo;
import com.example.weatherapp.data.remote.dto.WeatherDto;

import io.reactivex.rxjava3.core.Single;

public interface WeatherRepository {

    Single<WeatherDto> getWeather(
            double latitude,
            double longitude,
            String timezone
    );

    void refreshWeather(HourlyDbo hourlyDbo);

    LiveData<HourlyDbo> getWeather();
}