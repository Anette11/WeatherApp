package com.example.weatherapp.use_cases;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.weatherapp.database.LocalMappers;
import com.example.weatherapp.models.Hourly;
import com.example.weatherapp.repositories.WeatherRepository;

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