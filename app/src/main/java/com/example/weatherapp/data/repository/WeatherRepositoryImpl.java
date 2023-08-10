package com.example.weatherapp.data.repository;

import androidx.lifecycle.LiveData;

import com.example.weatherapp.data.local.WeatherDbManager;
import com.example.weatherapp.data.local.dbo.HourlyDbo;
import com.example.weatherapp.data.remote.WeatherApi;
import com.example.weatherapp.data.remote.dto.WeatherDto;
import com.example.weatherapp.domain.repository.WeatherRepository;

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
        weatherDbManager.deleteAllHourly();
        weatherDbManager.saveHourly(hourlyDbo);
    }

    @Override
    public LiveData<HourlyDbo> getWeather() {
        return weatherDbManager.getHourly();
    }
}