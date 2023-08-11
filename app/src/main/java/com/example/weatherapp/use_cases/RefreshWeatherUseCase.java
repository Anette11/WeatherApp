package com.example.weatherapp.use_cases;

import com.example.weatherapp.database.DatabaseModelsMappers;
import com.example.weatherapp.api.ApiModelsMappers;
import com.example.weatherapp.repositories.WeatherRepository;
import com.example.weatherapp.utils.formatters.DateFormatter;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
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

    public Completable execute(
            double latitude,
            double longitude
    ) {
        return repository
                .getWeather(latitude, longitude, dateFormatter.getTimezone())
                .flatMap(weatherDto -> Single.just(ApiModelsMappers.fromWeatherDtoToWeather(weatherDto)))
                .flatMap(weather -> Single.just(weather.getHourly()))
                .flatMapCompletable(hourly -> {
                    repository.refreshWeather(DatabaseModelsMappers.fromHourlyToHourlyDbo(hourly));
                    return Completable.fromSingle(Single.just(hourly));
                });
    }
}