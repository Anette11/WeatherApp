package com.example.weatherapp.domain.use_case;

import com.example.weatherapp.data.remote.RemoteMappers;
import com.example.weatherapp.domain.data.Hourly;
import com.example.weatherapp.domain.repository.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetWeatherUseCase {

    private final WeatherRepository repository;

    @Inject
    public GetWeatherUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    public Single<Hourly> execute(
            double latitude,
            double longitude,
            String timezone
    ) {
        return repository
                .getWeather(latitude, longitude, timezone)
                .flatMap(weatherDto -> Single.just(RemoteMappers.fromWeatherDtoToWeather(weatherDto)))
                .flatMap(weather -> Single.just(weather.getHourly()));
    }
}