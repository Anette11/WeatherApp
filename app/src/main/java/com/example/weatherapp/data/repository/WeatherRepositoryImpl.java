package com.example.weatherapp.data.repository;

import androidx.lifecycle.LiveData;

import com.example.weatherapp.data.local.WeatherDao;
import com.example.weatherapp.data.local.dbo.HourlyDbo;
import com.example.weatherapp.data.remote.WeatherApi;
import com.example.weatherapp.data.remote.dto.WeatherDto;
import com.example.weatherapp.domain.repository.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class WeatherRepositoryImpl implements WeatherRepository {

    private final WeatherApi weatherApi;
    private final WeatherDao weatherDao;

    @Inject
    public WeatherRepositoryImpl(
            WeatherApi weatherApi,
            WeatherDao weatherDao
    ) {
        this.weatherApi = weatherApi;
        this.weatherDao = weatherDao;
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
        weatherDao.deleteAll();
        weatherDao.save(hourlyDbo);
    }

    @Override
    public LiveData<HourlyDbo> getWeather() {
        return weatherDao.get();
    }
}
