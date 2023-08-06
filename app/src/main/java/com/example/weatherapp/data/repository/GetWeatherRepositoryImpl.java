package com.example.weatherapp.data.repository;

import com.example.weatherapp.data.remote.WeatherApi;
import com.example.weatherapp.data.remote.dto.GetWeatherResponseDto;
import com.example.weatherapp.domain.repository.GetWeatherRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetWeatherRepositoryImpl implements GetWeatherRepository {

    private final WeatherApi weatherApi;

    @Inject
    public GetWeatherRepositoryImpl(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Single<GetWeatherResponseDto> getWeather(
            double latitude,
            double longitude,
            String timezone
    ) {
        return weatherApi.getWeather(latitude, longitude, timezone);
    }
}
