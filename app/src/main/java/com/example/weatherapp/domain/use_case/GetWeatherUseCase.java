package com.example.weatherapp.domain.use_case;

import androidx.lifecycle.LiveData;

import com.example.weatherapp.data.local.dbo.HourlyDbo;
import com.example.weatherapp.domain.repository.WeatherRepository;

public class GetWeatherUseCase {

    private final WeatherRepository repository;

    public GetWeatherUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    public LiveData<HourlyDbo> execute() {
        return repository.getWeather();
    }
}
