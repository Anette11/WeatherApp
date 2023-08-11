package com.example.weatherapp.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.weatherapp.database.models.HourlyDbo;
import com.example.weatherapp.api.models.WeatherDto;

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