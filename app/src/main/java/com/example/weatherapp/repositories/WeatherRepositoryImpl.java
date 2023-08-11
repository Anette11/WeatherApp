package com.example.weatherapp.repositories;

import androidx.lifecycle.LiveData;

import com.example.weatherapp.database.WeatherDbManager;
import com.example.weatherapp.database.models.HourlyDbo;
import com.example.weatherapp.api.WeatherApi;
import com.example.weatherapp.api.models.WeatherDto;
import com.example.weatherapp.repositories.interfaces.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class WeatherRepositoryImpl implements WeatherRepository {

    private final WeatherApi weatherApi;
    private final WeatherDbManager weatherDbManager;

    @Inject
    public WeatherRepositoryImpl(
            WeatherApi weatherApi,
            WeatherDbManager weatherDbManager
    ) {
        this.weatherApi = weatherApi;
        this.weatherDbManager = weatherDbManager;
    }

    @Override
    public Single<WeatherDto> getWeather(
            double latitude,
            double longitude,
            String timezone
    ) {
        return weatherApi.getWeather(latitude, longitude, timezone);
    }

    @Override
    public void refreshWeather(HourlyDbo hourlyDbo) {
        weatherDbManager.refreshWeather(hourlyDbo);
    }

    @Override
    public LiveData<HourlyDbo> getWeather() {
        return weatherDbManager.getHourlyLiveData();
    }
}