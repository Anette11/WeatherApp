package com.example.weatherapp.domain.use_case;

import com.example.weatherapp.data.remote.RemoteMappers;
import com.example.weatherapp.domain.data.GetWeatherResponse;
import com.example.weatherapp.domain.repository.GetWeatherRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetWeatherUseCase {

    private final GetWeatherRepository repository;

    @Inject
    public GetWeatherUseCase(GetWeatherRepository repository) {
        this.repository = repository;
    }

    public Single<GetWeatherResponse> execute(
            Double latitude,
            Double longitude
    ) {
        return repository.getWeather(latitude, longitude)
                .flatMap(getWeatherResponseDto ->
                        Single.just(RemoteMappers.fromGetWeatherResponseDtoToGetWeatherResponse(getWeatherResponseDto)));
    }
}