package com.example.weatherapp.domain.use_case;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.weatherapp.data.local.LocalMappers;
import com.example.weatherapp.domain.data.Hourly;
import com.example.weatherapp.domain.repository.WeatherRepository;

public class GetWeatherFromDbUseCase {

    private final WeatherRepository repository;

    public GetWeatherFromDbUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    public LiveData<Hourly> execute() {
        return Transformations.map(
                repository.getWeather(),
                hourlyDbo -> hourlyDbo == null ? null : LocalMappers.fromHourlyDboToHourly(hourlyDbo)
        );
    }
}