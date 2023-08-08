package com.example.weatherapp.domain.use_case;

import com.example.weatherapp.data.local.LocalMappers;
import com.example.weatherapp.data.remote.RemoteMappers;
import com.example.weatherapp.domain.data.Hourly;
import com.example.weatherapp.domain.repository.WeatherRepository;
import com.example.weatherapp.presentation.utils.DateFormatter;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class RefreshWeatherUseCase {

    private final WeatherRepository repository;
    private final DateFormatter dateFormatter;

    @Inject
    public RefreshWeatherUseCase(
            WeatherRepository repository,
            DateFormatter dateFormatter
    ) {
        this.repository = repository;
        this.dateFormatter = dateFormatter;
    }

    public Single<Hourly> execute(
            double latitude,
            double longitude
    ) {
        return repository
                .getWeather(latitude, longitude, dateFormatter.getTimezone())
                .flatMap(weatherDto -> Single.just(RemoteMappers.fromWeatherDtoToWeather(weatherDto)))
                .flatMap(weather -> Single.just(weather.getHourly()))
                .flatMap(hourly -> {
                    repository.refreshWeather(LocalMappers.fromHourlyToHourlyDbo(hourly));
                    return Single.just(hourly);
                });
    }
}